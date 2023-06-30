package com.example.vaqui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
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

class fragment_formulario_lecheras : Fragment() {
    private lateinit var ingreso_litros_lechera: TextInputEditText
    private lateinit var ingreso_fecha_ordeno_lechera: TextInputEditText
    private lateinit var ingreso_peso_lechera: TextInputEditText
    private lateinit var ingreso_fecha_revi_lechera: TextInputEditText
    private lateinit var fecha_parto_lechera: TextInputEditText
    private lateinit var cantidad_partos_lechera: TextInputEditText
    private var ultimoId : Int = 0
    private val categoria = "lechera"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_formulario_lecheras, container, false)

        ingreso_litros_lechera=view.findViewById(R.id.ingreso_litros_lechera)
        ingreso_fecha_ordeno_lechera=view.findViewById(R.id.ingreso_fecha_ordeno_lechera)
        ingreso_peso_lechera=view.findViewById(R.id.ingreso_peso_lechera)
        ingreso_fecha_revi_lechera=view.findViewById(R.id.ingreso_fecha_revi_lechera)
        fecha_parto_lechera=view.findViewById(R.id.ingreso_fecha_parto_lechera)
        cantidad_partos_lechera=view.findViewById(R.id.ingreso_cantidad_partos_lechera)



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

        // Deshabilito el teclado en las fechas
        ingreso_fecha_ordeno_lechera.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        ingreso_fecha_revi_lechera.apply {
            isFocusable = false
            isClickable = true
        }

        fecha_parto_lechera.apply {
            isFocusable = false
            isClickable = true
        }


        ingreso_fecha_ordeno_lechera.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        ingreso_fecha_revi_lechera.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        fecha_parto_lechera.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker3,myCalendar3.get(Calendar.YEAR),myCalendar3.get(Calendar.MONTH),myCalendar3.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        return view
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        ingreso_fecha_ordeno_lechera.setText(sdf.format(myCalendar.time))

    }
    private fun updateLable2(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        ingreso_fecha_revi_lechera.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable3(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        fecha_parto_lechera.setText(sdf.format(myCalendar.time))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonEnviar: Button= view.findViewById(R.id.boton_enviar_lechera)
        botonEnviar.setOnClickListener { obtenerUltimoIdGeneral() }
    }

    private fun obtenerUltimoIdGeneral(){
        val url = "http://192.168.234.77:8080/ultimoIdGeneral"
        val queue = Volley.newRequestQueue(requireContext())

        val request = JsonObjectRequest(Request.Method.GET, url,null,
            { response ->

                ultimoId = response.getInt("id")
                println(ultimoId)

                clickAddLechera()
            },
            { error ->
                Toast.makeText(requireContext(), "Error al obtener el último ID", Toast.LENGTH_LONG).show()
            })

        queue.add(request)
    }

    private fun clickAddLechera() {
        val url="http://192.168.234.77:8080/agregarLecheras/$ultimoId"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca lechera ingresada exitosamente", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_fragment_formulario_lecheras_to_gestion)

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Vaca lechera no agregada", Toast.LENGTH_LONG).show()
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
                parametros.put("litros_producidos",ingreso_litros_lechera?.text.toString())
                parametros.put("fecha_ordeno",ingreso_fecha_ordeno_lechera?.text.toString())
                parametros.put("peso_kilos",ingreso_peso_lechera?.text.toString())
                parametros.put("fecha_revision",ingreso_fecha_revi_lechera?.text.toString())
                parametros.put("fecha_parto",fecha_parto_lechera?.text.toString())
                parametros.put("cant_partos",cantidad_partos_lechera?.text.toString())
                parametros.put("categoria",categoria)

                Log.d("error", "$parametros")
                Log.d("error", "error")

                return parametros.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }

}