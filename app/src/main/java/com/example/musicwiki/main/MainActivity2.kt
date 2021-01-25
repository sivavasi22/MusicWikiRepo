package com.example.musicwiki.main

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.musicwiki.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : BaseActivity() {
    data class Userdetails(val specs: MutableList<Any>)


    var position = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val album = intent.getStringExtra("rock")
        //PrefsHelper().savePref("rock",album)
back_chat_menu.setOnClickListener {
    onBackPressed()
}
        album_text.text = album

        val intent1 = this.getIntent();
        if (intent1 != null && intent1.getExtras() != null) {
            //val jobID = this.getIntent().getExtras()!!.getInt("JOBID");
            position = intent1.getIntExtra("position", 0)
        };
        val viewPager: ViewPager = findViewById(R.id.chat_viewPager)
        val adapter = GenereViewPageAdapter(supportFragmentManager,album)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(position)


        val tabLayout = findViewById<TabLayout>(R.id.chat_tabs)
        tabLayout.setupWithViewPager(viewPager)

        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                adapter.notifyDataSetChanged()
            }

        })
    }


    }
