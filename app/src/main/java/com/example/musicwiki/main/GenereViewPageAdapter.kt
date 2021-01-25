package com.example.musicwiki.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter


@Suppress("DEPRECATION")
class GenereViewPageAdapter(fm: FragmentManager, val album: String?) : FragmentStatePagerAdapter(fm){


    override fun getItem(position: Int): Fragment {
        val fragment = GenereMenuFragmnet(position,album)

        return fragment
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var position  = position+1
        if (position==1){
            return  ("ALBUMS")
        }
        if(position==2)
        {
            return ("ARTISTS")
        }
        else{
            return "TRACKS"
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}
