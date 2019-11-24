package lingga.app.footballleague.ui.lastmatch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import lingga.app.footballleague.R

class LastMatchFragment : Fragment() {

    companion object {
        fun newInstance() = LastMatchFragment()
    }

    private lateinit var viewModel: LastMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.last_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LastMatchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
