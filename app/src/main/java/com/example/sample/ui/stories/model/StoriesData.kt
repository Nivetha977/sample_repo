package com.example.sample.ui.stories.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 *Created by Nivetha S on 14-11-2021.
 */
@Entity(tableName = "stories_data")
class StoriesData {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var title: String = ""

    @SerializedName("by")
    var authorName: String = ""
}