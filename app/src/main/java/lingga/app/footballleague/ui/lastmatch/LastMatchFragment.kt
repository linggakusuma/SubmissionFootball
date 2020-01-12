package lingga.app.footballleague.ui.lastmatch

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.Navigation2Directions
import lingga.app.footballleague.R
import lingga.app.footballleague.adapter.EventAdapter
import lingga.app.footballleague.databinding.LastMatchFragmentBinding
import lingga.app.footballleague.model.Favorites
import lingga.app.footballleague.ui.detailleague.DetailLeagueFragmentDirections

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
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
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
                        LastMatchFragmentDirections.actionGlobalSearchFragment(
                            query
                        )
                    )

                return true
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionFavorites) {
            findNavController().navigate(DetailLeagueFragmentDirections.actionGlobalFavoritesFragment2())
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
