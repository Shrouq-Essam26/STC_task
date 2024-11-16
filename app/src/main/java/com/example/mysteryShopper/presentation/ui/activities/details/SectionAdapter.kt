package com.example.mysteryShopper.presentation.ui.activities.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mysteryShopper.data.model.SectionModel
import com.example.mysteryShopper.databinding.ItemHorizontalCardBinding
import com.example.mysteryShopper.databinding.ListItemCharacterBinding

class SectionAdapter ( private val items: List<SectionModel> , private val onItemClicked: (SectionModel) -> Unit ): RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHorizontalCardBinding.inflate(layoutInflater, parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

  inner  class SectionViewHolder(private val binding: ItemHorizontalCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(section: SectionModel) {
            binding.itemTitle.text = section.title
            Glide.with(itemView.context)
                .load(section.thumbnailUrl.replace("http://", "https://"))
                .placeholder(com.example.mysteryShopper.R.drawable.ic_launcher_foreground) // Add placeholder drawable
                .error(com.example.mysteryShopper.R.drawable.ic_launcher_foreground)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache for smooth scrolling
                .into(binding.itemImage)

            binding.root.setOnClickListener {
                onItemClicked(section)
            }
        }
    }
}