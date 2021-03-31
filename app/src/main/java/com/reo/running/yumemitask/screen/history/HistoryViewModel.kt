package com.reo.running.yumemitask.screen.history

import androidx.lifecycle.*
import com.reo.running.yumemitask.model.Contributor
import com.reo.running.yumemitask.model.ContributorsRepository

class HistoryViewModel(
    private val repository: ContributorsRepository
) : ViewModel() {
    val contributorsList: LiveData<List<Contributor>> = repository.getHistory()

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