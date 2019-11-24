package lingga.app.footballleague.ui.nextmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import lingga.app.footballleague.adapter.EventAdapter
import lingga.app.footballleague.databinding.NextMatchFragmentBinding
import lingga.app.footballleague.ui.detailleague.DetailLeagueFragmentArgs

class NextMatchFragment : Fragment() {
    private lateinit var viewModel: NextMatchViewModel
    private lateinit var binding: NextMatchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NextMatchFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val idLeague = arguments?.let { DetailLeagueFragmentArgs.fromBundle(it).league.idLeague }
        val viewModelFactory = idLeague?.let { NextMatchViewModelFactory(it, application) }
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NextMatchViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewEvent.adapter = EventAdapter(EventAdapter.OnClickListener {
            Toast.makeText(context, "tes", Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }

}
