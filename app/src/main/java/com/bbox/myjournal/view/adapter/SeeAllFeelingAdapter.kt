package com.bbox.myjournal.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bbox.myjournal.databinding.FeelingItemYearBinding
import com.bbox.myjournal.ui_models.FeelingDetailUiModel
import com.bbox.myjournal.view.fragments.SeeAllFeelingFragment

class SeeAllFeelingAdapter(private val context: SeeAllFeelingFragment) :
    RecyclerView.Adapter<FeelingYearViewHolder>() {

    private var feelingDetailList = mutableListOf<FeelingDetailUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeelingYearViewHolder {
        val view = FeelingItemYearBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FeelingYearViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: FeelingYearViewHolder, position: Int) {
        holder.bind(feelingDetailList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFeelingDetailList(feelingDetailList: List<FeelingDetailUiModel>) {
        this.feelingDetailList = feelingDetailList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = feelingDetailList.size
}
