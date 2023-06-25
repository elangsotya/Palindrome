package com.example.palindromeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.palindromeapp.DataItem
import com.example.palindromeapp.R

class MainAdapter(private val context: Context, private val list: ArrayList<DataItem>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_review, viewGroup, false)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val list = list[position]
        viewHolder.tvFirstName.text = list.firstName
        viewHolder.tvLastName.text = list.lastName
        viewHolder.tvEmail.text = list.email
        Glide.with(viewHolder.itemView.context)
            .load(list.avatar)
            .into(viewHolder.ivPhoto)
        viewHolder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list)
        }
    }

    override fun getItemCount() = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFirstName: TextView = view.findViewById(R.id.tv_first_name)
        val tvLastName: TextView = view.findViewById(R.id.tv_last_name)
        val ivPhoto: ImageView = view.findViewById(R.id.img_item_photo)
        val tvEmail: TextView = view.findViewById(R.id.tv_email)
    }

     fun setData(data: ArrayList<DataItem>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}