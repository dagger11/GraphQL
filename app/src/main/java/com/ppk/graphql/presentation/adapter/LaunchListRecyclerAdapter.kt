package com.ppk.graphql.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ppk.graphql.R
import com.ppk.graphql.data.model.LaunchModel
import com.ppk.graphql.presentation.contract.ViewContract


class LaunchListRecyclerAdapter constructor(launchList:ArrayList<LaunchModel>,
                                            private val viewContract: ViewContract
): RecyclerView.Adapter<LaunchListRecyclerAdapter.ViewHolder>() {

    private var launchList: ArrayList<LaunchModel>? = launchList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_spacelist,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:LaunchModel = launchList?.get(position) ?: LaunchModel("Error","Error","Error")
        holder.name.text = model.name
        holder.date.text = model.date
        holder.baseLayout.setOnClickListener { viewContract.nextActivity(model) }
    }

    override fun getItemCount(): Int = launchList?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val date: TextView = itemView.findViewById(R.id.date)
        val photo: ImageView = itemView.findViewById(R.id.photo)
        val baseLayout:RelativeLayout = itemView.findViewById(R.id.baseLayout)
    }
}