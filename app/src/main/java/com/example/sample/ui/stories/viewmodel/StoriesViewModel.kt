package com.example.sample.ui.stories.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sample.app.App
import com.example.sample.network.Resource
import com.example.sample.ui.stories.model.StoriesData
import com.example.sample.ui.stories.repository.StoriesRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 *Created by Nivetha S on 14-11-2021.
 */
@HiltViewModel
class StoriesViewModel @Inject constructor(private val storiesRepository: StoriesRepository) :
    ViewModel() {

    private val _storiesResponse = MutableLiveData<List<StoriesData>>()
    val storiesListDB: LiveData<List<StoriesData>>
        get() = _storiesResponse

    private val _storiesUiState = MutableLiveData<Resource<StoriesData>>()
    val storiesUiState: LiveData<Resource<StoriesData>>
        get() = _storiesUiState


    fun initiateRefresh() {
        App.mAppDatabase?.getStoriesDao()?.deleteAll()
        showStories()
    }


    fun showStories() {
        CoroutineScope(Dispatchers.IO).launch {
            _storiesUiState.postValue(Resource.loading(null))
            if (App.mAppDatabase?.getStoriesDao()?.getStories()!!.isNotEmpty()) {
                delay(1000)
                _storiesResponse.postValue(App.mAppDatabase!!.getStoriesDao().getStories())
            } else {
                val listOfStories = storiesRepository.getStories()
                if (listOfStories.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).async {
                        for (i in listOfStories!!.body()!!.indices) {
                            val listOfStoriesData =
                                storiesRepository.getStoriesData(listOfStories.body()!![i]).body()
                            insertDataBase(listOfStoriesData)
                        }

                        _storiesResponse.postValue(App.mAppDatabase!!.getStoriesDao().getStories())
                    }

                } else {
                    _storiesUiState.postValue(
                        Resource.error(
                            listOfStories.errorBody().toString(),
                            null
                        )
                    )
                }


            }
        }

    }

    private fun insertDataBase(body: StoriesData?) {
        var storiesData = StoriesData()
        storiesData.title = body?.title.toString()
        storiesData.authorName = body?.authorName.toString()
        App.mAppDatabase!!.getStoriesDao().insert(storiesData)
    }


}