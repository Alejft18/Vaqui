package com.example.vaqui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.R
import org.json.JSONObject

class LecherasAdapter(private val lecherasList: ArrayList<JSONObject>,private val lecherasListener: LecherasListener): RecyclerView.Adapter<LecherasAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var BovinoID : TextView = view.findViewById(R.id.idlecheras_item)
        var litros : TextView = view.findViewById(R.id.litros_item)

        fun bind(lecheras: JSONObject){
            BovinoID.text = lecheras.getString("id")
            litros.text = lecheras.getString("litro_producidos")
            Log.w("errorrrr", "No cargó la imagen")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_lecheras,  parent, false)
    )

    override fun getItemCount() = this.lecherasList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lecheras = lecherasList[position]
        try {
            holder.bind(lecheras)

            holder.itemView.setOnClickListener {
                lecherasListener.onItemClicked(lecheras , position)
            }
        } catch (e : Exception) {

        }
    }
}