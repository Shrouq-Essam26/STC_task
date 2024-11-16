package com.example.mysteryShopper.presentation.ui.activities.home

import CharacterAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysteryShopper.R
import com.example.mysteryShopper.core.Status
import com.example.mysteryShopper.databinding.ActivityHomeBinding
import com.example.mysteryShopper.presentation.ui.activities.details.DetailsActivity
import com.example.mysteryShopper.presentation.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)
    private lateinit var characterAdapter: CharacterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        viewModel.loadCharacters()
    }

    private fun setupRecyclerView() {
        characterAdapter = CharacterAdapter { character ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("character", character)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)        }

        binding.recyclerViewCharacters.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = characterAdapter
        }

        binding.recyclerViewCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading && firstVisibleItemPosition + visibleItemCount >= totalItemCount) {
                    characterAdapter.showLoadingFooter(true)
                    viewModel.loadCharacters()
                }
            }
        })
    }

    private fun setupObservers() {
        viewModel.characters.observe(this) { resource ->
            when (resource?.status) {
                Status.LOADING -> {
                    if (viewModel.currentPage == 0) dialog.show()
                }
                Status.SUCCESS -> {
                    dialog.dismiss()
                    characterAdapter.submitList(resource.data)
                    characterAdapter.showLoadingFooter(false)
                }
                Status.ERROR -> {
                    dialog.dismiss()
                    characterAdapter.showLoadingFooter(false)
                    Toast.makeText(this, "Error: ${resource.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
