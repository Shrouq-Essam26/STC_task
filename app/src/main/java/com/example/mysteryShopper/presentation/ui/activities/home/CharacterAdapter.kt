import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mysteryShopper.data.model.CharacterModel
import com.example.mysteryShopper.databinding.ListItemCharacterBinding

class CharacterAdapter(
    private val onClick: (CharacterModel) -> Unit
) : ListAdapter<CharacterModel, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    private var isLoadingFooterVisible: Boolean = false

    fun showLoadingFooter(show: Boolean) {
        isLoadingFooterVisible = show
        notifyItemChanged(itemCount - 1)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (isLoadingFooterVisible) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoadingFooterVisible && position == itemCount - 1) LOADING_VIEW_TYPE else ITEM_VIEW_TYPE
    }

    inner class CharacterViewHolder(private val binding: ListItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterModel?, isLoading: Boolean) {
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.characterImage.visibility = View.GONE
                binding.characterName.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.characterImage.visibility = View.VISIBLE
                binding.characterName.visibility = View.VISIBLE

                // Load character data
                Glide.with(itemView)
                    .load(character?.thumbnail?.imageUrl?.replace("http://", "https://"))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.characterImage)

                binding.characterName.text = character?.name
                itemView.setOnClickListener { onClick(character!!) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        if (position == itemCount - 1 && isLoadingFooterVisible) {
            holder.bind(null, true)
        } else {
            holder.bind(getItem(position), false)
        }
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private const val ITEM_VIEW_TYPE = 0
        private const val LOADING_VIEW_TYPE = 1
    }
}