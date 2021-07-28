package com.brainstem.myui4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting the layout to the Activity
        setContentView(R.layout.activity_main)
        //populating the recycler view with data to show on the main layout
        val listOfItemView = mutableListOf(
            Itemview(R.drawable.photo5, "Adria Rogers", "6:22:45", "2 days ago"),
            Itemview(R.drawable.photo6, "John Rojan", "10:13:45", "15 minutes ago"),
            Itemview(R.drawable.photo7, "Ayomide Jeffrey", "10:22:05", "23 hours ago"),
            Itemview(R.drawable.photo8, "Jarritos Ola", "18:22:45", "1 days ago"),
            Itemview(R.drawable.photo9, "Treddy Chen", "09:22:45", "20 days ago")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = Adapter(listOfItemView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Populating view Pager with pictures
        val images = mutableListOf(
            ViewPagerData(R.drawable.photo10, "It's Rojan's birthday"),
            ViewPagerData(R.drawable.photo9, "It's Adria's birthday"),
            ViewPagerData(R.drawable.photo8, "It's Rojan's birthday"),
            ViewPagerData(R.drawable.photo7, "It's Jeffery's birthday"),
            ViewPagerData(R.drawable.photo6, "It's Jarrito's birthday"),
            ViewPagerData(R.drawable.photo5, "It's Chen's birthday"))

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(images)

        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.setPadding(-9, 0, 99, 0)

        val compoTrans = CompositePageTransformer()
        compoTrans.addTransformer { page, position ->
            val r: Float = 1 - kotlin.math.abs(position)
            page.translationX = 60f
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager.setPageTransformer(compoTrans)
    }
}
