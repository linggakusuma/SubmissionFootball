package lingga.app.footballleague.ui.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.Navigation2Directions
import lingga.app.footballleague.adapter.TeamsAdapter
import lingga.app.footballleague.databinding.TeamsFragmentBinding
import lingga.app.footballleague.ui.lastmatch.LastMatchFragment

class TeamsFragment : Fragment() {

    private lateinit var viewModel: TeamsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = TeamsFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val id = arguments?.getString(LastMatchFragment.EXTRA_ID)
        val viewModelFactory = id?.let { TeamsViewModelFactory(it, application) }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewTeams.adapter = TeamsAdapter(TeamsAdapter.OnClickListener {
            findNavController().navigate(
                Navigation2Directions.actionGlobalDetailTeamFragment(
                    it.idTeam.toString(),
                    it.strTeam.toString(),
                    false
                )
            )
        })
        return binding.root
    }

}
