package com.bbox.myjournal.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbox.myjournal.R
import com.bbox.myjournal.enums.ChooseColor
import com.bbox.myjournal.repositories.local.factories.IJournalFactory
import com.bbox.myjournal.repositories.local.feeling_repository.IFeelingRepository
import com.bbox.myjournal.repositories.local.db_models.FeelingDetail
import com.bbox.myjournal.utils.MutableEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class AddNewFeelingViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatcher: CoroutineDispatcher,
    journalFactory: IJournalFactory
) : ViewModel() {

    var etDetail = ""

    val tvColor = MutableLiveData<String>()
    val tvDateAndTime = MutableLiveData<String>()
    var selectedColor: Int = -1
    var selectDateForCompare: String = ""
    var selectDateAndTime: Date? = null

    var showDialog: MutableEvent<String> = MutableEvent()
    var openPicker: MutableEvent<Boolean> = MutableEvent()
    var clearEditFiled: MutableEvent<Boolean> = MutableEvent()

    private val feelingRepository: IFeelingRepository by lazy {
        journalFactory.create(IFeelingRepository::class.java)
    }

    private fun submit() {
        viewModelScope.launch(dispatcher) {
            feelingRepository.saveFeelingDetail(getAllDetail())
                .collect {
                    clearAllFieldsData()
                    showDialog.postEvent(context.getString(R.string.label_added))
                }
        }
    }

    private fun getAllDetail(): FeelingDetail {
        return FeelingDetail(
            feelingData = etDetail,
            feelingColor = selectedColor.toString(),
            feelingDateAndTime = selectDateAndTime
        )
    }

    fun checkValidation() {
        when {
            selectedColor == -1 -> showDialog.postEvent(context.getString(R.string.label_choose_color))
            selectDateForCompare.isEmpty() -> showDialog.postEvent(context.getString(R.string.label_select_date))
            etDetail.isEmpty() -> showDialog.postEvent(context.getString(R.string.label_feeling_detail))
            else -> submit()
        }
    }

    fun chooseColor(color: Int) {
        selectedColor = color
        when (color) {
            ChooseColor.GREEN.value -> tvColor.postValue(context.getString(R.string.label_green))
            ChooseColor.YELLOW.value -> tvColor.postValue(context.getString(R.string.label_yellow))
            ChooseColor.RED.value -> tvColor.postValue(context.getString(R.string.label_red))
        }
    }

    fun openCalender() {
        openPicker.postEvent(true)
    }

    fun initData() {
        tvColor.postValue(context.getString(R.string.label_color_hint))
        tvDateAndTime.postValue(context.getString(R.string.label_date_hint))
    }

    private fun clearAllFieldsData() {
        clearEditFiled.postEvent(true)
        initData()
        etDetail = ""
        selectedColor = -1
        selectDateForCompare = ""
        selectDateAndTime = null
    }
}
