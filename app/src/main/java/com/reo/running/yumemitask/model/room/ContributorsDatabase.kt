package com.reo.running.yumemitask.model.room

import androidx.room.*
import com.reo.running.yumemitask.model.Github

@Database(entities = [ContributorsData::class],version = 1)
abstract class ContributorsDatabase : RoomDatabase() {
    abstract fun contributorsDao(): ContributorsDataDao
}

@Entity
data class ContributorsData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val login: String,
    val node_id: String, val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean,
    val contributions: Int,
)

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