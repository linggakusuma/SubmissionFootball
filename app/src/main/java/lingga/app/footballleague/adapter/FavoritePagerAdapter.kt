package lingga.app.footballleague.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import lingga.app.footballleague.ui.favorites.lastmatchfavorite.LastMatchFavoriteFragment
import lingga.app.footballleague.ui.favorites.nextmatchfavorite.NextMatchFavoriteFragment
import lingga.app.footballleague.ui.favorites.teamsfavorite.TeamsFavoriteFragment

class FavoritePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
    fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    init {
        val lastMatchFavoriteFragment = LastMatchFavoriteFragment()
        val nextMatchFavoriteFragment = NextMatchFavoriteFragment()
        val teamsFavoriteFragment = TeamsFavoriteFragment()

        fragments.add(lastMatchFavoriteFragment)
        fragments.add(nextMatchFavoriteFragment)
        fragments.add(teamsFavoriteFragment)

        titles.add("Last Match")
        titles.add("Next Match")
        titles.add("Teams")
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}