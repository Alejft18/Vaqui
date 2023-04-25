package com.example.vaqui.adapter
import org.json.JSONObject
import java.text.FieldPosition

interface EmpleadosListener {
    fun onItemClicked(empleados: JSONObject, position: Int)
}