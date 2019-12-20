package lingga.app.footballleague.ui.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import lingga.app.footballleague.adapter.StandingsAdapter
import lingga.app.footballleague.databinding.StandingsFragmentBinding
import lingga.app.footballleague.ui.lastmatch.LastMatchFragment

class StandingsFragment : Fragment() {

    private lateinit var viewModel: StandingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = StandingsFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val id = arguments?.getString(LastMatchFragment.EXTRA_ID)
        val viewModelFactory = id?.let { StandingsViewModelFactory(it, application) }
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(StandingsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewStandings.adapter = StandingsAdapter()

        return binding.root
    }
}
