package lingga.app.footballleague.ui.detailleague

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import lingga.app.footballleague.databinding.DetailLeagueFragmentBinding

class DetailLeagueFragment : Fragment() {
    private lateinit var viewModel: DetailLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailLeagueFragmentBinding.inflate(inflater)
        val detailLeague =
            arguments?.let { DetailLeagueFragmentArgs.fromBundle(it).league.idLeague }
        val viewModelFactory = detailLeague?.let { DetailLeagueViewModelFactory(it) }
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailLeagueViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
