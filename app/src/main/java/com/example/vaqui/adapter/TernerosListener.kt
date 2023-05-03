package com.example.vaqui.adapter
import org.json.JSONObject

interface TernerosListener {
    fun onItemClicked(terneros: JSONObject, position: Int)
}