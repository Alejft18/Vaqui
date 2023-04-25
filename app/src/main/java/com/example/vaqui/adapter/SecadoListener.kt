package com.example.vaqui.adapter
import org.json.JSONObject

interface SecadoListener {
    fun onItemClicked(secado: JSONObject, position: Int)
}