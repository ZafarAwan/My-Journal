package com.bbox.myjournal.ui_models

import com.bbox.myjournal.repositories.local.db_models.FeelingDetail
import java.time.DayOfWeek
import java.time.Month
import java.time.format.TextStyle
import java.util.*
import kotlin.collections.ArrayList

fun setFeelingDetailAccordingToView(feelingDetails: List<FeelingDetail>): List<FeelingDetailUiModel> {
    val feelingDetailSingleItemList = feelingDetails.map {

        val cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
        cal.time = it.feelingDateAndTime

        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_WEEK)
        val date: Int = cal.get(Calendar.DATE)
        var hour: Int = cal.get(Calendar.HOUR)
        var minute: Int = cal.get(Calendar.MINUTE)

        val monthName = Month.of(month + 1).getDisplayName(TextStyle.FULL, Locale.getDefault())
        val dayName = DayOfWeek.of(day).getDisplayName(TextStyle.FULL, Locale.getDefault())

        var nHours = hour + 5
        val nHour = if (nHours < 10) "0$nHours" else "$nHours"

        val nMinute = if (minute < 10) "0${minute}" else "$minute"

        FeelingDetailSingleItem(
            year.toString(),
            monthName,
            month + 1,
            "$dayName, $date",
            date,
            it.feelingData,
            "$nHour:$nMinute",
            it.feelingColor.toInt()
        )

    }


    val filterYearList = mutableListOf<FeelingDetailUiModel>()
    feelingDetailSingleItemList.map {
        if (filterYearList.isEmpty())
            createObjectAndAddInList(filterYearList, it)
        else
            checkItsExistOrNot(filterYearList, it)
    }

    return filterByMonth(filterYearList)
}


/********** filter by Year Logic *************/


fun createObjectAndAddInList(
    filterYearList: MutableList<FeelingDetailUiModel>,
    item: FeelingDetailSingleItem
) {

    val timeObj = FeelingDetailItem(item.dayDetail, "${item.dayTime}", item.dayFeelingColor)
    val dayObj = FeelingDetailDayItem("${item.dayAndDate}", item.date, arrayListOf(timeObj))
    val monthObj = FeelingDetailMonthItem(
        item.monthName, item.month,
        arrayListOf(dayObj)
    )
    filterYearList.add(FeelingDetailUiModel(item.year, arrayListOf(monthObj)))
}

fun checkItsExistOrNot(
    filterYearList: MutableList<FeelingDetailUiModel>,
    singleItem: FeelingDetailSingleItem
) {
    val item = filterYearList.firstOrNull {
        it.year == singleItem.year
    }

    if (item != null) {
        item.monthAndFeelingList.add(createMonthItem(singleItem))
    } else
        createObjectAndAddInList(filterYearList, singleItem)
}

fun createMonthItem(singleItem: FeelingDetailSingleItem): FeelingDetailMonthItem {

    val timeObj =
        FeelingDetailItem(singleItem.dayDetail, "${singleItem.dayTime}", singleItem.dayFeelingColor)
    val dayObj =
        FeelingDetailDayItem("${singleItem.dayAndDate}", singleItem.date, arrayListOf(timeObj))
    return FeelingDetailMonthItem(
        singleItem.monthName, singleItem.month,
        arrayListOf(dayObj)
    )
}

fun filterByMonth(filterYearList: MutableList<FeelingDetailUiModel>): List<FeelingDetailUiModel> {

    return filterYearList.map {
        it.monthAndFeelingList = filterMonthListOperation(it)
        it
    }
}


/********** filter by Month Logic *************/


fun filterMonthListOperation(it: FeelingDetailUiModel): ArrayList<FeelingDetailMonthItem> {
    val filterMonthList = arrayListOf<FeelingDetailMonthItem>()

    it.monthAndFeelingList.map { it1 ->
        if (filterMonthList.isEmpty()) {
            createMonthObjectAndAddInList(filterMonthList, it1)
        } else {
            checkMonthItsExistOrNot(filterMonthList, it1)
        }
    }

    return filterByDay(filterMonthList)
}

