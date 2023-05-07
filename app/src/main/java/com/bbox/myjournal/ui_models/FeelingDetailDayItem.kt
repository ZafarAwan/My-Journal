package com.bbox.myjournal.ui_models

data class FeelingDetailDayItem(
    var dayAndDate: String,
    var date: Int,
    var timeFeelingData: ArrayList<FeelingDetailItem>
){
    fun getDayName(): String {
        return "Day - $dayAndDate"
    }
}
