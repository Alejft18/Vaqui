package com.example.vaqui.Buscador

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.vaqui.R
import com.example.vaqui.actualizarEmpleadoFragment
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import org.w3c.dom.Text


class DetalleEmpleadoFragment : DialogFragment() {

    private lateinit var tvBarra_empleado : Toolbar
    private lateinit var img_empleado : ImageView
    private lateinit var id_empleados : TextView
    private lateinit var nombre_empleado : TextView
    private lateinit var apellidos_empleado : TextView
    private lateinit var telefono_empleado : TextView
    private lateinit var correo_empleado : TextView
    private lateinit var area_empleado : TextView
    private lateinit var contrasena_empleado : TextView
    private lateinit var rol_empleado : TextView

    private lateinit var btn_detalle_actualizar_empleado : Button
    private lateinit var btn_detalle_eliminar_empleado : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, androidx.appcompat.R.style.AlertDialog_AppCompat)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_detalle_empleado, container, false)
        this.tvBarra_empleado = ll.findViewById(R.id.tvBarra_empleado)
        this.img_empleado = ll.findViewById(R.id.img_empleado)
        this.id_empleados = ll.findViewById(R.id.id_empleados)
        this.nombre_empleado = ll.findViewById(R.id.nombre_empleado)
        this.apellidos_empleado = ll.findViewById(R.id.apellidos_empleado)
        this.telefono_empleado = ll.findViewById(R.id.telefono_empleado)
        this.correo_empleado = ll.findViewById(R.id.correo_empleado)
        this.contrasena_empleado = ll.findViewById(R.id.contrasena_empleado)
        this.area_empleado = ll.findViewById(R.id.area_empleado)

        this.btn_detalle_actualizar_empleado = ll.findViewById(R.id.btn_detalle_actualizar_empleado)
        this.btn_detalle_eliminar_empleado = ll.findViewById(R.id.btn_detalle_eliminar_empleado)

        btn_detalle_actualizar_empleado.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_empleado",id_empleados.text.toString())
            bundle.putString("nombre_empleado",nombre_empleado.text.toString())
            bundle.putString("apellidos_empleado",apellidos_empleado.text.toString())
            bundle.putString("telefono_empleado",telefono_empleado.text.toString())
            bundle.putString("correo_empleado",correo_empleado.text.toString())
            bundle.putString("contrasena_empleado",contrasena_empleado.text.toString())
            bundle.putString("area_empleado",area_empleado.text.toString())

            val actualizarEmpleadoFragment = actualizarEmpleadoFragment()
            actualizarEmpleadoFragment.arguments = bundle
            findNavController().navigate(R.id.action_detalleEmpleadoFragment_to_actualizarEmpleadoFragment, bundle)
        }


        btn_detalle_eliminar_empleado.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Eliminar empleado")
            builder.setMessage("¿Estas seguro de eliminar a ${nombre_empleado.text} de tus empleados?")
            builder.setPositiveButton("Aceptar") { dialog, which ->

                val url = "http://192.168.56.187:8080/eliminarUsuario/${id_empleados.text}"
                val queue = Volley.newRequestQueue(requireContext())

                val request = StringRequest(
                    Request.Method.DELETE, url,
                    { response ->
                        Toast.makeText(requireContext(),"eliminacion exitosa", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                ) { error ->
                    Toast.makeText(requireContext(), "Error al eliminar el empleado", Toast.LENGTH_SHORT).show()
                    println("Error en la solicitud: " + error.message)
                }
                queue.add(request)

            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tvBarra_empleado.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_close_24)
        this.tvBarra_empleado.setNavigationOnClickListener {
            dismiss()
        }

    val usuarios = JSONObject(arguments?.getString("usuarios"))
        this.id_empleados.text = usuarios.getString("id")
        this.nombre_empleado.text = usuarios.getString("nombre")
        this.apellidos_empleado.text = usuarios.getString("apellido")
        this.telefono_empleado.text = usuarios.getString("telefono")
        this.correo_empleado.text = usuarios.getString("correo")
        this.contrasena_empleado.text = usuarios.getString("contrasena")
        this.area_empleado.text = usuarios.getString("area")

        Glide.with(this)
            .load(usuarios.getString("imagen"))
            .into(this.img_empleado)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}