fun createMonthObjectAndAddInList(
    filterMonthList: MutableList<FeelingDetailMonthItem>,
    item: FeelingDetailMonthItem
) {
    val timeObj = FeelingDetailItem(
        item.daysFeelingData[0].timeFeelingData[0].dayDetail,
        item.daysFeelingData[0].timeFeelingData[0].dayTime,
        item.daysFeelingData[0].timeFeelingData[0].dayFeelingColor
    )
    val dayObj =
        FeelingDetailDayItem(
            item.daysFeelingData[0].dayAndDate,
            item.daysFeelingData[0].date,
            arrayListOf(timeObj)
        )
    filterMonthList.add(
        FeelingDetailMonthItem(
            item.monthName, item.month,
            arrayListOf(dayObj)
        )
    )
}

fun checkMonthItsExistOrNot(
    filterMonthList: MutableList<FeelingDetailMonthItem>,
    singleItem: FeelingDetailMonthItem
) {

    val item = filterMonthList.firstOrNull {
        it.month == singleItem.month
    }

    if (item != null) {
        item.daysFeelingData.add(createDayItem(singleItem))
    } else
        createMonthObjectAndAddInList(filterMonthList, singleItem)
}

fun createDayItem(singleItem: FeelingDetailMonthItem): FeelingDetailDayItem {

    val timeObj = FeelingDetailItem(
        singleItem.daysFeelingData[0].timeFeelingData[0].dayDetail,
        singleItem.daysFeelingData[0].timeFeelingData[0].dayTime,
        singleItem.daysFeelingData[0].timeFeelingData[0].dayFeelingColor

    )
    return FeelingDetailDayItem(
        singleItem.daysFeelingData[0].dayAndDate,
        singleItem.daysFeelingData[0].date,
        arrayListOf(timeObj)
    )
}


/********** filter by Day Logic *************/


fun filterByDay(filterMonthList: ArrayList<FeelingDetailMonthItem>): ArrayList<FeelingDetailMonthItem> {

    return filterMonthList.map {
        it.daysFeelingData = filterDayListOperation(it)
        it
    } as ArrayList<FeelingDetailMonthItem>

}

fun filterDayListOperation(singleMonth: FeelingDetailMonthItem): ArrayList<FeelingDetailDayItem> {
    val filterDayList = arrayListOf<FeelingDetailDayItem>()

    singleMonth.daysFeelingData.map { it1 ->
        if (filterDayList.isEmpty()) {
            createDayObjectAndAddInList(filterDayList, it1)
        } else {
            checkDayItsExistOrNot(filterDayList, it1)
        }
    }

    return filterDayList
}

fun createDayObjectAndAddInList(
    filterDayList: ArrayList<FeelingDetailDayItem>,
    item: FeelingDetailDayItem
) {

    val timeObj = FeelingDetailItem(
        item.timeFeelingData[0].dayDetail,
        item.timeFeelingData[0].dayTime,
        item.timeFeelingData[0].dayFeelingColor,
    )
    filterDayList.add(
        FeelingDetailDayItem(item.dayAndDate, item.date, arrayListOf(timeObj))
    )
}


fun checkDayItsExistOrNot(
    filterDayList: java.util.ArrayList<FeelingDetailDayItem>,
    singleItem: FeelingDetailDayItem
) {
    val item = filterDayList.firstOrNull {
        it.date == singleItem.date
    }

    if (item != null) {
        item.timeFeelingData.add(createTimeItem(singleItem))
    } else
        createDayObjectAndAddInList(filterDayList, singleItem)

}

fun createTimeItem(singleItem: FeelingDetailDayItem): FeelingDetailItem {
    return FeelingDetailItem(
        singleItem.timeFeelingData[0].dayDetail,
        singleItem.timeFeelingData[0].dayTime,
        singleItem.timeFeelingData[0].dayFeelingColor,
    )
}


/********** filter Background Color *************/


fun getColorValue(feelingColorList: List<Int>): Int {
    return if (feelingColorList.size == 1)
        feelingColorList[0]
    else {
        feelingColorList.groupingBy { it }
            .eachCount()
            .maxByOrNull { it.value }!!
            .key
    }
}

fun getEntries(dailyEntries: ArrayList<FeelingDetailDayItem>): List<Int> {
    val feelingSingleColorList = arrayListOf<Int>()

    val feelingColorList = dailyEntries.map {
        it.timeFeelingData.map {
            feelingSingleColorList.add(it.dayFeelingColor)
        }
        feelingSingleColorList
    }.flatten()

    return feelingColorList
}

fun getMonthEntries(dailyEntries: ArrayList<FeelingDetailDayItem>): Int {
    var count = 0

    dailyEntries.forEach {
        it.timeFeelingData.forEach {
            count++
        }
    }
    return count
}




