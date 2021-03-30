package com.reo.running.yumemitask.screen.history

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.PrimaryKey
import com.reo.running.yumemitask.model.Contributor
import com.reo.running.yumemitask.model.ContributorsRepository
import com.reo.running.yumemitask.model.room.ContributorsDataDao
import com.reo.running.yumemitask.model.room.ContributorsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel(
    private val repository: ContributorsRepository,
) : ViewModel() {
    val contributorsList: LiveData<List<Contributor>?> = repository.getRepository()

    private val _selectedHistory = MutableLiveData<Contributor>()
    val selectedHistory: LiveData<Contributor> = _selectedHistory

    fun displayHistory(position: Int): MutableList<String> {
        val historyList: MutableList<String> = mutableListOf()
        _selectedHistory.value = contributorsList.value?.get(position)
        viewModelScope.launch {
            val lastIndex = repository.getLastIndex()
            contributorsList.value?.get(position)?.run {

                repository.getHistory().let {
                    for (i in lastIndex downTo 0 step 1) {
                        historyList.add(it[i].login)
                    }
                }
            }
        }
        return historyList
    }

    fun selectHistory()

    companion object {
        class Factory(
            private val repository: ContributorsRepository = ContributorsRepository()
        ) : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                HistoryViewModel(repository) as T
        }
    }
}