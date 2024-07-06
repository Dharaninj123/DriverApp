package com.example.drivererp.ui.route

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.drivererp.R
import com.example.drivererp.data.RouteItemModel

class RouteAdapter(private val mList: List<RouteItemModel>) : RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_routeitem, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.locationView.text = item.location
        holder.distanceView.text = item.distance
        holder.timeView.text = item.time


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val locationView: TextView = itemView.findViewById(R.id.location)
        val distanceView: TextView = itemView.findViewById(R.id.distance)
        val timeView: TextView = itemView.findViewById(R.id.time)
    }
}