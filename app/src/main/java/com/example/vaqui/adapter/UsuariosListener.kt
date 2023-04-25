package com.example.vaqui.adapter
import org.json.JSONObject

interface UsuariosListener {
    fun onItemClicked(usuarios: JSONObject, position: Int)
}