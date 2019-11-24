package lingga.app.footballleague.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.adapter.LeagueAdapter
import lingga.app.footballleague.databinding.LeagueFragmentBinding

class LeagueFragment : Fragment() {

    private lateinit var viewModel: LeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LeagueFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val leagueViewModelFactory = LeagueViewModelFactory(application)
        viewModel =
            ViewModelProviders.of(this, leagueViewModelFactory).get(LeagueViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerViewLeague.adapter = LeagueAdapter(LeagueAdapter.OnClickListener {
            this.findNavController()
                .navigate(
                    LeagueFragmentDirections.actionLeagueFragmentToDetailLeagueFragment(
                        it.strLeague.toString(),
                        it
                    )
                )
        })
        return binding.root
    }
}