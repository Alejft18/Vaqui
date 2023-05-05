package com.example.vaqui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.R
import org.json.JSONObject

class LecherasAdapter(private val lecherasList: ArrayList<JSONObject>,private val lecherasListener: LecherasListener): RecyclerView.Adapter<LecherasAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var BovinoID : TextView = view.findViewById(R.id.IdVacas)
        var categorias : TextView = view.findViewById(R.id.categoVacas)

        fun bind(bovinos: JSONObject){
            BovinoID.text = bovinos.getString("Bovino_ID")
            categorias.text = bovinos.getString("Categoria")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_general,  parent, false)
    )

    override fun getItemCount() = this.lecherasList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bovinos = lecherasList[position]
        try {
            holder.bind(bovinos)

            holder.itemView.setOnClickListener {
                lecherasListener.onItemClicked(bovinos , position)
            }
        } catch (e : Exception) {

        }
    }
}