package com.bbox.myjournal.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bbox.myjournal.databinding.FeelingItemDayBinding
import com.bbox.myjournal.ui_models.FeelingDetailDayItem
import com.bbox.myjournal.view.fragments.SeeAllFeelingFragment

class FeelingDayViewHolder(
    private val binding: FeelingItemDayBinding,
    private val context: SeeAllFeelingFragment
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(dayDetail: FeelingDetailDayItem) {
        binding.item = dayDetail
        binding.tvEntries.text = "${dayDetail.timeFeelingData.size} entries"
        val timeViewAdapter = TimeViewAdapter(dayDetail.timeFeelingData, context)
        binding.rvList.adapter = timeViewAdapter
        binding.rvList.layoutManager = LinearLayoutManager(context.requireContext())
    }
}