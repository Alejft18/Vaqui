package com.example.vaqui.adapter
import org.json.JSONObject

interface GestacionListener {
    fun onItemClicked(gestacion: JSONObject, position: Int)
}