package com.bbox.myjournal.view.adapter

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bbox.myjournal.R
import com.bbox.myjournal.databinding.FeelingItemTimeBinding
import com.bbox.myjournal.enums.ChooseColor
import com.bbox.myjournal.ui_models.FeelingDetailItem
import com.bbox.myjournal.view.fragments.SeeAllFeelingFragment

class FeelingTimeViewHolder(
    private val binding: FeelingItemTimeBinding,
    private val context: SeeAllFeelingFragment
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(timeDetailItem: FeelingDetailItem) {
        binding.item = timeDetailItem
        binding.viewBg.background = getColorCode(timeDetailItem.dayFeelingColor)
    }

    private fun getColorCode(dayFeelingColor: Int): Drawable? {

        return when (dayFeelingColor) {
            ChooseColor.GREEN.value -> context.requireContext().getDrawable(R.drawable.circle_green)
            ChooseColor.RED.value -> context.requireContext().getDrawable(R.drawable.circle_red)
            ChooseColor.YELLOW.value -> context.requireContext()
                .getDrawable(R.drawable.circle_yellow)
            else -> context.requireContext().getDrawable(R.drawable.circle_green)
        }
    }
}