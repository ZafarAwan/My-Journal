package com.bbox.myjournal.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbox.myjournal.R
import com.bbox.myjournal.ui_models.setFeelingDetailAccordingToView
import com.bbox.myjournal.repositories.local.factories.IJournalFactory
import com.bbox.myjournal.repositories.local.feeling_repository.IFeelingRepository
import com.bbox.myjournal.ui_models.FeelingDetailUiModel
import com.bbox.myjournal.utils.MutableEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SeeAllFeelingViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatcher: CoroutineDispatcher,
    journalFactory: IJournalFactory
) : ViewModel() {

    var showDialog: MutableEvent<String> = MutableEvent()
    var feelingList: MutableEvent<List<FeelingDetailUiModel>> = MutableEvent()

    var progress = MutableLiveData<Int>()

    private val feelingRepository: IFeelingRepository by lazy {
        journalFactory.create(IFeelingRepository::class.java)
    }

    fun getAllFeelingList() {

        progress.postValue(1)
        viewModelScope.launch(dispatcher) {
            feelingRepository.getFeelingDetailList()
                .collect {
                    if (it?.isNotEmpty() == true) {
                        val filterList = setFeelingDetailAccordingToView(it)
                        progress.postValue(0)
                        feelingList.postEvent(filterList)
                    } else {
                        progress.postValue(0)
                        showDialog.postEvent(context.getString(R.string.label_no_data))
                    }
                }
        }
    }

}
