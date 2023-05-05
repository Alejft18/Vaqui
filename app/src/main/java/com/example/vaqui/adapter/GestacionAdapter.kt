package com.example.vaqui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.R
import com.example.vaqui.RecyclerView_Gestacion
import org.json.JSONObject

class GestacionAdapter(private val gestacionList: ArrayList<JSONObject>, private val gestacionListener: GestacionListener): RecyclerView.Adapter<GestacionAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var BovinoID : TextView = view.findViewById(R.id.id_general)
        var categorias : TextView = view.findViewById(R.id.raza_general)

        fun bind(gestacion: JSONObject){
            BovinoID.text = gestacion.getString("id")
            categorias.text = gestacion.getString("categoria")
            Log.w("errorrrr", "No cargó la imagen")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_general,  parent, false)
    )

    override fun getItemCount() = this.gestacionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gestacion = gestacionList[position]
        try {
            holder.bind(gestacion)

            holder.itemView.setOnClickListener {
                gestacionListener.onItemClicked(gestacion , position)
            }
        } catch (e : Exception) {
            Log.w("errorrrr", "No cargó la imagen")
        }
    }
}