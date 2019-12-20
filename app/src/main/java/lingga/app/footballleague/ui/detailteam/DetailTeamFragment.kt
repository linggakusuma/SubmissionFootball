package lingga.app.footballleague.ui.detailteam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import lingga.app.footballleague.databinding.DetailTeamFragmentBinding

class DetailTeamFragment : Fragment() {

    private lateinit var viewModel: DetailTeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailTeamFragmentBinding.inflate(inflater)
        val id = arguments?.let { DetailTeamFragmentArgs.fromBundle(it).id }
        val viewModelFactory = id?.let { DetailTeamViewModelFactory(it) }
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailTeamViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
