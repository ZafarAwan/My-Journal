package com.bbox.myjournal.ui_models


data class FeelingDetailMonthItem(
    var monthName: String,
    var month: Int,
    var daysFeelingData: ArrayList<FeelingDetailDayItem>
) {

    fun getMonthNameValue(): String {
        return "Month - $monthName"
    }
}
