package com.example.mysteryShopper.presentation.ui.activities.details

import CharacterAdapter
import android.annotation.SuppressLint
import android.os.Bundle
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

        if (character != null) {
            // Load data into views
            Glide.with(this)
                .load(character.thumbnail.imageUrl.replace("http://", "https://"))
                .into(binding.characterImageView)

            binding.characterName.text = character.name
            binding.characterDescription.text = character.description ?: "No description available."
    }

//    private fun setupRecyclerView() {
//        characterAdapter = CharacterAdapter { character ->
//            Toast.makeText(this, "Clicked on: ${character.name}", Toast.LENGTH_SHORT).show()
//        }
//
//        binding.recyclerViewCharacters.apply {
//            layoutManager = LinearLayoutManager(this@HomeActivity)
//            adapter = characterAdapter
//        }
//
//        binding.recyclerViewCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                val visibleItemCount = layoutManager.childCount
//                val totalItemCount = layoutManager.itemCount
//                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//
//                if (!viewModel.isLoading && firstVisibleItemPosition + visibleItemCount >= totalItemCount) {
//                    characterAdapter.showLoadingFooter(true)
//                    viewModel.loadCharacters()
//                }
//            }
//        })
//    }
//
//    private fun setupObservers() {
//        viewModel.characters.observe(this) { resource ->
//            when (resource?.status) {
//                Status.LOADING -> {
//                    if (viewModel.currentPage == 0) dialog.show()
//                }
//                Status.SUCCESS -> {
//                    dialog.dismiss()
//                    characterAdapter.submitList(resource.data)
//                    characterAdapter.showLoadingFooter(false)
//                }
//                Status.ERROR -> {
//                    dialog.dismiss()
//                    characterAdapter.showLoadingFooter(false)
//                    Toast.makeText(this, "Error: ${resource.message}", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
}}
