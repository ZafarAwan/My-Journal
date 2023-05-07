package com.bbox.myjournal.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bbox.myjournal.databinding.FeelingItemTimeBinding
import com.bbox.myjournal.ui_models.FeelingDetailItem
import com.bbox.myjournal.view.fragments.SeeAllFeelingFragment

class TimeViewAdapter(
    timeFeelingData: List<FeelingDetailItem>,
    private val context: SeeAllFeelingFragment
) :
    RecyclerView.Adapter<FeelingTimeViewHolder>() {

    var monthDetailList = timeFeelingData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeelingTimeViewHolder {
        val view = FeelingItemTimeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FeelingTimeViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: FeelingTimeViewHolder, position: Int) {
        holder.bind(monthDetailList[position])
    }

    override fun getItemCount() = monthDetailList.size
}
