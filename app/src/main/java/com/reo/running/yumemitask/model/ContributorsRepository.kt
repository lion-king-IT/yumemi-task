package com.reo.running.yumemitask.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.reo.running.yumemitask.YumemiApplication
import com.reo.running.yumemitask.model.room.ContributorsDataDao

class ContributorsRepository(
    private val remoteDataSource: RemoteDataSource = RemoteDataSource(),
    private val contributorsDataDao: ContributorsDataDao = YumemiApplication.db.contributorsDao()
) {
    fun getRepository(): LiveData<List<Contributor>?> {
        return liveData {
            val repos = remoteDataSource.getRepository()
            emit(repos)
        }
    }

    suspend fun displayHistory(): Contributor? {
        return contributorsDataDao.getAll().lastOrNull()
    }

    suspend fun saveHistory(contributor: Contributor) {
        contributorsDataDao.insertContributors(contributor)
    }
}