package com.bbox.myjournal.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bbox.myjournal.databinding.FeelingItemDayBinding
import com.bbox.myjournal.ui_models.FeelingDetailDayItem
import com.bbox.myjournal.view.fragments.SeeAllFeelingFragment

class DayViewAdapter(daysFeelingDetail: List<FeelingDetailDayItem>, private val context: SeeAllFeelingFragment) :
    RecyclerView.Adapter<FeelingDayViewHolder>() {

    private var dayDetailList = daysFeelingDetail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeelingDayViewHolder {
        val view = FeelingItemDayBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FeelingDayViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: FeelingDayViewHolder, position: Int) {
        holder.bind(dayDetailList[position])
    }

    override fun getItemCount() = dayDetailList.size
}
