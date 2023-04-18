
package com.example.vaqui.adapter

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import com.example.vaqui.adapter.BovinosListener
import com.example.vaqui.*


/*class bovinosAdapter (private val bovinosList: ArrayList<JSONObject>, private val bovinosListener: BovinosListener) : RecyclerView.Adapter<bovinosAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var BovinosID : TextView=view.findViewById(R.id.BovinosID)
        var Raza : TextView=view.findViewById(R.id.Raza)

        fun bind(bovino: JSONObject){
            BovinosID.text = bovino.getString("ID")
            Raza.text = bovino.getString("Raza")
        }
    }


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_buscador, parent, false)
    }
}*/
