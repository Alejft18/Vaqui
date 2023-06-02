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
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*


class fragment_formulario_sementales : Fragment() {
    private lateinit var ingreso_peso_semental: TextInputEditText
    private lateinit var ingreso_fecha_extraccion_toro: TextInputEditText
    private lateinit var ingreso_vacas_montadas_toro: TextInputEditText
    private lateinit var ingreso_fecha_revi_toro: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonEnviar: Button = view.findViewById(R.id.boton_enviar_toro)
        botonEnviar.setOnClickListener { clickAddToro(view) }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_formulario_sementales, container, false)

        ingreso_peso_semental=view.findViewById(R.id.ingreso_peso_semental)
        ingreso_fecha_extraccion_toro=view.findViewById(R.id.ingreso_fecha_extraccion_toro)
        ingreso_vacas_montadas_toro=view.findViewById(R.id.ingreso_vacas_montadas_toro)
        ingreso_fecha_revi_toro=view.findViewById(R.id.ingreso_fecha_extraccion_toro)


        //logica de las fechas (datePicker)
        val myCalendar= Calendar.getInstance()

        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }

        // Deshabilito el teclado en las fechas
        ingreso_fecha_extraccion_toro.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        ingreso_fecha_revi_toro.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }


        ingreso_fecha_extraccion_toro.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }


        ingreso_fecha_revi_toro.setOnClickListener {
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
        ingreso_fecha_extraccion_toro.setText(sdf.format(myCalendar.time))
        ingreso_fecha_revi_toro.setText(sdf.format(myCalendar.time))
    }



    private fun clickAddToro(view: View) {
        val url="http://192.168.95.187/phpVaqui/agregar_toro.php"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Toro ingresado exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Toro no agregado", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()

                parametros.put("peso_kilos",ingreso_peso_semental?.text.toString())
                parametros.put("fecha_extraccion",ingreso_fecha_extraccion_toro?.text.toString())
                parametros.put("vacas_montadas",ingreso_vacas_montadas_toro?.text.toString())
                parametros.put("fecha_revision",ingreso_fecha_revi_toro?.text.toString())

                Log.d("error", "$parametros")
                Log.d("error", "error")
                return parametros

            }
        }
        queue.add(resultadoPost)

    }


}