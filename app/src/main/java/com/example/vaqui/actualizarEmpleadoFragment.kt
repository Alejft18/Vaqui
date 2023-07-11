package com.example.vaqui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import java.util.HashMap

class actualizarEmpleadoFragment : Fragment(), AdapterView.OnItemSelectedListener{

    private lateinit var id_empleado : TextView
    private lateinit var actualizar_nombre : TextInputEditText
    private lateinit var actualizar_apellido : TextInputEditText
    private lateinit var actualizar_telefono : TextInputEditText
    private lateinit var actualizar_correo : TextInputEditText
    private lateinit var actualizar_contrasena : TextInputEditText
    private lateinit var actualizar_area_empleado: Spinner
    private lateinit var imagen_atras_actualizar_empleado : ImageView
    private val rol = "empleado"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // traigo los datos del detallle del empleado
        val idEmpelado = arguments?.getString("id_empleado")
        val nombre = arguments?.getString("nombre_empleado")
        val apellidos = arguments?.getString("apellidos_empleado")
        val telefono = arguments?.getString("telefono_empleado")
        val correo = arguments?.getString("correo_empleado")
        val contrasena = arguments?.getString("contrasena_empleado")
        val area = arguments?.getString("area_empleado")


        val ll = inflater.inflate(R.layout.fragment_actualizar_empleado, container, false)
        this.id_empleado = ll.findViewById(R.id.id_empleado)
        this.actualizar_nombre = ll.findViewById(R.id.actualizar_nombre_empleado)
        this.actualizar_apellido = ll.findViewById(R.id.actualizar_apellido)
        this.actualizar_telefono = ll.findViewById(R.id.actualizar_numero_cel)
        this.actualizar_correo = ll.findViewById(R.id.actualizar_correo_empleado)
        this.actualizar_contrasena = ll.findViewById(R.id.actualizar_contrasena_empleado)
        this.actualizar_area_empleado = ll.findViewById(R.id.actualizar_area_empleado)
        this.imagen_atras_actualizar_empleado = ll.findViewById(R.id.imagen_atras_actualizar_empleado)

        imagen_atras_actualizar_empleado.setOnClickListener {
            findNavController().navigate(R.id.action_actualizarEmpleadoFragment_to_gestionEmpleadosFragment)
        }

        //Logica del spinner
        val spinnerData = arrayOf("Seleccione el area", "Ordeño", "Limpieza")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val positionDisable = 0
        adapter.getView(positionDisable, null, actualizar_area_empleado)?.isEnabled = true
        actualizar_area_empleado.adapter = adapter


        //sobrepongo los datos del empleado en el formulario de actualizar
        id_empleado.text = idEmpelado
        actualizar_nombre.setText(nombre)
        actualizar_apellido.setText(apellidos)
        actualizar_telefono.setText(telefono)
        actualizar_correo.setText(correo)
        actualizar_contrasena.setText(contrasena)
        val areaPosition = spinnerData.indexOf(area)
        if (areaPosition >= 0) {
            actualizar_area_empleado.setSelection(areaPosition)
        }

        actualizar_contrasena.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("¿Deseas cambiar la contraseña de ${actualizar_nombre.text}?")
            builder.setMessage("Si cambias la contraseña de ${actualizar_nombre.text} recuerda en notificarle")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botonEnviar: Button = view.findViewById(R.id.boton_actualizar_empleado)
        botonEnviar.setOnClickListener { clickUpdateEmpleado(view) }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = parent?.getItemAtPosition(position).toString()
        if (selectedItem == "Seleccione el area") {
            Toast.makeText(
                requireContext(),
                "Por favor, selecciona una opción válida", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    private fun clickUpdateEmpleado(view: View) {
        val url="http://192.168.180.187:8080/actualizarUsuario"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.PUT, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Empleado actualizado exitosamente", Toast.LENGTH_SHORT).show()

                val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                navController.navigate(R.id.action_actualizarEmpleadoFragment_to_gestionEmpleadosFragment)

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Bovino no actualizado", Toast.LENGTH_LONG).show()
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json" // Establece el tipo de contenido como JSON
                return headers
            }
            override fun getBodyContentType(): String {
                return "application/json"
            }
            override fun getBody(): ByteArray {
                val params = JSONObject()
                params.put("id",id_empleado.text)
                params.put("nombre",actualizar_nombre?.text.toString())
                params.put("apellido",actualizar_apellido?.text.toString())
                params.put("telefono",actualizar_telefono?.text.toString())
                params.put("correo",actualizar_correo?.text.toString())
                params.put("contrasena",actualizar_contrasena?.text.toString())
                params.put("rol",rol)
                params.put("area",actualizar_area_empleado?.selectedItem.toString())

                return  params.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }


}