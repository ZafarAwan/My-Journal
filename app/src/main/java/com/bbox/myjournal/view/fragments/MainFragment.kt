package com.bbox.myjournal.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bbox.myjournal.R
import com.bbox.myjournal.databinding.FragmentMainBinding
import com.bbox.myjournal.base.BaseBindableFragment
import com.bbox.myjournal.utils.Utils.navigateToMasterScreen
import com.bbox.myjournal.utils.observeEvent
import com.bbox.myjournal.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseBindableFragment<FragmentMainBinding>() {

    override val layout: Int
        get() = R.layout.fragment_main

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onBind() = with(binding) {
        lifecycleOwner = viewLifecycleOwner
        viewModel = mainViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {


        observeEvent(mainViewModel.addFeelingBtn) {
            navigateToMasterScreen(MainFragmentDirections.toAddFeelingFragment())
        }

        observeEvent(mainViewModel.seeAllFeelingBtn) {
            navigateToMasterScreen(MainFragmentDirections.toSeeFeelingFragment())
        }

    }

}