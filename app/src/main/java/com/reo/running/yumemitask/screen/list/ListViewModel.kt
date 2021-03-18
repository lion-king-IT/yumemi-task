package com.reo.running.yumemitask.screen.list

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.reo.running.yumemitask.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {
    private var _textViewTitle = MutableLiveData<String>()
    val textViewTitle: LiveData<String>
        get() = _textViewTitle

    private var _buttonViewTitle = MutableLiveData<String>()
    val buttonViewTitle: LiveData<String>
        get() = _buttonViewTitle

    init {
        _textViewTitle.value = "ここにリストが、表示されます"
        _buttonViewTitle.value = "詳細へ"
    }
}