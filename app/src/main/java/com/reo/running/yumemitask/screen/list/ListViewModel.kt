package com.reo.running.yumemitask.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.reo.running.yumemitask.R

class ListViewModel : ViewModel() {
    private var _textViewTitle: MutableLiveData<String> =
        MutableLiveData<String>().also { mutableLiveData ->
            mutableLiveData.value = "ここへリストが表示されます"
        }
    val textViewTitle: LiveData<String>
        get() = _textViewTitle

    private var _buttonViewTitle: MutableLiveData<String> =
        MutableLiveData<String>().also { mutableLiveData ->
            mutableLiveData.value = "詳細へ"
        }
    val buttonViewTitle: LiveData<String>
        get() = _buttonViewTitle
}