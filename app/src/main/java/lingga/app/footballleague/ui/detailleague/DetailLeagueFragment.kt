package lingga.app.footballleague.ui.detailleague

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.detail_league_fragment.*
import lingga.app.footballleague.adapter.ViewPagerAdapter
import lingga.app.footballleague.databinding.DetailLeagueFragmentBinding
import lingga.app.footballleague.ui.lastmatch.LastMatchFragment

class DetailLeagueFragment : Fragment() {

    private lateinit var viewModel: DetailLeagueViewModel
    private lateinit var detailLeague: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailLeagueFragmentBinding.inflate(inflater)
        detailLeague =
            arguments?.let { DetailLeagueFragmentArgs.fromBundle(it).league.idLeague }.toString()
        val viewModelFactory = DetailLeagueViewModelFactory(detailLeague)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailLeagueViewModel::class.java)
        val bundle = Bundle()
        bundle.putString(
            LastMatchFragment.EXTRA_ID,
            arguments?.let { DetailLeagueFragmentArgs.fromBundle(it).league.idLeague }
        )
        val pagerAdapter = ViewPagerAdapter(fragmentManager as FragmentManager, bundle)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val bundle = Bundle()
        bundle.putString(
            LastMatchFragment.EXTRA_ID,
            arguments?.let { DetailLeagueFragmentArgs.fromBundle(it).league.idLeague }
        )
        val pagerAdapter = ViewPagerAdapter(fragmentManager as FragmentManager, bundle)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}