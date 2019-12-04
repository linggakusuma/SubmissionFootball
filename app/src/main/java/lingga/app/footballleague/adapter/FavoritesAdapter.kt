package lingga.app.footballleague.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lingga.app.footballleague.databinding.ListFavoriteBinding
import lingga.app.footballleague.model.Favorites

class FavoritesAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Favorites, FavoritesAdapter.FavoritesViewHolder>(DiffcallbackFavorite) {
    class FavoritesViewHolder(private var binding: ListFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorites: Favorites) {
            binding.event = favorites
            binding.executePendingBindings()
        }
    }

    companion object DiffcallbackFavorite : DiffUtil.ItemCallback<Favorites>() {
        override fun areItemsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Favorites, newItem: Favorites): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesViewHolder {
        return FavoritesViewHolder(ListFavoriteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val favorites = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(favorites)
        }
        holder.bind(favorites)
    }

    class OnClickListener(val clickListener: (favorites: Favorites) -> Unit) {
        fun onClick(favorites: Favorites) = clickListener(favorites)
    }
}