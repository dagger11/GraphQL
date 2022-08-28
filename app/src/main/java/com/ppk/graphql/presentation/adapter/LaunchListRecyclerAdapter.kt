package com.ppk.graphql.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ppk.graphql.CharactersListQuery
import com.ppk.graphql.R
import com.ppk.graphql.presentation.contract.ViewContract

open class LaunchListRecyclerAdapter constructor(
    private val charactersListQuery: List<CharactersListQuery.Result?>,
    private val viewContract: ViewContract
): RecyclerView.Adapter<LaunchListRecyclerAdapter.ViewHolder>() {

    lateinit var characterList: List<CharactersListQuery.Result>
    var characterCount: Int =0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_spacelist,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = charactersListQuery.get(position)
        holder.name.text = data?.name
        holder.date.text = data?.species
        Glide.with(viewContract.provideContext()).load(data?.image).into(holder.photo)
        holder.baseLayout.setOnClickListener { data?.id?.let { it1 -> viewContract.nextActivity(it1) } }
    }

    override fun getItemCount(): Int = charactersListQuery.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val date: TextView = itemView.findViewById(R.id.date)
        val photo: ImageView = itemView.findViewById(R.id.photo)
        val baseLayout:RelativeLayout = itemView.findViewById(R.id.baseLayout)
    }
}