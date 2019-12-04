package lingga.app.footballleague.ui.detailmatch

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import lingga.app.footballleague.R
import lingga.app.footballleague.databinding.DetailMatchFragmentBinding

class DetailMatchFragment : Fragment() {

    private lateinit var viewModel: DetailMatchViewModel
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailMatchFragmentBinding.inflate(inflater)
        val id = arguments?.let { DetailMatchFragmentArgs.fromBundle(it).id }
        val viewModelFactory = id?.let { DetailMatchViewModelFactory(it, context as Context) }
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailMatchViewModel::class.java)

        viewModel.isFavorite.observe(this, Observer {
            if (it != null) {
                isFavorite = it
            }
        })

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.favorites_menu, menu)
        menuItem = menu
        setFavorite()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val typeMatch = arguments?.let { DetailMatchFragmentArgs.fromBundle(it).type }
        if (item.itemId == R.id.favorites) {
            if (isFavorite) {
                viewModel.removeFavorite()
            } else {
                if (typeMatch != null) {
                    viewModel.addToFavorite(typeMatch)
                }
            }
            isFavorite = !isFavorite
            setFavorite()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite() {
        if (isFavorite) menuItem?.getItem(0)?.icon =
            ContextCompat.getDrawable(context as Context, R.drawable.ic_favorite_black_24dp)
        else menuItem?.getItem(0)?.icon =
            ContextCompat.getDrawable(context as Context, R.drawable.ic_favorite_border_black_24dp)
    }
}
