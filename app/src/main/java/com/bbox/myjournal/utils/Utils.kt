package com.bbox.myjournal.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bbox.myjournal.R
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object Utils {

    fun Fragment.navigateToMasterScreen(directions: NavDirections) {
        try {
            requireParentFragment().findNavController().navigate(directions)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getFormattedDate(currentDate: String): Date? {
        val formatter =  DateTimeFormatter.ofPattern("d-M-yyyy HH:mm")
        val date = LocalDateTime.parse(currentDate, formatter)

        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant())
    }


    fun showErrorMsgDialog(it: String?, requireContext: Context) {

        val alertDialogBuilder = AlertDialog.Builder(requireContext)
        alertDialogBuilder.setTitle(requireContext.getString(R.string.label_alert))
        alertDialogBuilder.setMessage(it)
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setPositiveButton(requireContext.getString(R.string.label_ok)) { d, _ ->
            d.dismiss()
        }
        alertDialogBuilder.create().show()
    }


/*
    fun dummyData(): MutableList<FeelingDetailUiModel> {
        var list1 = arrayListOf<FeelingDetailUiModel>()
        var list2 = arrayListOf<FeelingDetailMonthItem>()
        var list3 = arrayListOf<FeelingDetailDayItem>()
        var list4 = arrayListOf<FeelingDetailItem>()

        var item4 = FeelingDetailItem(
            "July ansfcguias asgcasog ascgausi ascguaisgc ascgaus ascjasuo",
            "19:16"
        )
        var item3 = FeelingDetailItem(
            " hkcuas ansfcguias asgcasog ascgausi ascguaisgc ascgaus ascjasuo",
            "15:16"
        )
        list4.add(item3)
        list4.add(item4)

        var item2 = FeelingDetailDayItem("Fri, 10", 1, list4)
        var item5 = FeelingDetailDayItem("Fri, 19", 5, list4)
        list3.add(item2)
        list3.add(item5)

        var item1 = FeelingDetailMonthItem("July", 1, 1, 3, list3)
        var item6 = FeelingDetailMonthItem("Aug", 2, 2, 6, list3)
        list2.add(item1)
        list2.add(item6)

        var item = FeelingDetailUiModel("2023", list2)

        list1.add(item)

        return list1
    }
*/

}