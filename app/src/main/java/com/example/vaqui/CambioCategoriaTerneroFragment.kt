package com.example.vaqui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.HashMap
import java.util.Locale

class CambioCategoriaTerneroFragment : Fragment() {
    private lateinit var imagen_atras_cambiar_ternero : ImageView
    private lateinit var cambio_id_madre_ternero : TextInputEditText
    private lateinit var cambio_ternero_peso : TextInputEditText
    private lateinit var cambio_ternero_fecha_revi : TextInputEditText
    private lateinit var id_cambio_ternero : TextView
    private lateinit var funcion_eliminar_ternero : TextView
    private lateinit var boton_cambio_a_ternero : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val idCambioTernero = arguments?.getString("id_cambio")
        val funcionEliminar = arguments?.getString("funcion_eliminar")


        val ll = inflater.inflate(R.layout.fragment_cambio_categoria_ternero, container, false)
        this.imagen_atras_cambiar_ternero = ll.findViewById(R.id.imagen_atras_cambiar_ternero)
        this.cambio_id_madre_ternero = ll.findViewById(R.id.cambio_id_madre_ternero)
        this.cambio_ternero_peso = ll.findViewById(R.id.cambio_ternero_peso)
        this.cambio_ternero_fecha_revi = ll.findViewById(R.id.cambio_ternero_fecha_revi)
        this.boton_cambio_a_ternero = ll.findViewById(R.id.boton_cambio_a_ternero)
        this.id_cambio_ternero = ll.findViewById(R.id.id_cambio_ternero)
        this.funcion_eliminar_ternero = ll.findViewById(R.id.funcion_eliminar_ternero)

        id_cambio_ternero.text = idCambioTernero
        funcion_eliminar_ternero.text = funcionEliminar

        imagen_atras_cambiar_ternero.setOnClickListener {
            findNavController().navigate(R.id.action_cambioCategoriaTerneroFragment_to_categorias)
        }

        //logica de las fechas (datePicker)
        val myCalendar= Calendar.getInstance()

        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }

        // Deshabilito el teclado en las fechas
        cambio_ternero_fecha_revi.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        cambio_ternero_fecha_revi.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }


        boton_cambio_a_ternero.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cambio de categoria")
            builder.setMessage("¿Seguro que desea realizar el cambio de categoria para el bovino ${idCambioTernero}?")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                clickCambioTernero()
            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return ll
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        cambio_ternero_fecha_revi.setText(sdf.format(myCalendar.time))
    }


    private fun clickCambioTernero() {
        val url= "http://192.168.180.187:8080/agregarTernero/${id_cambio_ternero.text}/${cambio_id_madre_ternero.text.toString()}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Ternero agregado ingresada exitosamente", Toast.LENGTH_LONG).show()
                eliminarCategoria()

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Vaca gestacion no agregada", Toast.LENGTH_LONG).show()
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
            override fun getBodyContentType(): String {
                return "application/json"
            }
            override fun getBody(): ByteArray {
                val parametros = JSONObject()
                parametros.put("id",id_cambio_ternero.text.toString())
                parametros.put("id_madre",cambio_id_madre_ternero?.text.toString().toInt())
                parametros.put("peso_kilos",cambio_ternero_peso?.text.toString().toDouble())
                parametros.put("fecha_revision",cambio_ternero_fecha_revi?.text.toString())
                parametros.put("categoria","ternero")

                Log.d("error", "$parametros")
                Log.d("error", "error")
                return parametros.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }

    private fun eliminarCategoria() {
        val url = "http://192.168.180.187:8080/${funcion_eliminar_ternero.text}${id_cambio_ternero.text}"
        val queue = Volley.newRequestQueue(requireContext())

        val request = StringRequest(
            Request.Method.DELETE, url,
            { response ->
                println("Eliminacion exitosa")
                findNavController().navigate(R.id.action_cambioCategoriaTerneroFragment_to_categorias)
            }
        ) { error ->
            println("Error en la eliminacion: " + error.message)
        }
        queue.add(request)

    }

}