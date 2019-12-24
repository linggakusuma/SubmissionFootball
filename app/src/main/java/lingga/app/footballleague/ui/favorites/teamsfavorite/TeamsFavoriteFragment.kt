package lingga.app.footballleague.ui.favorites.teamsfavorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.adapter.TeamsAdapter
import lingga.app.footballleague.databinding.TeamsFavoriteFragmentBinding
import lingga.app.footballleague.db.FavoriteRoomDatabase
import lingga.app.footballleague.ui.favorites.FavoritesFragmentDirections

class TeamsFavoriteFragment : Fragment() {

    private lateinit var viewModel: TeamsFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = TeamsFavoriteFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val data = FavoriteRoomDatabase.getInstance(application).favoriteDao
        val viewModelFactory = TeamsFavoriteViewModelFactory(data, application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(TeamsFavoriteViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewTeamsFavorite.adapter = TeamsAdapter(TeamsAdapter.OnClickListener {
            findNavController().navigate(
                FavoritesFragmentDirections.actionFavoritesFragmentToDetailTeamFragment(
                    it.idTeam.toString(),
                    it.strTeam.toString(),
                    true
                )
            )
        })
        return binding.root
    }
}
