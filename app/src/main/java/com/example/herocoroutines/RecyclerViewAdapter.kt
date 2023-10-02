package com.example.herocoroutines


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(var items:MutableList<Hero> = mutableListOf(), val onItemClick:(result:Hero) -> Unit):RecyclerView.Adapter<RecycleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val listItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout,parent, false)
        return RecycleViewHolder(listItemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.name.text = "Name: ${items[position].name}"
        holder.work.text = "Occupation: ${items[position].work.occupation}"
        Glide.with(holder.itemView)
            .load(items[position].images.md)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            onItemClick(items[position])
        }
    }
    fun updateItems(itemsToUpdate: MutableList<Hero>){
        items = itemsToUpdate
        notifyDataSetChanged()
    }
}

class RecycleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val name:TextView = itemView.findViewById(R.id.name)
    val image: ImageView = itemView.findViewById(R.id.image)
    val work:TextView = itemView.findViewById(R.id.work)
}