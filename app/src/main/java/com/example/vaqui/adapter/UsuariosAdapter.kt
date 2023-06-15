package com.example.vaqui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.Buscador.BuscadorBovinoFragment
import com.example.vaqui.R
import org.json.JSONObject

class UsuariosAdapter (private val usuarioList: ArrayList<JSONObject>, private val usuariosListener: UsuariosListener) : RecyclerView.Adapter<UsuariosAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var  id: TextView = view.findViewById(R.id.idEmpleado)
        var area : TextView = view.findViewById(R.id.areaEmpleado)

        fun bind(usuarios: JSONObject){
            id.text = usuarios.getString("id")
            area.text = usuarios.getString("area")
            Log.w("errorrrr", "No cargó la imagen")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_empleados,  parent, false)
    )

    override fun getItemCount() = this.usuarioList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bovinos = usuarioList[position]
        try {
            holder.bind(bovinos)
            holder.itemView.setOnClickListener {
                usuariosListener.onItemClicked(bovinos , position)
            }
        } catch (e : Exception) {
            Log.w("errorrrr", "No cargó la imagen")
        }
    }
}