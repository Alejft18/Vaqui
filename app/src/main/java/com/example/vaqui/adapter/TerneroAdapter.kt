package com.example.vaqui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.R
import org.json.JSONObject

class TerneroAdapter(private val terneroList: ArrayList<JSONObject>, private val ternerosListener: TernerosListener) : RecyclerView.Adapter<TerneroAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var BovinoID : TextView = view.findViewById(R.id.Idternero)
        var categorias : TextView = view.findViewById(R.id.categoriaternero)

        fun bind(ternero: JSONObject){
            BovinoID.text = ternero.getString("id")
            categorias.text = ternero.getString("categoria")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_ternero,  parent, false)
    )

    override fun getItemCount() = this.terneroList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ternero = terneroList[position]
        try {
            holder.bind(ternero)

            holder.itemView.setOnClickListener {
                ternerosListener.onItemClicked(ternero , position)
            }
        } catch (e : Exception) {
            Log.w("errorrrr", "No cargó la imagen")
        }
    }
}