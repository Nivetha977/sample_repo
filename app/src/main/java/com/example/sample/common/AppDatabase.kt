package com.example.sample.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sample.db.StoriesDao
import com.example.sample.ui.stories.model.StoriesData

/**
 *Created by Nivetha S on 14-11-2021.
 */
@Database(entities = [StoriesData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getStoriesDao(): StoriesDao

}