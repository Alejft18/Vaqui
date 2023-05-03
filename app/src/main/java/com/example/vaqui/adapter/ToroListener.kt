package com.example.vaqui.adapter
import org.json.JSONObject

interface ToroListener {
    fun onItemClicked(toros: JSONObject, position: Int)
}