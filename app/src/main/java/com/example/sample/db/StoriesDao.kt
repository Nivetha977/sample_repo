package com.example.sample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sample.network.Resource
import com.example.sample.ui.stories.model.StoriesData

/**
 *Created by Nivetha S on 14-11-2021.
 */
@Dao
interface StoriesDao {

    @Insert
    fun insert(storiesData: StoriesData)

    @Query("SELECT * FROM stories_data")
    fun getStories(): List<StoriesData>

    @Query("DELETE FROM stories_data")
    fun deleteAll()

}