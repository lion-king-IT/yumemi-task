package com.reo.running.yumemitask.model.room

import androidx.room.*
import com.reo.running.yumemitask.model.Contributor

@Database(entities = [Contributor::class], version = 1)
abstract class ContributorsDatabase : RoomDatabase() {
    abstract fun contributorsDao(): ContributorsDataDao
}

@Entity
data class Contributor(
    @PrimaryKey
    val id: String,
    val login: String,
    val node_id: String,
    val avatar_url: String,
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
    val site_admin: String,
    val contributions: Int,
)

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