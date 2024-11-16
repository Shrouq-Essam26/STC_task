package com.example.mysteryShopper.presentation.ui.activities.expandedView

import CharacterAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysteryShopper.core.Status
import com.example.mysteryShopper.data.model.SectionModel
import com.example.mysteryShopper.databinding.ActivityExpandedViewBinding
import com.example.mysteryShopper.databinding.ActivityHomeBinding
import com.example.mysteryShopper.presentation.ui.activities.details.DetailsActivity
import com.example.mysteryShopper.presentation.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class ExpandedViewActivity : BaseActivity<ActivityExpandedViewBinding>() {
    private val viewModel: ExpandedViewViewModel by viewModels()

    override fun getViewBinding() = ActivityExpandedViewBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val section = intent.getSerializableExtra("section") as? SectionModel
        section?.let { setupUI(it) }
    }

    private fun setupUI(section: SectionModel) {
        Glide.with(this)
            .load(section.thumbnailUrl.replace("http://", "https://"))
            .into(binding.expandedImageView)

        binding.expandedTitle.text = section.title

        binding.backButton.setOnClickListener { finish() }
    }
}