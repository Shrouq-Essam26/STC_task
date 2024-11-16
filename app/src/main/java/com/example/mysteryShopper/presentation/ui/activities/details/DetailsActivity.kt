package com.example.mysteryShopper.presentation.ui.activities.details

import CharacterAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysteryShopper.core.Status
import com.example.mysteryShopper.data.model.CharacterModel
import com.example.mysteryShopper.databinding.ActivityDetailsBinding
import com.example.mysteryShopper.databinding.ActivityHomeBinding
import com.example.mysteryShopper.presentation.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {
    private val viewModel: DetailsViewModel by viewModels()

    override fun getViewBinding() = ActivityDetailsBinding.inflate(layoutInflater)
    private lateinit var characterAdapter: CharacterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // Retrieve CharacterModel from Intent
        val character = intent.getSerializableExtra("character") as CharacterModel
        Log.e("Chacter", character.toString())

        if (character != null) {
            // Load data into views
            Glide.with(this)
                .load(character.thumbnail.imageUrl.replace("http://", "https://"))
                .into(binding.characterImageView)

            binding.characterName.text = character.name
            binding.characterDescription.text = character.description ?: "No description available."

            viewModel.fetchSection(character.resourceURI.replace("http://", "https://"), "comics")
            viewModel.fetchSection(character.resourceURI.replace("http://", "https://"), "series")
            viewModel.fetchSection(character.resourceURI.replace("http://", "https://"), "stories")
            viewModel.fetchSection(character.resourceURI.replace("http://", "https://"), "events")
        }
        setupRecyclerViews()
        setupObservers()
    }

    private fun setupRecyclerViews() {
        binding.comicsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.seriesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.storiesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.eventsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupObservers() {
        viewModel.comics.observe(this) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { data ->
                        if (!data.isNullOrEmpty()) {
                            binding.comicsRecyclerView.adapter = SectionAdapter(data)
                            binding.comicsTitle.visibility = View.VISIBLE
                        }
                    }
                }

                Status.ERROR -> showToast("Failed to load comics: ${resource.message}")
                Status.LOADING -> showToast("Loading comics...")
            }
        }

        viewModel.series.observe(this) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { data ->
                        if (!data.isNullOrEmpty()) {
                            binding.seriesRecyclerView.adapter = SectionAdapter(data)
                            binding.seriesTitle.visibility = View.VISIBLE
                        }

                    }
                }

                Status.ERROR -> showToast("Failed to load series: ${resource.message}")
                Status.LOADING -> showToast("Loading series...")
            }
        }

        viewModel.stories.observe(this) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { data ->
                        if (!data.isNullOrEmpty()) {
                            binding.storiesRecyclerView.adapter = SectionAdapter(data)
                            binding.storiesTitle.visibility = View.VISIBLE
                        }
                    }
                }

                Status.ERROR -> showToast("Failed to load stories: ${resource.message}")
                Status.LOADING -> showToast("Loading stories...")
            }
        }

        viewModel.events.observe(this) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { data ->
                        if (!data.isNullOrEmpty()) {
                            binding.eventsRecyclerView.adapter = SectionAdapter(data)
                            binding.eventsTitle.visibility = View.VISIBLE
                        }
                    }
                }

                Status.ERROR -> showToast("Failed to load events: ${resource.message}")
                Status.LOADING -> showToast("Loading events...")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
