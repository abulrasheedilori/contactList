package com.brainstem.myui4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    var status:List<Itemview>
    ): RecyclerView.Adapter<Adapter.MyViewHolder> (){

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
            return MyViewHolder(view)
        }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.apply {
        var data_image = findViewById<ImageView>(R.id.profile_img)
        var data_name = findViewById<TextView>(R.id.recycler_status_name)
        var data_time = findViewById<TextView>(R.id.recycler_status_cake_text)
        var data_day =  findViewById<TextView>(R.id.recycler_status_portfolio_text)

        data_image.setImageResource(status[position].image)
        data_name.text = status[position].name
        data_time.text = status[position].time
        data_day.text = status[position].day
        }
    }

    override fun getItemCount(): Int {
        return status.size
    }
}