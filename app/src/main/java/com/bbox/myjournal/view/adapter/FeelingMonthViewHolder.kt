package com.bbox.myjournal.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bbox.myjournal.R
import com.bbox.myjournal.databinding.FeelingItemMonthBinding
import com.bbox.myjournal.enums.ChooseColor
import com.bbox.myjournal.ui_models.*
import com.bbox.myjournal.view.fragments.SeeAllFeelingFragment

class FeelingMonthViewHolder(
    private val binding: FeelingItemMonthBinding,
    private val context: SeeAllFeelingFragment
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(monthDetail: FeelingDetailMonthItem) {

        binding.item = monthDetail

        val monthEntries = getMonthEntries(monthDetail.daysFeelingData)
        val entries = getEntries(monthDetail.daysFeelingData)

        binding.tvEntries.text = "$monthEntries entries"
        binding.monthView.setBackgroundColor(getColorCode(entries, context))

        val dayViewAdapter = DayViewAdapter(monthDetail.daysFeelingData, context)
        binding.rvList.adapter = dayViewAdapter
        binding.rvList.layoutManager = LinearLayoutManager(context.requireContext())

    }

    private fun getColorCode(
        daysFeelingData: List<Int>,
        context: SeeAllFeelingFragment
    ): Int {
        return when (getColorValue(daysFeelingData)) {
            ChooseColor.GREEN.value -> context.requireContext().getColor(R.color.color_green)
            ChooseColor.RED.value -> context.requireContext().getColor(R.color.color_red)
            ChooseColor.YELLOW.value -> context.requireContext().getColor(R.color.color_yellow)
            else -> context.requireContext().getColor(R.color.color_green)
        }
    }

}


