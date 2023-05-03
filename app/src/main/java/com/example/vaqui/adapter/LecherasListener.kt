package com.example.vaqui.adapter
import org.json.JSONObject

interface LecherasListener {
    fun onItemClicked(lecheras: JSONObject, position: Int)

}
