package com.example.vaqui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class fragment_formulario_gestacion : Fragment() {
    private lateinit var ingreso_peso_gestacion : TextInputEditText
    private lateinit var ingreso_fecha_inseminacionGesta : TextInputEditText
    private lateinit var ingreso_fecha_aproxParto_gesta : TextInputEditText
    private lateinit var ingreso_fecha_ulti_parto_gesta : TextInputEditText
    private lateinit var ingreso_fecha_revi_gesta : TextInputEditText
    private lateinit var imagen_atras_ingreso_gestacion : ImageView
    private var ultimoId: Int = 0
    private val categoria = "gestacion"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonEnviar: Button = view.findViewById(R.id.boton_enviar_gestacion)
        botonEnviar.setOnClickListener {
            obtenerUltimoIdGeneral()
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_formulario_gestacion, container, false)
        this.ingreso_peso_gestacion = ll.findViewById(R.id.ingreso_peso_gestacion)
        this.ingreso_fecha_inseminacionGesta = ll.findViewById(R.id.ingreso_fecha_inseminacionGesta)
        this.ingreso_fecha_aproxParto_gesta = ll.findViewById(R.id.ingreso_fecha_aproxParto_gesta)
        this.ingreso_fecha_ulti_parto_gesta = ll.findViewById(R.id.ingreso_fecha_ulti_parto_gesta)
        this.ingreso_fecha_revi_gesta = ll.findViewById(R.id.ingreso_fecha_revi_gesta)
        this.imagen_atras_ingreso_gestacion = ll.findViewById(R.id.imagen_atras_ingresar_gestacion)

        imagen_atras_ingreso_gestacion.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_formulario_gestacion_to_elegir_categoria)
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
        ingreso_fecha_inseminacionGesta.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        ingreso_fecha_aproxParto_gesta.apply {
            isFocusable = false
            isClickable = true
        }

        ingreso_fecha_ulti_parto_gesta.apply {
            isFocusable = false
            isClickable = true
        }

        ingreso_fecha_revi_gesta.apply {
            isFocusable = false
            isClickable = true
        }

        ingreso_fecha_inseminacionGesta.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        ingreso_fecha_aproxParto_gesta.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH))

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        ingreso_fecha_ulti_parto_gesta.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker3,myCalendar3.get(Calendar.YEAR),myCalendar3.get(Calendar.MONTH),myCalendar3.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        ingreso_fecha_revi_gesta.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker4,myCalendar4.get(Calendar.YEAR),myCalendar4.get(Calendar.MONTH),myCalendar4.get(Calendar.DAY_OF_MONTH))

            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }



        return ll
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        ingreso_fecha_inseminacionGesta.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable2(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        ingreso_fecha_aproxParto_gesta.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable3(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es", "CO"))
        ingreso_fecha_ulti_parto_gesta.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable4(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        ingreso_fecha_revi_gesta.setText(sdf.format(myCalendar.time))

    }

    private fun obtenerUltimoIdGeneral(){
        val url = "https://vaquijpa2-production.up.railway.app/ultimoIdGeneral"
        val queue = Volley.newRequestQueue(requireContext())

        val request = JsonObjectRequest(Request.Method.GET, url,null,
            { response ->

                ultimoId = response.getInt("id")
                println(ultimoId)

                clickAddGestacion()
            },
            { error ->
                Toast.makeText(requireContext(), "Error al obtener el último ID", Toast.LENGTH_LONG).show()
            })

        queue.add(request)
    }

    private fun clickAddGestacion() {
        val url="https://vaquijpa2-production.up.railway.app/agregarGestacion/$ultimoId"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca gestacion ingresada exitosamente", Toast.LENGTH_LONG).show()
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

                parametros.put("id",ultimoId)
                parametros.put("peso_kilos",ingreso_peso_gestacion?.text.toString())
                parametros.put("fecha_inseminacion",ingreso_fecha_inseminacionGesta?.text.toString())
                parametros.put("fecha_aprox_parto",ingreso_fecha_aproxParto_gesta?.text.toString())
                parametros.put("fecha_ultimo_parto",ingreso_fecha_ulti_parto_gesta?.text.toString())
                parametros.put("fecha_revision",ingreso_fecha_revi_gesta?.text.toString())
                parametros.put("categoria",categoria)

                Log.d("error", "$parametros")
                Log.d("error", "error")

                return parametros.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
        findNavController().navigate(R.id.action_fragment_formulario_gestacion_to_gestion2)

    }


}