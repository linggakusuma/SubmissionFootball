package lingga.app.footballleague.ui.league

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.R
import lingga.app.footballleague.adapter.LeagueAdapter
import lingga.app.footballleague.databinding.LeagueFragmentBinding

class LeagueFragment : Fragment() {

    private lateinit var viewModel: LeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LeagueFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val leagueViewModelFactory = LeagueViewModelFactory(application)
        viewModel =
            ViewModelProviders.of(this, leagueViewModelFactory).get(LeagueViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerViewLeague.adapter = LeagueAdapter(LeagueAdapter.OnClickListener {

            findNavController()
                .navigate(
                    LeagueFragmentDirections.actionLeagueFragmentToNavigation22(
                        it.strLeague.toString(),
                        it
                    )
                )
        })
        setHasOptionsMenu(true)
        return binding.root
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
                        LeagueFragmentDirections.actionLeagueFragmentToSearchFragment(
                            query
                        )
                    )
                return true
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionFavorites) {
            findNavController().navigate(LeagueFragmentDirections.actionLeagueFragmentToFavoritesFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}