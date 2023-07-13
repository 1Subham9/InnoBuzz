package com.amtron.innobuzz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amtron.innobuzz.R
import com.amtron.innobuzz.model.Post


class PostAdapter(private val itemClickInterface: ItemClickInterface) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val allData = ArrayList<Post>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.title)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = allData[position].title

        holder.title.setOnClickListener {
            itemClickInterface.getPost(position)
        }

    }


    fun updateList(newList: List<Post>) {
        allData.clear()
        allData.addAll(newList)
        notifyDataSetChanged()

    }

    interface ItemClickInterface {
        fun getPost(position : Int)
    }

}