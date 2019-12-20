package lingga.app.footballleague.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lingga.app.footballleague.databinding.ListStandingsBinding
import lingga.app.footballleague.model.Standings

class StandingsAdapter :
    ListAdapter<Standings, StandingsAdapter.StandingsViewHolder>(DiffcallbackStandings) {
    class StandingsViewHolder(private var binding: ListStandingsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(standings: Standings) {
            binding.standings = standings
            binding.executePendingBindings()
        }
    }

    companion object DiffcallbackStandings : DiffUtil.ItemCallback<Standings>() {
        override fun areItemsTheSame(oldItem: Standings, newItem: Standings): Boolean {
            return oldItem.teamid === newItem.teamid
        }

        override fun areContentsTheSame(oldItem: Standings, newItem: Standings): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StandingsViewHolder {
        return StandingsViewHolder(ListStandingsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        val standings = getItem(position)
        holder.bind(standings)
    }
}