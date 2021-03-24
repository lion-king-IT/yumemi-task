package com.reo.running.yumemitask.screen.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reo.running.yumemitask.YumemiApplication

class HistoryViewModel : ViewModel() {
    private val _historyText: MutableLiveData<String> =
        MutableLiveData<String>().also { mutableLiveData ->
            mutableLiveData.value = "履歴"
        }

    val historyText: LiveData<String>
        get() = _historyText
}