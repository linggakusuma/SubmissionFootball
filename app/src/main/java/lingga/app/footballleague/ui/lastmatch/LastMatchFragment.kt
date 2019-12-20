package lingga.app.footballleague.ui.lastmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.Navigation2Directions
import lingga.app.footballleague.adapter.EventAdapter
import lingga.app.footballleague.databinding.LastMatchFragmentBinding
import lingga.app.footballleague.model.Favorites

class LastMatchFragment : Fragment() {

    private lateinit var viewModel: LastMatchViewModel

    companion object {
        const val EXTRA_ID = "id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LastMatchFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val id = arguments?.getString(EXTRA_ID)
        val viewModelFactory = id?.let { LastMatchViewModelFactory(it, application) }
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LastMatchViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewEvent.adapter = EventAdapter(EventAdapter.OnClickListener {
            findNavController().navigate(
                Navigation2Directions.actionGlobalDetailMatchFragment2(
                    it.idEvent.toString(),
                    it.strEvent.toString(),
                    Favorites.TYPE_LAST
                )
            )
        })

        binding.recyclerViewEventNextMatch.adapter = EventAdapter(EventAdapter.OnClickListener {
            findNavController().navigate(
                Navigation2Directions.actionGlobalDetailMatchFragment2(
                    it.idEvent.toString(),
                    it.strEvent.toString(),
                    Favorites.TYPE_NEXT
                )
            )
        })

        return binding.root
    }
}
