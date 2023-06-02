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
import java.util.Calendar
import java.util.HashMap
import java.util.Locale

class fragment_formulario_secado : Fragment() {
    private lateinit var ingreso_fechaUltParto_secado: TextInputEditText
    private lateinit var ingreso_peso_secado: TextInputEditText
    private lateinit var ingreso_fechaRevision_secado: TextInputEditText
    private lateinit var ingreso_fechaOrdenio_secado: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonEnviar: Button = view.findViewById(R.id.boton_enviar_secado)
        botonEnviar.setOnClickListener { clickAddSecado(view) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_formulario_secado, container, false)

        ingreso_fechaUltParto_secado = view.findViewById(R.id.ingreso_fechaUltParto_secado)
        ingreso_peso_secado = view.findViewById(R.id.ingreso_peso_secado)
        ingreso_fechaRevision_secado = view.findViewById(R.id.ingreso_fechaRevision_secado)
        ingreso_fechaOrdenio_secado = view.findViewById(R.id.ingreso_fechaOrdenio_secado)


        //logica de las fechas (datePicker)
        val myCalendar= Calendar.getInstance()

        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }

        // Deshabilito el teclado en las fechas
        ingreso_fechaUltParto_secado.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        ingreso_fechaRevision_secado.apply {
            isFocusable = false
            isClickable = true
        }

        ingreso_fechaOrdenio_secado.apply {
            isFocusable = false
            isClickable = true
        }


        ingreso_fechaUltParto_secado.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        ingreso_fechaRevision_secado.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        ingreso_fechaOrdenio_secado.setOnClickListener {
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
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        ingreso_fechaUltParto_secado.setText(sdf.format(myCalendar.time))
        ingreso_fechaRevision_secado.setText(sdf.format(myCalendar.time))
        ingreso_fechaOrdenio_secado.setText(sdf.format(myCalendar.time))
    }

    private fun clickAddSecado(view: View) {
        val url="http://192.168.226.77/phpVaqui/agregar_secado.php"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca en secado ingresada exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Vaca en secado no agregada", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()

                parametros.put("fecha_ultParto",ingreso_fechaUltParto_secado?.text.toString())
                parametros.put("peso_kilos",ingreso_peso_secado?.text.toString())
                parametros.put("fecha_revision",ingreso_fechaRevision_secado?.text.toString())
                parametros.put("fecha_ordeño",ingreso_fechaOrdenio_secado?.text.toString())

                Log.d("error", "$parametros")
                Log.d("error", "error")
                return parametros

            }
        }
        queue.add(resultadoPost)

    }

}