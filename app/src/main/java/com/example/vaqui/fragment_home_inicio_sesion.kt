package com.example.vaqui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONException

class fragment_home_inicio_sesion : Fragment() {
    private lateinit var documento_inicio_sesion : TextInputEditText
    private lateinit var contrasena_inicio_sesion : TextInputEditText
    private lateinit var boton_iniciar_sesion : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_home_inicio_sesion, container, false)
        this.documento_inicio_sesion = ll.findViewById(R.id.documento_inicio_sesion)
        this.contrasena_inicio_sesion = ll.findViewById(R.id.contrasena_inicio_sesion)
        this.boton_iniciar_sesion = ll.findViewById(R.id.boton_iniciar_sesion)

        boton_iniciar_sesion.setOnClickListener {
            obtenerUsuario()
        }

        return ll
    }


    private fun obtenerUsuario() {
        val url = "http://192.168.208.187:8080/buscarUsuario/${documento_inicio_sesion.text.toString()}"
        val queue = Volley.newRequestQueue(requireContext())

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val nombre = response.getString("nombre")
                val apellido = response.getString("apellido")
                val telefono = response.getString("telefono")
                val correo = response.getString("correo")
                val contrasena = response.getString("contrasena")
                val rol = response.getString("rol")
                val area = response.getString("area")
                val imagen = response.getString("imagen")

                if (contrasena_inicio_sesion.text.toString() == contrasena.toString()) {
                    Toast.makeText(requireContext(), "inicio de sesion exitoso", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(requireContext(), "documento y/o contraseña invalida", Toast.LENGTH_SHORT).show()
                }

            },
        ){ error ->
            Toast.makeText(requireContext(), "documento y/o contraseña invalida", Toast.LENGTH_SHORT).show()
            println("Error en la solicitud: " + error.message)
        }

        queue.add(jsonObjectRequest)
    }


}