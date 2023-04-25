package com.example.vaqui.adapter
import org.json.JSONObject

interface AdminsListener {
    fun onItemClicked(administrador: JSONObject, position: Int)
}