package com.yb.serversapp.features.status.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yb.serversapp.databinding.ItemServerBinding
import com.yb.serversapp.databinding.ItemServerTypeBinding
import com.yb.serversapp.domain.models.ServerStatus
import com.yb.serversapp.features.status.dashboard.ServersAdapter.ServerViewItem
import com.yb.serversapp.features.status.dashboard.ServersAdapter.ServerViewItem.ServerStatusItem
import com.yb.serversapp.features.status.dashboard.ServersAdapter.ServerViewItem.ServerTypeItem
import com.yb.serversapp.models.UiServerStatus
import java.util.*

class ServersAdapter(
    private val serverClickAction: (UiServerStatus) -> Unit
) : ListAdapter<ServerViewItem, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(DIFF_CALLBACK).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SERVER_TYPE_VIEWTYPE -> ServerTypeViewHolder.create(parent)
            SERVER_STATUS_VIEWTYPE -> ServerStatusViewHolder.create(parent, serverClickAction)
            else -> throw Exception("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ServerTypeItem -> (holder as ServerTypeViewHolder).bind(item)
            is ServerStatusItem -> (holder as ServerStatusViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).viewType

    class ServerTypeViewHolder(private val binding: ItemServerTypeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ServerTypeItem) {
            with(binding) {
                serverType.text = item.serverType
            }
        }

        companion object {
            fun create(parent: ViewGroup): ServerTypeViewHolder {
                return ServerTypeViewHolder(ItemServerTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class ServerStatusViewHolder(
        private val binding: ItemServerBinding,
        private val serverClickAction: (UiServerStatus) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ServerStatusItem) {
            with(binding) {
                status.text = item.status.responseCode?.toString()
                status.setBackgroundResource(item.status.getStatusColor())
                serverName.text = item.status.serverName
                root.setOnClickListener { serverClickAction.invoke(item.status) }
            }
        }

        companion object {
            fun create(parent: ViewGroup, serverClickAction: (UiServerStatus) -> Unit): ServerStatusViewHolder {
                return ServerStatusViewHolder(
                    binding = ItemServerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    serverClickAction = serverClickAction
                )
            }
        }
    }

    sealed class ServerViewItem(val viewType: Int, val id: String = UUID.randomUUID().toString()) {
        data class ServerTypeItem(val serverType: String) : ServerViewItem(SERVER_TYPE_VIEWTYPE)

        data class ServerStatusItem(val status: UiServerStatus) : ServerViewItem(SERVER_STATUS_VIEWTYPE)

        companion object {
            fun from(statuses: List<ServerStatus>): List<ServerViewItem> {
                val items = mutableListOf<ServerViewItem>()
                var prevType = ""
                for (status in statuses) {
                    if (prevType != status.type) {
                        items.add(ServerTypeItem(serverType = status.type))
                        prevType = status.type
                    }
                    items.add(ServerStatusItem(status = UiServerStatus(status)))
                }
                return items
            }
        }
    }

    companion object {
        private const val SERVER_TYPE_VIEWTYPE = 1
        private const val SERVER_STATUS_VIEWTYPE = 2

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ServerViewItem>() {

            override fun areItemsTheSame(oldItem: ServerViewItem, newItem: ServerViewItem): Boolean {
                return when {
                    oldItem is ServerTypeItem && newItem is ServerTypeItem -> oldItem.serverType == newItem.serverType
                    oldItem is ServerStatusItem && newItem is ServerStatusItem -> oldItem.status.serverName == newItem.status.serverName
                    else -> false
                }
            }

            override fun areContentsTheSame(oldItem: ServerViewItem, newItem: ServerViewItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}