package lingga.app.footballleague.ui.favorites.nextmatchfavorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import lingga.app.footballleague.adapter.FavoritesAdapter
import lingga.app.footballleague.databinding.NextMatchFavoriteFragmentBinding
import lingga.app.footballleague.model.Favorites
import lingga.app.footballleague.ui.favorites.FavoritesFragmentDirections

class NextMatchFavoriteFragment : Fragment() {

    private lateinit var viewModel: NextMatchFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = NextMatchFavoriteFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = NextMatchFavoriteViewModelFactory(application, context as Context)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NextMatchFavoriteViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewLastFavorite.adapter =
            FavoritesAdapter(FavoritesAdapter.OnClickListener {
                findNavController().navigate(
                    FavoritesFragmentDirections.actionFavoritesFragmentToDetailMatchFragment2(
                        it.idEvent.toString(),
                        it.strEvent.toString(),
                        Favorites.TYPE_NEXT
                    )
                )
            })
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.lastFavorite
    }
}
