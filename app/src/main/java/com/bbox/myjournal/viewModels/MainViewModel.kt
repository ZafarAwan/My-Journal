package com.bbox.myjournal.viewModels

import android.content.Context
import androidx.lifecycle.*
import com.bbox.myjournal.utils.MutableEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    var addFeelingBtn: MutableEvent<Boolean> = MutableEvent()
    var seeAllFeelingBtn: MutableEvent<Boolean> = MutableEvent()

    fun addFeeling() {
        addFeelingBtn.postEvent(true)
    }

    fun seeAllFeeling() {
        seeAllFeelingBtn.postEvent(true)
    }

}