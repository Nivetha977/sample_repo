package com.example.sample.network

import com.example.sample.ui.stories.model.StoriesData
import retrofit2.Response
import javax.inject.Inject

/**
 *Created by Nivetha S on 14-11-2021.
 */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getStories(): Response<List<Long>> = apiService.getStories()

    suspend fun getStoriesData(id: Long): Response<StoriesData> = apiService.getStoriesData(id)
}