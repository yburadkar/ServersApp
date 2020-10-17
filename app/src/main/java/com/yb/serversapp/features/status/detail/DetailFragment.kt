package com.yb.serversapp.features.status.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.yb.serversapp.R
import com.yb.serversapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentDetailBinding.inflate(inflater, container, false).also { _binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val serverStatus = args.serverStatus
        with(binding) {
            serverStatus.let {
                serverName.text = it.serverName
                status.text = it.responseCode?.toString()
                status.setBackgroundResource(it.getStatusColor())
                serverUrl.text = it.url ?: getString(R.string.url_null_text)
                responseTime.text = it.responseTime?.toString()
                responseClass.text = it.classType
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}