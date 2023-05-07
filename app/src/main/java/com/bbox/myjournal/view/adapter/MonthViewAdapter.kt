package com.bbox.myjournal.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bbox.myjournal.databinding.FeelingItemMonthBinding
import com.bbox.myjournal.ui_models.FeelingDetailMonthItem
import com.bbox.myjournal.view.fragments.SeeAllFeelingFragment

class MonthViewAdapter(
    monthAndFeelingList: List<FeelingDetailMonthItem>,
    private val context: SeeAllFeelingFragment
) : RecyclerView.Adapter<FeelingMonthViewHolder>() {

    var monthDetailList = monthAndFeelingList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeelingMonthViewHolder {
        val view = FeelingItemMonthBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FeelingMonthViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: FeelingMonthViewHolder, position: Int) {
        holder.bind(monthDetailList[position])
    }

    override fun getItemCount() = monthDetailList.size
}
