package lingga.app.footballleague.ui.detailteam

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import lingga.app.footballleague.R
import lingga.app.footballleague.databinding.DetailTeamFragmentBinding
import lingga.app.footballleague.db.FavoriteRoomDatabase

class DetailTeamFragment : Fragment() {

    private lateinit var viewModel: DetailTeamViewModel
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailTeamFragmentBinding.inflate(inflater)
        val id = arguments?.let { DetailTeamFragmentArgs.fromBundle(it).id }
        val application = requireNotNull(this.activity).application
        val data = FavoriteRoomDatabase.getInstance(application).favoriteDao
        isFavorite = arguments?.let { DetailTeamFragmentArgs.fromBundle(it).favorite }!!
        val viewModelFactory =
            id?.let { DetailTeamViewModelFactory(it, data, application) }
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailTeamViewModel::class.java)

        binding.viewModel = viewModel
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
        if (item.itemId == R.id.favorites) {
            if (isFavorite) {
                viewModel.removeFavorite()
            } else {
                viewModel.saveFavorite()
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