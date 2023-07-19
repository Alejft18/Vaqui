package com.example.vaqui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private lateinit var documentoInicioSesion: TextInputEditText
    private lateinit var contrasenaInicioSesion: TextInputEditText
    private lateinit var botonIniciarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        documentoInicioSesion = findViewById(R.id.documento_inicio_sesion)
        contrasenaInicioSesion = findViewById(R.id.contrasena_inicio_sesion)
        botonIniciarSesion = findViewById(R.id.boton_iniciar_sesion)

        botonIniciarSesion.setOnClickListener {
            obtenerUsuario()
        }
    }

    private fun obtenerUsuario() {
        val url = "https://vaquijpa2-production.up.railway.app/buscarUsuario/${documentoInicioSesion.text.toString()}"
        val queue: RequestQueue = Volley.newRequestQueue(this)

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


                val bundle = Bundle()
                bundle.putString("nombre_perfil",nombre)
                bundle.putString("apellido_perfil",apellido)
                bundle.putString("telefono_perfil",telefono)
                bundle.putString("correo_perfil",correo)
                bundle.putString("contrasena_perfil",contrasena)
                bundle.putString("rol_perfil",rol)
                bundle.putString("area_perfil",area)

                if (contrasenaInicioSesion.text.toString() == contrasena.toString()){
                    Toast.makeText(this, "Bienvenido $nombre", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)

                }else {
                    Toast.makeText(this, "documento y/o contraseña invalida", Toast.LENGTH_SHORT)
                        .show()
                }

            },
            { error ->
                Toast.makeText(this, "documento y/o contraseña invalida", Toast.LENGTH_SHORT).show()
                println("Error en la solicitud: " + error.message)
            }
        )

        queue.add(jsonObjectRequest)
    }


}