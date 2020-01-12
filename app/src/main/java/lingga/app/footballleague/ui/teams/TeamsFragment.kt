package lingga.app.footballleague.ui.teams

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.Navigation2Directions
import lingga.app.footballleague.R
import lingga.app.footballleague.adapter.TeamsAdapter
import lingga.app.footballleague.databinding.TeamsFragmentBinding
import lingga.app.footballleague.ui.lastmatch.LastMatchFragment

class TeamsFragment : Fragment() {

    private lateinit var viewModel: TeamsViewModel
    private lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = TeamsFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        id = arguments?.getString(LastMatchFragment.EXTRA_ID).toString()
        val viewModelFactory = TeamsViewModelFactory(id, application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewTeams.adapter = TeamsAdapter(TeamsAdapter.OnClickListener {
            findNavController().navigate(
                Navigation2Directions.actionGlobalDetailTeamFragment(
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
                        Navigation2Directions.actionGlobalSearchTeamsFragment(
                            query, id
                        )
                    )

                return true
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionFavorites) {
            findNavController().navigate(Navigation2Directions.actionGlobalFavoritesFragment())
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

