package com.reo.running.yumemitask.screen.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {
    private val _detailsText = MutableLiveData<String>()
    val detailsText: LiveData<String>
        get() = _detailsText
    init {
        _detailsText.value = "詳細"
    }
}