package com.johnnmancilla.androidforfun.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.johnnmancilla.androidforfun.R
import com.johnnmancilla.androidforfun.model.response.Laptop









class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>{


    private val items: List<Laptop>

   // abstract fun itemSelected(itemSelected:Laptop)

    constructor(items: List<Laptop>) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_main_item, parent, false) as View
        return MyViewHolder(v,this)
    }


    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.title?.setText(item?.title)
        holder.description?.setText(item?.description)


    }

    class MyViewHolder(view: View,adapter:MainAdapter?) : RecyclerView.ViewHolder(view){
        var title:TextView?=null
        var description:TextView?=null

        init {
            title = view.findViewById<View>(R.id.title) as TextView
            description = view.findViewById<View>(R.id.description) as TextView
            this.itemView.setOnClickListener {
               // adapter?.itemSelected(itemTitle.text.toString())
            }
        }
    }




}
