package com.example.vaqui.adapter
import org.json.JSONObject
import java.text.FieldPosition

interface BovinosListener {
    fun onItemClicked(bovinos: JSONObject, position: Int)
}
