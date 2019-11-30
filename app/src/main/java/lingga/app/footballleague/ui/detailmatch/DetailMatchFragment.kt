package lingga.app.footballleague.ui.detailmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import lingga.app.footballleague.databinding.DetailMatchFragmentBinding

class DetailMatchFragment : Fragment() {

    private lateinit var viewModel: DetailMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailMatchFragmentBinding.inflate(inflater)
        val id = DetailMatchFragmentArgs.fromBundle(arguments!!).id
        val viewModelFactory = DetailMatchViewModelFactory(id)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailMatchViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}
