package com.example.vaqui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.R
import org.json.JSONObject

class EngordeAdapter(private val engordeList: ArrayList<JSONObject>, private val engordeListener: EngordeListener) : RecyclerView.Adapter<EngordeAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var BovinoID : TextView = view.findViewById(R.id.id_general)
        var categorias : TextView = view.findViewById(R.id.raza_general)

        fun bind(engorde: JSONObject){
            BovinoID.text = engorde.getString("id")
            categorias.text = engorde.getString("categoria")
            Log.w("errorrrr", "No cargó la imagen")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_general,  parent, false)
    )

    override fun getItemCount() = this.engordeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val engorde = engordeList[position]
        try {
            holder.bind(engorde)

            holder.itemView.setOnClickListener {
                engordeListener.onItemClicked(engorde , position)
            }
        } catch (e : Exception) {
            Log.w("errorrrr", "No cargó la imagen")
        }
    }
}
