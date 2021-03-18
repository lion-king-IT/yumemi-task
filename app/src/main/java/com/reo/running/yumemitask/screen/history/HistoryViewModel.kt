package com.reo.running.yumemitask.screen.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryViewModel : ViewModel() {
    private val _historyText = MutableLiveData<String>()
    val historyText: LiveData<String>
        get() = _historyText
    init {
        _historyText.value = "履歴"
    }
}