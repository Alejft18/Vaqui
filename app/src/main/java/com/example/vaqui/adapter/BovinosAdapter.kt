package com.example.vaqui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.Moldels.Bovino
import com.example.vaqui.R
import org.json.JSONObject

class BovinosAdapter(private val bovinoList: ArrayList<JSONObject>, private val bovinosListener: BovinosListener) : RecyclerView.Adapter<BovinosAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var BovinoID : TextView = view.findViewById(R.id.IdVacasg)
        var raza : TextView = view.findViewById(R.id.razag)

        fun bind(bovinos: JSONObject){
            BovinoID.text = bovinos.getString("id")
            raza.text = bovinos.getString("raza")
            Log.w("errorrrr", "No cargó la imagen")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_general,  parent, false)
        )

    override fun getItemCount() = this.bovinoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bovinos = bovinoList[position]
        try {
            holder.bind(bovinos)
            holder.itemView.setOnClickListener {
                bovinosListener.onItemClicked(bovinos , position)
            }
        } catch (e : Exception) {
            Log.w("errorrrr", "No cargó la imagen")
        }
    }


}