package com.reo.running.yumemitask

import android.app.Application
import androidx.room.Room
import com.reo.running.yumemitask.model.room.ContributorsDatabase

class YumemiApplication : Application() {
    companion object {
        lateinit var db: ContributorsDatabase
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            ContributorsDatabase::class.java,
            "contributors-database"
        ).build()
    }
}