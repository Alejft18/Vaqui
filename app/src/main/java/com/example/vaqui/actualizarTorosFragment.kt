package com.example.vaqui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class actualizarTorosFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var id_toro: TextView
    private lateinit var actualizar_peso_toro: TextInputEditText
    private lateinit var actualizar_fecha_revisión_toro: TextInputEditText
    private lateinit var actualizar_vacas_montadas_toro: TextInputEditText
    private lateinit var actualizar_extraccion_toro: TextInputEditText

    private lateinit var boton_actualizar_toro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_actualizar_toros, container, false)

        this.id_toro = ll.findViewById(R.id.id_toro)
        this.actualizar_fecha_revisión_toro = ll.findViewById(R.id.actualizar_fecha_revisión_toro)
        this.actualizar_peso_toro = ll.findViewById(R.id.actualizar_peso_toro)
        this.actualizar_vacas_montadas_toro = ll.findViewById(R.id.actualizar_vacas_montadas_toro)
        this.actualizar_extraccion_toro = ll.findViewById(R.id.actualizar_extraccion_toro)

        this.boton_actualizar_toro = ll.findViewById(R.id.boton_actualizar_toro)

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

        actualizar_fecha_revisión_toro.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        actualizar_extraccion_toro.apply {
            isFocusable = false
            isClickable = true
        }

        actualizar_fecha_revisión_toro.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }


        actualizar_extraccion_toro.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        boton_actualizar_toro.setOnClickListener {
            val url="http://192.168.226.77/phpVaqui/actualizar_bovino_toro.php"
            val queue = Volley.newRequestQueue(getActivity())
            val resultPost= object  : StringRequest(
                Request.Method.POST,url,
                Response.Listener { response->
                    Toast.makeText(getActivity(), "Se modificó la vaca general", Toast.LENGTH_LONG).show()
                },{error->
                    Toast.makeText(getActivity(), " No Se modificó la vaca general", Toast.LENGTH_LONG).show()
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val parametros  = HashMap<String,String>()
                    //id posicionar
                    parametros.put("id", id_toro?.text.toString())
                    parametros.put("fecha_revisión",actualizar_fecha_revisión_toro?.text.toString())
                    parametros.put("peso",actualizar_peso_toro?.text.toString())
                    parametros.put("vacas montadas",actualizar_vacas_montadas_toro?.text.toString())
                    parametros.put("fecha_extracción",actualizar_extraccion_toro?.text.toString())

                    return parametros
                }
            }
            queue.add(resultPost)
        }

        return ll

    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_fecha_revisión_toro.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable2(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_extraccion_toro.setText(sdf.format(myCalendar.time))
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}