package lingga.app.footballleague.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lingga.app.footballleague.databinding.ListTeamsBinding
import lingga.app.footballleague.model.Teams

class TeamsAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Teams, TeamsAdapter.TeamsViewHolder>(DiffcallbackTeams) {
    class TeamsViewHolder(private var binding: ListTeamsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teams: Teams) {
            binding.teams = teams
            binding.executePendingBindings()
        }
    }

    companion object DiffcallbackTeams : DiffUtil.ItemCallback<Teams>() {
        override fun areItemsTheSame(oldItem: Teams, newItem: Teams): Boolean {
            return oldItem.idTeam === newItem.idTeam
        }

        override fun areContentsTheSame(oldItem: Teams, newItem: Teams): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamsViewHolder {
        return TeamsViewHolder(ListTeamsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val teams = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(teams)
        }
        holder.bind(teams)
    }

    class OnClickListener(val clickListener: (teams: Teams) -> Unit) {
        fun onClick(teams: Teams) = clickListener(teams)
    }
}