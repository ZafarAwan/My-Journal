package com.bbox.myjournal.view.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bbox.myjournal.R
import com.bbox.myjournal.base.BaseBindableFragment
import com.bbox.myjournal.databinding.FragmentAddFeelingBinding
import com.bbox.myjournal.utils.Utils
import com.bbox.myjournal.utils.Utils.showErrorMsgDialog
import com.bbox.myjournal.utils.observeEvent
import com.bbox.myjournal.viewModels.AddNewFeelingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AddNewFeelingFragment : BaseBindableFragment<FragmentAddFeelingBinding>() {

    override val layout: Int
        get() = R.layout.fragment_add_feeling

    private val addNewFeelingViewModel by viewModels<AddNewFeelingViewModel>()

    override fun onBind() = with(binding) {
        lifecycleOwner = viewLifecycleOwner
        viewModel = addNewFeelingViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewFeelingViewModel.initData()
        initObserver()
    }

    private fun initObserver() {


        observeEvent(addNewFeelingViewModel.openPicker) {
            if (it)
                openDateAndTimePicker()
        }

        observeEvent(addNewFeelingViewModel.clearEditFiled) {
            if (it)
                binding.etDetail.setText("")
        }

        observeEvent(addNewFeelingViewModel.showDialog) {
            if (it.isNotEmpty())
                showErrorMsgDialog(it, requireContext())
        }

    }

    private fun openDateAndTimePicker() {

        val c: Calendar = Calendar.getInstance()
        var mYear = c.get(Calendar.YEAR)
        var mMonth = c.get(Calendar.MONTH)
        var mDay = c.get(Calendar.DAY_OF_MONTH)
        var mHour = c.get(Calendar.HOUR_OF_DAY)
        var mMinute = c.get(Calendar.MINUTE)


        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.CustomTimePickerDialogTheme,
            { _, year, monthOfYear, dayOfMonth ->
                openTimePicker(year, monthOfYear, dayOfMonth, mHour, mMinute)
            },
            mYear,
            mMonth,
            mDay
        )
        datePickerDialog.show()
    }

    private fun openTimePicker(
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int,
        mHour: Int,
        mMinute: Int
    ) {

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            R.style.CustomTimePickerDialogTheme,
            { _, hourOfDay, minute ->
                val title = requireContext().getString(R.string.label_date_hint)

                val hour = if (hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"
                val nMinute = if (minute < 10) "0$minute" else "$minute"


                val dateAndTime =
                    Utils.getFormattedDate("$dayOfMonth-${monthOfYear + 1}-$year $hour:$nMinute")

                addNewFeelingViewModel.tvDateAndTime.postValue("$title \n${dateAndTime.toString()}")
                addNewFeelingViewModel.selectDateForCompare = dateAndTime.toString()
                addNewFeelingViewModel.selectDateAndTime = dateAndTime
            },
            mHour,
            mMinute,
            false
        )
        timePickerDialog.show()
    }

}