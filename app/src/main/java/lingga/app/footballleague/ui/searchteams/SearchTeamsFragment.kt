package lingga.app.footballleague.ui.searchteams

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.R
import lingga.app.footballleague.adapter.TeamsAdapter
import lingga.app.footballleague.databinding.SearchTeamsFragmentBinding

class SearchTeamsFragment : Fragment() {

    private lateinit var viewModel: SearchTeamsViewModel
    private lateinit var id: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SearchTeamsFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        id = arguments?.let { SearchTeamsFragmentArgs.fromBundle(it).id }.toString()
        val query = arguments?.let { SearchTeamsFragmentArgs.fromBundle(it).query }
        val viewModelFactory =
            query?.let { SearchTeamsViewModelFactory(it, application, id) }
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SearchTeamsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewSearchTeams.adapter = TeamsAdapter(TeamsAdapter.OnClickListener {
            findNavController().navigate(
                SearchTeamsFragmentDirections.actionSearchTeamsFragmentToDetailTeamFragment(
                    it.idTeam.toString(),
                    it.strTeam.toString(),
                    false
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
                        SearchTeamsFragmentDirections.actionSearchTeamsFragmentSelf(
                            query, id
                        )
                    )

                return true
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionFavorites) {
            findNavController().navigate(SearchTeamsFragmentDirections.actionSearchTeamsFragmentToFavoritesFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}
