package com.bbox.myjournal.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bbox.myjournal.R
import com.bbox.myjournal.databinding.FragmentSeeFeelingBinding
import com.bbox.myjournal.base.BaseBindableFragment
import com.bbox.myjournal.utils.Utils.showErrorMsgDialog
import com.bbox.myjournal.utils.observeEvent
import com.bbox.myjournal.view.adapter.SeeAllFeelingAdapter
import com.bbox.myjournal.viewModels.SeeAllFeelingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllFeelingFragment : BaseBindableFragment<FragmentSeeFeelingBinding>() {

    override val layout: Int
        get() = R.layout.fragment_see_feeling

    private val seeAllFeelingViewModel by viewModels<SeeAllFeelingViewModel>()
    private val adapter = SeeAllFeelingAdapter(this@SeeAllFeelingFragment)

    override fun onBind() = with(binding) {
        lifecycleOwner = viewLifecycleOwner
        viewModel = seeAllFeelingViewModel
        rvList.layoutManager = LinearLayoutManager(context)
        rvList.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seeAllFeelingViewModel.getAllFeelingList()

        initObserver()
    }

    private fun initObserver() {
        observeEvent(seeAllFeelingViewModel.feelingList) {
            adapter.setFeelingDetailList(it)
        }

        observeEvent(seeAllFeelingViewModel.showDialog) {
            showErrorMsgDialog(it, requireContext())
        }
    }

}