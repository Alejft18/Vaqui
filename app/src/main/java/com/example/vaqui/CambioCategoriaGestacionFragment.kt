package com.example.vaqui

import android.annotation.SuppressLint
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

class CambioCategoriaGestacionFragment : Fragment() {
    private lateinit var imagen_atras_cambio_a_gestacion : ImageView
    private lateinit var cambio_gestacion_peso : TextInputEditText
    private lateinit var cambio_gestacion_fecha_insemi : TextInputEditText
    private lateinit var cambio_gestacion_fecha_aproxParto : TextInputEditText
    private lateinit var cambio_gestacion_fecha_ulti_parto : TextInputEditText
    private lateinit var cambio_gestacion_fecha_revi : TextInputEditText
    private lateinit var id_cambio_gestacion : TextView
    private lateinit var funcion_eliminar_gestacion : TextView
    private lateinit var boton_cambio_a_gestacion : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val idCambioGestacion = arguments?.getString("id_cambio")
        val funcionEliminar = arguments?.getString("funcion_eliminar")

        val ll = inflater.inflate(R.layout.fragment_cambio_categoria_gestacion, container, false)
        this.imagen_atras_cambio_a_gestacion = ll.findViewById(R.id.imagen_atras_cambio_a_gestacion)
        this.cambio_gestacion_peso = ll.findViewById(R.id.cambio_gestacion_peso)
        this.cambio_gestacion_fecha_insemi = ll.findViewById(R.id.cambio_gestacion_fecha_insemi)
        this.cambio_gestacion_fecha_aproxParto = ll.findViewById(R.id.cambio_gestacion_fecha_aproxParto)
        this.cambio_gestacion_fecha_ulti_parto = ll.findViewById(R.id.cambio_gestacion_fecha_ulti_parto)
        this.cambio_gestacion_fecha_revi = ll.findViewById(R.id.cambio_gestacion_fecha_revi)
        this.id_cambio_gestacion = ll.findViewById(R.id.id_cambio_gestacion)
        this.funcion_eliminar_gestacion = ll.findViewById(R.id.funcion_eliminar_gestacion)
        this.boton_cambio_a_gestacion = ll.findViewById(R.id.boton_cambio_a_gestacion)

        id_cambio_gestacion.text = idCambioGestacion
        funcion_eliminar_gestacion.text = funcionEliminar


        imagen_atras_cambio_a_gestacion.setOnClickListener {
            findNavController().navigate(R.id.action_cambioCategoriaGestacionFragment_to_elegirCambioCategoriaTerneroFragment)
        }

        //logica de las fechas (datePicker)
        val myCalendar= Calendar.getInstance()
        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }

        val myCalendar2= Calendar.getInstance()
        val datePicker2= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar2.set(Calendar.YEAR,year)
            myCalendar2.set(Calendar.MONTH,month)
            myCalendar2.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable2(myCalendar2)
        }

        val myCalendar3= Calendar.getInstance()
        val datePicker3= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar3.set(Calendar.YEAR,year)
            myCalendar3.set(Calendar.MONTH,month)
            myCalendar3.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable3(myCalendar3)
        }

        val myCalendar4= Calendar.getInstance()
        val datePicker4= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar4.set(Calendar.YEAR,year)
            myCalendar4.set(Calendar.MONTH,month)
            myCalendar4.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable4(myCalendar4)
        }

        // Deshabilito el teclado en las fechas
        cambio_gestacion_fecha_insemi.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        cambio_gestacion_fecha_aproxParto.apply {
            isFocusable = false
            isClickable = true
        }

        cambio_gestacion_fecha_ulti_parto.apply {
            isFocusable = false
            isClickable = true
        }

        cambio_gestacion_fecha_revi.apply {
            isFocusable = false
            isClickable = true
        }

        cambio_gestacion_fecha_insemi.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(
                Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate= Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        cambio_gestacion_fecha_aproxParto.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(
                Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH))

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        cambio_gestacion_fecha_ulti_parto.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker3,myCalendar3.get(Calendar.YEAR),myCalendar3.get(
                Calendar.MONTH),myCalendar3.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate= Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        cambio_gestacion_fecha_revi.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker4,myCalendar4.get(Calendar.YEAR),myCalendar4.get(
                Calendar.MONTH),myCalendar4.get(Calendar.DAY_OF_MONTH))

            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate= Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        boton_cambio_a_gestacion.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cambio de categoria")
            builder.setMessage("¿Seguro que desea realizar el cambio de categoria para el bovino ${idCambioGestacion}?")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                clickCambioGestacion()


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
        cambio_gestacion_fecha_insemi.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable2(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        cambio_gestacion_fecha_aproxParto.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable3(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es", "CO"))
        cambio_gestacion_fecha_ulti_parto.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable4(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        cambio_gestacion_fecha_revi.setText(sdf.format(myCalendar.time))

    }

    private fun clickCambioGestacion() {
        val url= "https://vaquijpa2-production.up.railway.app/agregarGestacion/${id_cambio_gestacion.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca en gestacion agregada ingresada exitosamente", Toast.LENGTH_LONG).show()
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
                parametros.put("id",id_cambio_gestacion.toString())
                parametros.put("peso_kilos",cambio_gestacion_peso?.text.toString())
                parametros.put("fecha_inseminacion",cambio_gestacion_fecha_insemi?.text.toString())
                parametros.put("fecha_aprox_parto",cambio_gestacion_fecha_aproxParto?.text.toString())
                parametros.put("fecha_ultimo_parto",cambio_gestacion_fecha_ulti_parto?.text.toString())
                parametros.put("fecha_revision",cambio_gestacion_fecha_revi?.text.toString())
                parametros.put("categoria","gestacion")

                Log.d("error", "$parametros")
                Log.d("error", "error")

                return parametros.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }

    private fun eliminarCategoria() {
        val url = "https://vaquijpa2-production.up.railway.app/${funcion_eliminar_gestacion.text}${id_cambio_gestacion.text}"
        val queue = Volley.newRequestQueue(requireContext())

        val request = StringRequest(
            Request.Method.DELETE, url,
            { response ->
                println("Eliminacion exitosa")
                findNavController().navigate(R.id.action_cambioCategoriaGestacionFragment_to_categorias)
            }
        ) { error ->
            println("Error en la eliminacion: " + error.message)
        }
        queue.add(request)

    }
}