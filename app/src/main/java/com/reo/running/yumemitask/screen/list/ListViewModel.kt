package com.reo.running.yumemitask.screen.list

import androidx.lifecycle.*
import com.reo.running.yumemitask.model.Contributor
import com.reo.running.yumemitask.model.ContributorsRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: ContributorsRepository
) : ViewModel() {
    val contributorsList: LiveData<List<Contributor>?> = repository.getRepository()

    private val _selectedContributor = MutableLiveData<Contributor>()
    val selectedContributor: LiveData<Contributor> = _selectedContributor

    fun selectContributor(position: Int) {
        _selectedContributor.value = contributorsList.value?.get(position)
        viewModelScope.launch {
            contributorsList.value?.get(position)?.run {
                repository.saveHistory(this)
            }
        }
    }

    companion object {
        class Facroty(private val repository: ContributorsRepository = ContributorsRepository()
        ) : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                ListViewModel(repository) as T
        }
    }
}