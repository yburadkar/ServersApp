package com.yb.serversapp.features.status

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yb.serversapp.App
import com.yb.serversapp.R
import com.yb.serversapp.databinding.FragmentServersBinding
import com.yb.serversapp.di.ViewModelFactory
import com.yb.serversapp.domain.models.ServerStatus
import com.yb.serversapp.helpers.Resource
import com.yb.serversapp.helpers.ResourceStatus
import com.yb.serversapp.helpers.showSnackbar
import javax.inject.Inject

class ServersFragment : Fragment() {
    private var _binding: FragmentServersBinding? = null
    private val binding: FragmentServersBinding get() = _binding!!
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ServersViewModel by viewModels { viewModelFactory }
    private lateinit var serversAdapter: ServersAdapter

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentServersBinding.inflate(inflater, container, false).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.serverStatuses.observe(viewLifecycleOwner) {
            renderViewState(it)
        }
    }

    private fun renderViewState(resource: Resource<List<ServerStatus>>) {
        showUserMessages(resource.status)
        resource.data?.let {
            serversAdapter.submitList(ServersAdapter.ServerViewItem.from(it))
        }
    }

    private fun showUserMessages(status: ResourceStatus) {
        with(binding) {
            if (status == ResourceStatus.ERROR) root.showSnackbar(getString(R.string.loading_error_message))
            srlServers.isRefreshing = status == ResourceStatus.LOADING
        }
    }

    private fun setUpViews() {
        serversAdapter = ServersAdapter()
        with(binding) {
            rvServers.apply {
                adapter = serversAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
            srlServers.setOnRefreshListener { viewModel.getServerStatuses() }
        }
    }

    private fun inject() {
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ServersFragment()
    }

}