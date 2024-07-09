package com.example.drivererp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.drivererp.R
import com.example.drivererp.data.HomeItemModel

class HomeAdapter(
    private val data: ArrayList<HomeItemModel>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val locationView: TextView = itemView.findViewById(R.id.location)
        val distanceView: TextView = itemView.findViewById(R.id.distance)
        val timeView: TextView = itemView.findViewById(R.id.time)

        init {
            itemView.setOnClickListener {
                itemClickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_homeitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.locationView.text = item.location
        holder.distanceView.text = item.distance
        holder.timeView.text = item.time
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
