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
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*


class fragment_formulario_gestacion : Fragment() {
    private lateinit var ingreso_peso_gestacion : TextInputEditText
    private lateinit var ingreso_fecha_inseminacionGesta : TextInputEditText
    private lateinit var ingreso_fecha_aproxParto_gesta : TextInputEditText
    private lateinit var ingreso_fecha_ulti_parto_gesta : TextInputEditText
    private lateinit var ingreso_fecha_revi_gesta : TextInputEditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonEnviar: Button = view.findViewById(R.id.boton_enviar_gestacion)
        botonEnviar.setOnClickListener { clickAddGestacion(view) }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll= inflater.inflate(R.layout.fragment_formulario_gestacion, container, false)

        ingreso_peso_gestacion=ll.findViewById(R.id.ingreso_peso_gestacion)
        ingreso_fecha_inseminacionGesta=ll.findViewById(R.id.ingreso_fecha_inseminacionGesta)
        ingreso_fecha_aproxParto_gesta=ll.findViewById(R.id.ingreso_fecha_aproxParto_gesta)
        ingreso_fecha_ulti_parto_gesta=ll.findViewById(R.id.ingreso_fecha_ulti_parto_gesta)
        ingreso_fecha_revi_gesta=ll.findViewById(R.id.ingreso_fecha_revi_gesta)


        //logica de las fechas (datePicker)
        val myCalendar= Calendar.getInstance()

        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
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
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        ingreso_fecha_ulti_parto_gesta.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        ingreso_fecha_revi_gesta.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))

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
        ingreso_fecha_aproxParto_gesta.setText(sdf.format(myCalendar.time))
        ingreso_fecha_ulti_parto_gesta.setText(sdf.format(myCalendar.time))
        ingreso_fecha_revi_gesta.setText(sdf.format(myCalendar.time))

    }

    private fun clickAddGestacion(view: View) {
        val url="http://192.168.226.77/phpVaqui/agregar_gestacion.php"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca gestacion ingresada exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Vaca gestacion no agregada", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()

                parametros.put("peso_kilos",ingreso_peso_gestacion?.text.toString())
                parametros.put("fecha_inseminacion",ingreso_fecha_inseminacionGesta?.text.toString())
                parametros.put("fecha_aproxParto",ingreso_fecha_aproxParto_gesta?.text.toString())
                parametros.put("fecha_ultimoParto",ingreso_fecha_ulti_parto_gesta?.text.toString())
                parametros.put("fecha_revision",ingreso_fecha_revi_gesta?.text.toString())

                Log.d("error", "$parametros")
                Log.d("error", "error")
                return parametros

            }
        }
        queue.add(resultadoPost)

    }


}