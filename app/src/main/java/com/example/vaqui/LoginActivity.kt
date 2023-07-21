package com.example.vaqui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.vaqui.Perfil.PerfilFragment
import com.google.android.material.textfield.TextInputEditText
import java.sql.DriverManager.println

class LoginActivity : AppCompatActivity() {
    private lateinit var documentoInicioSesion: TextInputEditText
    private lateinit var contrasenaInicioSesion: TextInputEditText
    private lateinit var olvide_contrasena : TextView
    private lateinit var botonIniciarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        documentoInicioSesion = findViewById(R.id.documento_inicio_sesion)
        contrasenaInicioSesion = findViewById(R.id.contrasena_inicio_sesion)
        botonIniciarSesion = findViewById(R.id.boton_iniciar_sesion)
        olvide_contrasena = findViewById(R.id.olvide_contrasena)

        botonIniciarSesion.setOnClickListener {
            obtenerUsuario()
        }

        olvide_contrasena.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("¿olvidaste tu contraseña?")
            builder.setMessage("Ponte en contacto con el administrador para gestionar el cambio de contraseña")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    @SuppressLint("ResourceType")
    private fun obtenerUsuario() {
        val url = "https://vaquijpa2-production.up.railway.app/buscarUsuario/${documentoInicioSesion.text.toString()}"
        val queue: RequestQueue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val documento = response.getString("id")
                val nombre = response.getString("nombre")
                val apellido = response.getString("apellido")
                val telefono = response.getString("telefono")
                val correo = response.getString("correo")
                val contrasena = response.getString("contrasena")
                val rol = response.getString("rol")
                val area = response.getString("area")
                val imagen = response.getString("imagen")





                if (contrasenaInicioSesion.text.toString() == contrasena.toString()){
                    Toast.makeText(this, "Bienvenido $nombre", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)

                    val bundle = Bundle()
                    bundle.putString("documento_perfil",documento)
                    bundle.putString("nombre_perfil",nombre)
                    bundle.putString("apellido_perfil",apellido)
                    bundle.putString("telefono_perfil",telefono)
                    bundle.putString("correo_perfil",correo)
                    bundle.putString("contrasena_perfil",contrasena)
                    bundle.putString("rol_perfil",rol)
                    bundle.putString("area_perfil",area)

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