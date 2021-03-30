package com.reo.running.yumemitask.model.room

import androidx.room.*
import com.reo.running.yumemitask.model.Contributor

@Database(entities = [Contributor::class], version = 1)
abstract class ContributorsDatabase : RoomDatabase() {
    abstract fun contributorsDao(): ContributorsDataDao
}


@Dao
interface ContributorsDataDao {
    @Query("Select * From contributor")
    suspend fun getAll(): List<Contributor>

    @Insert
    suspend fun insertContributors(contributors: Contributor)

    @Update
    suspend fun updateContributors(contributors: List<Contributor>)

    @Delete
    suspend fun deleteContributors(contributors: List<Contributor>)
}