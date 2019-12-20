package lingga.app.footballleague.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import lingga.app.footballleague.ui.lastmatch.LastMatchFragment
import lingga.app.footballleague.ui.standings.StandingsFragment
import lingga.app.footballleague.ui.teams.TeamsFragment

class ViewPagerAdapter(fm: FragmentManager, bundle: Bundle) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    init {
        val lastMatchFragment = LastMatchFragment()
        val standingsFragment = StandingsFragment()
        val teamsFragment = TeamsFragment()

        lastMatchFragment.arguments = bundle
        standingsFragment.arguments = bundle
        teamsFragment.arguments = bundle

        fragments.add(lastMatchFragment)
        fragments.add(standingsFragment)
        fragments.add(teamsFragment)

        titles.add("Match")
        titles.add("Standings")
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