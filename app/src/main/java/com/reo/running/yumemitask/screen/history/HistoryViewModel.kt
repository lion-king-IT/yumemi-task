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
    private val readDao: ContributorsDatabase
) : ViewModel() {
    val contributorsList: LiveData<List<Contributor>?> = repository.getRepository()

    private val _selectedHistory = MutableLiveData<Contributor>()
    val selectedHistory: LiveData<Contributor> = _selectedHistory

    fun selectHistory(position: Int) {
        _selectedHistory.value = contributorsList.value?.get(position)
        viewModelScope.launch {
            contributorsList.value?.get(position)?.run {
                repository.
            }
        }
    }

    fun displayHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val lastIndex = repository.getLastIndex()
            val historyList: MutableList<String> = mutableListOf()
            readDao.contributorsDao().getAll().let {
                for (i in lastIndex downTo 0 step 1) {
                    historyList.add(it[i].login)
                }
            }
            withContext(Dispatchers.Main) {
                var position = 0
                val adapter = HistoryViewAdapter(historyList,position)

            }
        }
    }


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