package com.example.vaqui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.R
import org.json.JSONObject

class ToroAdapter (private val toroList: ArrayList<JSONObject>, private val toroListener: ToroListener) : RecyclerView.Adapter<ToroAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var BovinoID : TextView = view.findViewById(R.id.Idtor_item)
        var categorias : TextView = view.findViewById(R.id.categoriToro)

        fun bind(toro: JSONObject){
            BovinoID.text = toro.getString("id")
            categorias.text = toro.getString("categoria")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_toro,  parent, false)
    )

    override fun getItemCount() = this.toroList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val toro = toroList[position]
        try {
            holder.bind(toro)

            holder.itemView.setOnClickListener {
                toroListener.onItemClicked(toro , position)
            }
        } catch (e : Exception) {

        }
    }
}