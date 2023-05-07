package com.bbox.myjournal.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bbox.myjournal.databinding.FeelingItemYearBinding
import com.bbox.myjournal.ui_models.FeelingDetailUiModel
import com.bbox.myjournal.view.fragments.SeeAllFeelingFragment

class FeelingYearViewHolder(
    private val binding: FeelingItemYearBinding,
    private val context: SeeAllFeelingFragment
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(yearDetail: FeelingDetailUiModel) {
        binding.item = yearDetail
        val monthViewAdapter = MonthViewAdapter(yearDetail.monthAndFeelingList, context)
        binding.rvList.adapter = monthViewAdapter
        binding.rvList.layoutManager = LinearLayoutManager(context.requireContext())
    }
}