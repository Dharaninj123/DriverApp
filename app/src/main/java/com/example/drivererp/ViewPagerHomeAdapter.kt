package com.example.drivererp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.drivererp.ui.Map.MapsFragment
import com.example.drivererp.ui.home.HomeFragment

class ViewPagerHomeAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = listOf<Fragment>(
        HomeFragment(),  // Replace with your actual fragments
        MapsFragment()
    )

    private val fragmentTitleList = listOf(
        "Route",
        "Track",
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
