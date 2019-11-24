package lingga.app.footballleague.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lingga.app.footballleague.databinding.ListLeagueBinding
import lingga.app.footballleague.model.League

class LeagueAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<League, LeagueAdapter.LeagueViewHolder>(Diffcalback) {

    class LeagueViewHolder(private var binding: ListLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(league: League) {
            binding.league = league
            binding.executePendingBindings()
        }
    }

    companion object Diffcalback : DiffUtil.ItemCallback<League>() {
        override fun areItemsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem.idLeague === newItem.idLeague
        }

        override fun areContentsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeagueViewHolder {
        return LeagueViewHolder(ListLeagueBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val league = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(league)
        }
        holder.bind(league)
    }

    class OnClickListener(val clickListener: (league: League) -> Unit) {
        fun onClick(league: League) = clickListener(league)
    }
}