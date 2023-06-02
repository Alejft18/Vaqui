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

class fragment_formulario_lecheras : Fragment() {
    private lateinit var ingreso_litros_lechera: TextInputEditText
    private lateinit var ingreso_fecha_ordeno_lechera: TextInputEditText
    private lateinit var ingreso_peso_lechera: TextInputEditText
    private lateinit var ingreso_fecha_revi_lechera: TextInputEditText
    private lateinit var fecha_parto_lechera: TextInputEditText
    private lateinit var cantidad_partos_lechera: TextInputEditText


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
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        fecha_parto_lechera.setOnClickListener {
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
        ingreso_fecha_ordeno_lechera.setText(sdf.format(myCalendar.time))
        ingreso_fecha_revi_lechera.setText(sdf.format(myCalendar.time))
        fecha_parto_lechera.setText(sdf.format(myCalendar.time))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonEnviar: Button= view.findViewById(R.id.boton_enviar_lechera)
        botonEnviar.setOnClickListener { clickAddLechera(view) }
    }

    private fun clickAddLechera(view: View) {
        val url="http://192.168.226.77/phpVaqui/agregar_lechera.php"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca lechera ingresada exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Vaca lechera no agregada", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()

                parametros.put("litros_producidos",ingreso_litros_lechera?.text.toString())
                parametros.put("fecha_ordeno",ingreso_fecha_ordeno_lechera?.text.toString())
                parametros.put("peso_kilos",ingreso_peso_lechera?.text.toString())
                parametros.put("fecha_revision",ingreso_fecha_revi_lechera?.text.toString())
                parametros.put("fecha_parto",fecha_parto_lechera?.text.toString())
                parametros.put("cant_partos",cantidad_partos_lechera?.text.toString())

                Log.d("error", "$parametros")
                Log.d("error", "error")
                return parametros

            }
        }
        queue.add(resultadoPost)

    }

}