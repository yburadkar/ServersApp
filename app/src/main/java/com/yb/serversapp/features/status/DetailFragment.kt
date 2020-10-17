package com.yb.serversapp.features.status

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yb.serversapp.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!
    private val serverStatus: UiServerStatus? by lazy {
        requireArguments().getParcelable(KEY_SERVER_STATUS)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val server = serverStatus
            server?.let {
                serverName.text = it.serverName
                status.text = it.responseCode?.toString()
                status.setBackgroundResource(it.getStatusColor())
                serverUrl.text = it.url
                responseTime.text = it.responseTime?.toString()
                responseClass.text = it.classType
            }
        }
    }

    companion object {
        private const val KEY_SERVER_STATUS = "server_status"

        fun newInstance(serverStatus: UiServerStatus): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_SERVER_STATUS, serverStatus)
                }
            }
        }
    }

}