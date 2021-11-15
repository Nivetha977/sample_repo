package com.example.sample.ui.stories.repository

import com.example.sample.network.ApiHelperImpl
import com.example.sample.ui.stories.model.StoriesData
import retrofit2.Response
import javax.inject.Inject

/**
 *Created by Nivetha S on 11-11-2021.
 */
class StoriesRepository @Inject constructor(var apiHelperImpl: ApiHelperImpl) {

    suspend fun getStories() = apiHelperImpl.getStories()

    suspend fun getStoriesData(id: Long): Response<StoriesData> = apiHelperImpl.getStoriesData(id)


}