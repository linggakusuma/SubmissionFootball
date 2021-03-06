package lingga.app.footballleague.ui.favorites


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import lingga.app.footballleague.adapter.FavoritePagerAdapter
import lingga.app.footballleague.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFavoritesBinding.inflate(inflater)
        val adapter = FavoritePagerAdapter(fragmentManager as FragmentManager)
        binding.viewPagerFavorites.adapter = adapter
        binding.tabLayoutFavorites.setupWithViewPager(binding.viewPagerFavorites)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val adapter = FavoritePagerAdapter(fragmentManager as FragmentManager)
        viewPagerFavorites.adapter = adapter
        tabLayoutFavorites.setupWithViewPager(viewPagerFavorites)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}
