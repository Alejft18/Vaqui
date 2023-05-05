package com.example.vaqui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.R
import org.json.JSONObject

class SecadoAdapter (private val secadoList: ArrayList<JSONObject>, private val secadoListener: SecadoListener) : RecyclerView.Adapter<SecadoAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var BovinoID : TextView = view.findViewById(R.id.idsecado)
        var categorias : TextView = view.findViewById(R.id.categoriaSecado)

        fun bind(bovinos: JSONObject){
            BovinoID.text = bovinos.getString("id")
            categorias.text = bovinos.getString("categoria")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_secado,  parent, false)
    )

    override fun getItemCount() = this.secadoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bovinos = secadoList[position]
        try {
            holder.bind(bovinos)

            holder.itemView.setOnClickListener {
                secadoListener.onItemClicked(bovinos , position)
            }
        } catch (e : Exception) {

        }
    }
}