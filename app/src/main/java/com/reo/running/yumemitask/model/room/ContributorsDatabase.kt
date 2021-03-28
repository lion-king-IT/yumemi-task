package com.reo.running.yumemitask.model.room

import androidx.room.*
import com.reo.running.yumemitask.model.Github

@Database(entities = [ContributorsData::class], version = 1)
abstract class ContributorsDatabase : RoomDatabase() {
    abstract fun contributorsDao(): ContributorsDataDao
}

@Dao
interface ContributorsDataDao {
    @Query("Select * From contributorsData")
    suspend fun getAll(): List<ContributorsData>

    @Insert
    suspend fun insertContributors(contributors: ContributorsData)

    @Update
    suspend fun updateContributors(contributors: List<ContributorsData>)

    @Delete
    suspend fun deleteContributors(contributors: List<ContributorsData>)
}