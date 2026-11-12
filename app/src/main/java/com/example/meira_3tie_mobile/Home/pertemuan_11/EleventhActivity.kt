package com.example.meira_3tie_mobile.Home.pertemuan_11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.meira_3tie_mobile.R
import com.example.meira_3tie_mobile.databinding.ActivityEleventhBinding
import com.google.android.material.tabs.TabLayoutMediator

class EleventhActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEleventhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEleventhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Pertemuan 11"
            setDisplayHomeAsUpEnabled(true)
        }

        // 1. Inisialisasi Adapter
        val tabsAdapter = EleventhTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Atur judul untuk setiap tab
            when (position) {
                0 -> {
                    tab.text = "Tab A"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(  this, R.drawable.ic_link_web)
                    //Tambah Badge Tanpa nomor (hanya titik)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = false
                }
                1 -> {
                    tab.text = "Tab B"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(  this, R.drawable.ic_home)
                    //Tambah Badge dengan nomor
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 99
                }
                2 -> {
                    tab.text = "Produk"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_more)
                }
            }
        }.attach()


        // Badge titik pada Tab A
        binding.tabLayout.getTabAt(0)
            ?.getOrCreateBadge()?.apply {
                isVisible = true
            }

        // Badge angka pada Tab B
        binding.tabLayout.getTabAt(1)
            ?.getOrCreateBadge()?.apply {
                isVisible = true
                number = 5
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
