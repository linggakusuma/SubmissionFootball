package lingga.app.footballleague.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import lingga.app.footballleague.ui.lastmatch.LastMatchFragment
import lingga.app.footballleague.ui.nextmatch.NextMatchFragment

class ViewPagerAdapter(fm: FragmentManager, bundle: Bundle) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    init {
        val nextMatchFragment = NextMatchFragment()
        val lastMatchFragment = LastMatchFragment()

        nextMatchFragment.arguments = bundle
        lastMatchFragment.arguments = bundle

        fragments.add(lastMatchFragment)
        fragments.add(nextMatchFragment)
        titles.add("Last Match")
        titles.add("Next Match")
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