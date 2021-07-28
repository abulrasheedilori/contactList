package com.brainstem.myui4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class ViewPagerAdapter(
    private var data:List<ViewPagerData>):RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>(){
        inner class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false)
        return  ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.itemView.apply {
            val imageViewPager = findViewById<ImageView>(R.id.viewPager_imageView)
            val msg = findViewById<TextView>(R.id.textView_prof_msg)
            val data = data[position]
            msg.text= data.message
            imageViewPager.setImageResource(data.image)

            // to slide vertically
//            imageViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
            //auto-draging
//            imageViewPager.beginDrag()
//            imageViewPager.fakeDragBy(-10f)
//            imageViewPager.endFakeDrag()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}