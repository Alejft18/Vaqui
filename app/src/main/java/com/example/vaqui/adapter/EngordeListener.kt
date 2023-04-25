package com.example.vaqui.adapter
import org.json.JSONObject

interface EngordeListener {
    fun onItemClicked(engorde: JSONObject, position: Int)
}