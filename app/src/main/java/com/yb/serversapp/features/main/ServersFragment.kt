package com.yb.serversapp.features.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.yb.serversapp.App
import com.yb.serversapp.databinding.FragmentServersBinding
import com.yb.serversapp.di.ViewModelFactory
import javax.inject.Inject

class ServersFragment: Fragment() {
    private var _binding: FragmentServersBinding? = null
    private lateinit var binding: FragmentServersBinding
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentServersBinding.inflate(inflater, container, false).also { _binding = it }.root
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