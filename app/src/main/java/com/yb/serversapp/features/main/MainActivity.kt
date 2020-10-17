package com.yb.serversapp.features.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.yb.serversapp.App
import com.yb.serversapp.R
import com.yb.serversapp.databinding.ActivityMainBinding
import com.yb.serversapp.di.ViewModelFactory
import com.yb.serversapp.features.status.ServersFragment
import com.yb.serversapp.features.status.ServersViewModel
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        if(savedInstanceState == null) switchFragment(ServersFragment.newInstance(), false)
    }

    private fun switchFragment(fragment: Fragment, addToBackStack: Boolean) {
        supportFragmentManager.commit(allowStateLoss = false) {
            replace(R.id.frag_container, fragment, CURRENT_FRAGMENT_TAG)
            if(addToBackStack) addToBackStack(null)
        }
    }

    private fun inject() {
        (application as App).appComponent.inject(this)
    }

    companion object {
        private const val CURRENT_FRAGMENT_TAG = "current_fragment"
    }

}