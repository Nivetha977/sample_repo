package com.example.sample.ui.stories.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample.R
import com.example.sample.app.App
import com.example.sample.common.showToast
import com.example.sample.databinding.ActivityMainBinding
import com.example.sample.network.Status
import com.example.sample.ui.stories.adapter.StoriesAdapter
import com.example.sample.ui.stories.model.StoriesData
import com.example.sample.ui.stories.viewmodel.StoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val storiesViewModel: StoriesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    var resId = R.anim.recyclerview_layout_animation
    var storiesAdapter: StoriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var animation = AnimationUtils.loadLayoutAnimation(this, resId)
        binding.swipeContainer.setOnRefreshListener(OnRefreshListener { // Your code to refresh the list here.
            storiesViewModel.initiateRefresh()
        })


        storiesViewModel.showStories()
        storiesViewModel.storiesListDB.observe(this, Observer {
            if (binding.swipeContainer.isRefreshing) {
                binding.swipeContainer.isRefreshing = false
            }
            binding.progressBar.visibility = View.GONE
            val layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rvStories.layoutManager = layoutManager
            binding.rvStories.layoutAnimation = animation
            storiesAdapter = StoriesAdapter(it)
            binding.rvStories.adapter = storiesAdapter
            binding.rvStories.scheduleLayoutAnimation()


        })

        storiesViewModel.storiesUiState.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(resources.getString(R.string.msg_something_went_wrong))
                }
            }
        })

        binding.editTextSearch.doAfterTextChanged {
            storiesAdapter?.filter?.filter(it.toString())
        }

    }
}