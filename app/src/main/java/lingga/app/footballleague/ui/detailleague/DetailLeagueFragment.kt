package lingga.app.footballleague.ui.detailleague

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.detail_league_fragment.*
import lingga.app.footballleague.Navigation2Directions
import lingga.app.footballleague.R
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item, menu)
        val searchView =
            menu.findItem(R.id.search).actionView as androidx.appcompat.widget.SearchView
        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                findNavController()
                    .navigate(
                        Navigation2Directions.actionGlobalSearchTeamsFragment(
                            query, detailLeague
                        )
                    )

                return true
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionFavorites) {
            findNavController().navigate(Navigation2Directions.actionGlobalFavoritesFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}