package com.example.contact
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private var contacts:List<ContactDataClass>,
    private val listener: OnItemClickListener

): RecyclerView.Adapter<ContactAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val image = itemView.findViewById<ImageView>(R.id.profile_image)
        val name =  itemView.findViewById<TextView>(R.id.name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val contact = contacts[position]


        holder.apply {
                    image.setImageResource(contact.image)
                    name.text = contact.name
                }

    }

    override fun getItemCount(): Int {
        return contacts.size
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}