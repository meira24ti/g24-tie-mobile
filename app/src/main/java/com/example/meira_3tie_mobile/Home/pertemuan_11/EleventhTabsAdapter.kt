package com.example.meira_3tie_mobile.Home.pertemuan_11

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class EleventhTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    //Jumlah total tabs yang ada
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment()
            1 -> TabBFragment()
            2 -> TabCFragment()
            else -> throw IllegalStateException(
                "Posisi tab tidak valid"
            )
        }
    }
}
