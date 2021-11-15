package com.example.sample.network

import com.example.sample.ui.stories.model.StoriesData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *Created by Nivetha S on 10-11-2021.
 */
interface ApiService {

    @GET(ApiNames.GET.STORIES)
    suspend fun getStories(): Response<List<Long>>

    @GET(ApiNames.GET.STORIES_DATA)
    suspend fun getStoriesData(@Path("id") id: Long): Response<StoriesData>

}