package lingga.app.footballleague.ui.detailleague

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import lingga.app.footballleague.R

class DetailLeagueFragment : Fragment() {

    companion object {
        fun newInstance() = DetailLeagueFragment()
    }

    private lateinit var viewModel: DetailLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_league_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailLeagueViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
