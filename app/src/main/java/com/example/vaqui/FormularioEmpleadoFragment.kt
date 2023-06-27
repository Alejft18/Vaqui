package com.example.vaqui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject

class FormularioEmpleadoFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var ingreso_documento_Empleado: TextInputEditText
    private lateinit var ingreso_nombre_empleado: TextInputEditText
    private lateinit var ingrso_apellidos_empleado: TextInputEditText
    private lateinit var ingreso_correo_empleado: TextInputEditText
    private lateinit var ingreso_telefono_empleado: TextInputEditText
    private lateinit var spinner_area: Spinner
    private lateinit var ingreso_contrasena_empleado: TextInputEditText
    private lateinit var imagen_atras : ImageView
    val imagen = ""
    val rol = "Empleado"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_formulario_empleado, container, false)

        this.ingreso_documento_Empleado = ll.findViewById(R.id.ingreso_documento_Empleado)
        this.ingreso_nombre_empleado = ll.findViewById(R.id.ingreso_nombre_empleado)
        this.ingrso_apellidos_empleado = ll.findViewById(R.id.ingrso_apellidos_empleado)
        this.ingreso_correo_empleado = ll.findViewById(R.id.ingreso_correo_empleado)
        this.ingreso_telefono_empleado = ll.findViewById(R.id.ingreso_telefono_empleado)
        this.spinner_area = ll.findViewById(R.id.spinner_area)
        this.ingreso_contrasena_empleado = ll.findViewById(R.id.ingreso_contrasena_empleado)
        this.imagen_atras = ll.findViewById(R.id.imagen_atras_ingreso_empleado)


        //Logica del spinner
        val spinnerData = arrayOf("Seleccione el area", "Ordeño", "Limpieza")

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val positionDisable = 0
        adapter.getView(positionDisable, null, spinner_area)?.isEnabled = true
        spinner_area.adapter = adapter

        imagen_atras.setOnClickListener {
            findNavController().navigate(R.id.action_formularioEmpleadoFragment_to_gestionEmpleadosFragment)

        }



        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonEnviar: Button = view.findViewById(R.id.boton_enviar_empleado)
        botonEnviar.setOnClickListener { clickAddEmpleado(view) }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = parent?.getItemAtPosition(position).toString()
        if (selectedItem == "Seleccione el area") {
            Toast.makeText(
                requireContext(),
                "Por favor, selecciona una opción válida",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun clickAddEmpleado(view: View) {
        val url = "http://192.168.252.77:8080/agregarUsuario"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(
                    requireContext(),
                    "Usuario ingresado exitosamente",
                    Toast.LENGTH_LONG
                ).show()

                val navController = Navigation.findNavController(
                    requireActivity(),
                    R.id.nav_host_fragment_container
                )
                navController.navigate(R.id.action_formularioEmpleadoFragment_to_gestionEmpleadosFragment)

            }, Response.ErrorListener {
                Toast.makeText(requireContext(), "Usuario no agregado", Toast.LENGTH_LONG).show()
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] =
                    "application/json" // Establece el tipo de contenido como JSON
                return headers
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }

            override fun getBody(): ByteArray {
                val parametros = JSONObject()
                parametros.put("id", ingreso_documento_Empleado?.text.toString())
                parametros.put("nombre", ingreso_nombre_empleado?.text.toString())
                parametros.put("apellido", ingrso_apellidos_empleado?.text.toString())
                parametros.put("telefono", ingreso_telefono_empleado?.text.toString())
                parametros.put("correo", ingreso_correo_empleado?.text.toString())
                parametros.put("contrasena", ingreso_contrasena_empleado?.text.toString())
                parametros.put("rol", rol)
                parametros.put("area", spinner_area?.selectedItem.toString())
                parametros.put("imagen", imagen)

                Log.d("error", "$parametros")
                Log.d("error", "error")
                return parametros.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }
}