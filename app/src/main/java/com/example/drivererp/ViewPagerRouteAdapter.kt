package com.example.drivererp


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.drivererp.ui.ui.route.RouteFragment

class ViewPagerRouteAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = listOf<Fragment>(
        RouteFragment(),  // Replace with your actual fragments
        Fragment2(),
        Fragment3()
    )

    private val fragmentTitleList = listOf(
        "Tab 1",
        "Tab 2",
        "Tab 3"
    )

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }
}
