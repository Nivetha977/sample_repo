package com.example.sample.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.sample.common.AppDatabase
import dagger.hilt.android.HiltAndroidApp

/**
 *Created by Nivetha S on 10-11-2021.
 */
@HiltAndroidApp
class App : Application() {

    companion object {
         var mAppDatabase: AppDatabase? = null
        lateinit var mInstance: App
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mAppDatabase = initializeRoomDb(this, "stories")

    }

    fun initializeRoomDb(context: Context, dbName: String): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .allowMainThreadQueries()
            .build()
    }
}