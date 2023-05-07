package com.bbox.myjournal.ui_models

data class FeelingDetailUiModel(
    var year: String,
    var monthAndFeelingList: ArrayList<FeelingDetailMonthItem>
){
    fun getYearValue(): String {
        return "Year - $year"
    }
}