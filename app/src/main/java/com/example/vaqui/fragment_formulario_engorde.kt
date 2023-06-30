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


class fragment_formulario_engorde : Fragment() {
    private lateinit var ingreso_peso_engorde: TextInputEditText
    private lateinit var ingreso_fecha_revi_engorde: TextInputEditText
    private lateinit var ingreso_alimento: TextInputEditText
    private var ultimoId: Int =0
    private val categoria = "engorde"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonEnviar: Button = view.findViewById(R.id.boton_enviar_engorde)
        botonEnviar.setOnClickListener {
            obtenerUltimoIdGeneral()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_formulario_engorde, container, false)
        ingreso_peso_engorde=view.findViewById(R.id.ingreso_peso_engorde)
        ingreso_fecha_revi_engorde=view.findViewById(R.id.ingreso_fecha_revi_engorde)
        ingreso_alimento=view.findViewById(R.id.ingreso_alimento)

        val myCalendar= Calendar.getInstance()

        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }


        ingreso_fecha_revi_engorde.apply {
            isFocusable = false
            isClickable = true
        }



        ingreso_fecha_revi_engorde.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))

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
        ingreso_fecha_revi_engorde.setText(sdf.format(myCalendar.time))

    }

    private fun obtenerUltimoIdGeneral(){
        val url = "http://192.168.234.77:8080/ultimoIdGeneral"
        val queue = Volley.newRequestQueue(requireContext())

        val request = JsonObjectRequest(Request.Method.GET, url,null,
            { response ->

                ultimoId = response.getInt("id")
                println(ultimoId)

                clickAddEngorde()
            },
            { error ->
                Toast.makeText(requireContext(), "Error al obtener el último ID", Toast.LENGTH_LONG).show()
            })

        queue.add(request)
    }

    private fun clickAddEngorde() {
        val url= "http://192.168.234.77:8080/ingresarEngorde/$ultimoId"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca engorde ingresada exitosamente", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_fragment_formulario_engorde_to_gestion)

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Vaca engorde no agregada", Toast.LENGTH_LONG).show()
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
                parametros.put("peso_kilos",ingreso_peso_engorde?.text.toString())
                parametros.put("fecha_revision",ingreso_fecha_revi_engorde?.text.toString())
                parametros.put("alimento",ingreso_alimento?.text.toString())
                parametros.put("categoria",categoria)

                Log.d("error", "$parametros")
                Log.d("error", "error")

                return parametros.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }
}