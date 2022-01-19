package com.example.picture_of_the_day.photo_of_planet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val EARTH_FRAGMENT = 0
private const val MARS_FRAGMENT = 1
private const val MOON_FRAGMENT = 2

class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    private val fragments = arrayOf(MarsFragment(), EarthFragment(),
        MoonFragment())
    override fun getItem(position: Int): Fragment {
        return when (position) {
            1 -> fragments[MARS_FRAGMENT]
            2 -> fragments[MOON_FRAGMENT]
            else -> fragments[EARTH_FRAGMENT]
        }
    }
    override fun getCount(): Int {
        return fragments.size
    }
}