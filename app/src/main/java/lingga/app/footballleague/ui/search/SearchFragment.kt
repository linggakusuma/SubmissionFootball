package lingga.app.footballleague.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.R
import lingga.app.footballleague.adapter.EventAdapter
import lingga.app.footballleague.databinding.SearchFragmentBinding
import lingga.app.footballleague.model.Favorites

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SearchFragmentBinding.inflate(inflater)
        val query = arguments?.let { SearchFragmentArgs.fromBundle(it).quert }
        val application = requireNotNull(this.activity).application
        val viewModelFactory = query?.let { SearchViewModelFactory(it, application) }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewSearch.adapter = EventAdapter(EventAdapter.OnClickListener {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailMatchFragment2(
                    it.idEvent.toString(),
                    it.strEvent.toString(),
                    Favorites.TYPE_LAST
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
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentSelf(query))
                return true
            }

        })
    }
}