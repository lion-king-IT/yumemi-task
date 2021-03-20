package com.reo.running.yumemitask.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reo.running.yumemitask.GithubAPI.Github
import com.reo.running.yumemitask.GithubAPI.Repository

class ListViewModel(private val repository: Repository) : ViewModel() {
    val repositoryList: LiveData<List<Github>?> = repository.getRepository()

    companion object {
        class Facroty(private val repository: Repository = Repository()): ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ListViewModel(repository) as T
        }
    }
}