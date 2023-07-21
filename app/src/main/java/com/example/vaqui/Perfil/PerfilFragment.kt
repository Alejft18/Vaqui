package com.example.vaqui.Perfil

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vaqui.R
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import java.sql.DriverManager.println

class PerfilFragment : Fragment() {
    private lateinit var area_usuario : TextView
    private lateinit var documento_usuario : TextInputEditText
    private lateinit var nombre_usuario : TextInputEditText
    private lateinit var apellidos_usuario : TextInputEditText
    private lateinit var telefono_usuario : TextInputEditText
    private lateinit var correo_usuario : TextInputEditText
    private lateinit var contrasena_usuario : TextInputEditText
    private lateinit var imagen_atras : ImageView
    private lateinit var texto_guardar_informacion_empleado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val area = "ordeño"
        val documento = "123456789"
        val nombre = "juan"
        val apellido = "Torres"
        val contrasena = "123456789"
        val telefono = "123456789"
        val correo = "juantorres@gmaiil.com"


        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_perfil, container, false)
        this.imagen_atras = ll.findViewById(R.id.imagen_atras_perfil)
        this.area_usuario = ll.findViewById(R.id.area_usuario)
        this.documento_usuario = ll.findViewById(R.id.documento_usuario)
        this.nombre_usuario = ll.findViewById(R.id.nombre_usuario)
        this.apellidos_usuario = ll.findViewById(R.id.apellidos_usuario)
        this.telefono_usuario = ll.findViewById(R.id.telefono_usuario)
        this.contrasena_usuario = ll.findViewById(R.id.contrasena_usuario)
        this.correo_usuario = ll.findViewById(R.id.correo_usuario)
        this.texto_guardar_informacion_empleado = ll.findViewById(R.id.texto_guardar_informacion_empleado)

        imagen_atras.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_inicio)

        }

        texto_guardar_informacion_empleado.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Guardado")
            builder.setMessage("Datos guiardados exitosamente")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                findNavController().navigate(R.id.action_perfilFragment_to_inicio)
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }




        area_usuario.text = area
        documento_usuario.setText(documento)
        nombre_usuario.setText(nombre)
        apellidos_usuario.setText(apellido)
        contrasena_usuario.setText(contrasena)
        telefono_usuario.setText(telefono)
        correo_usuario.setText(correo)

        return ll
    }


}
