package lingga.app.footballleague.ui.favorites.lastmatchfavorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.adapter.FavoritesAdapter
import lingga.app.footballleague.databinding.LastMatchFavoriteFragmentBinding
import lingga.app.footballleague.model.Favorites
import lingga.app.footballleague.ui.favorites.FavoritesFragmentDirections

class LastMatchFavoriteFragment : Fragment() {

    private lateinit var viewModel: LastMatchFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LastMatchFavoriteFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = LastMatchFavoriteViewModelFactory(application, context as Context)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LastMatchFavoriteViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewLastFavorite.adapter =
            FavoritesAdapter(FavoritesAdapter.OnClickListener {
                findNavController().navigate(
                    FavoritesFragmentDirections.actionFavoritesFragmentToDetailMatchFragment2(
                        it.idEvent.toString(),
                        it.strEvent.toString(),
                        Favorites.TYPE_LAST
                    )
                )
            })
        return binding.root
    }

}
