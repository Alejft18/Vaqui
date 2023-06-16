package com.example.vaqui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class actualizarLecherasFragment : Fragment() {

/*    private lateinit var id_lechera: TextView
    private lateinit var actualizar_fecha_revisión_lechera: EditText
    private lateinit var actualizar_peso_lechera: TextInputEditText
    private lateinit var actualizar_partos_lechera: TextInputEditText
    private lateinit var actualizar_ultpartaro_lechera: EditText
    private lateinit var actualizar_ordeño_lechera: EditText
    private lateinit var actualizar_litros_lechera: TextInputEditText

    private lateinit var boton_actualizar_lechera: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_actualizar_lecheras, container, false)

        this.id_lechera = ll.findViewById(R.id.id_lechera)
        this.actualizar_fecha_revisión_lechera = ll.findViewById(R.id.actualizar_fecha_revisión_lechera)
        this.actualizar_peso_lechera = ll.findViewById(R.id.actualizar_peso_lechera)
        this.actualizar_partos_lechera = ll.findViewById(R.id.actualizar_partos_lechera)
        this.actualizar_ultpartaro_lechera = ll.findViewById(R.id.actualizar_ultpartaro_lechera)
        this.actualizar_ordeño_lechera = ll.findViewById(R.id.actualizar_ordeño_lechera)
        this.actualizar_litros_lechera = ll.findViewById(R.id.actualizar_litros_lechera)

        this.boton_actualizar_lechera = ll.findViewById(R.id.boton_actualizar_lechera)


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

        actualizar_fecha_revisión_lechera.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        actualizar_ultpartaro_lechera.apply {
            isFocusable = false
            isClickable = true
        }

        actualizar_ordeño_lechera.apply {
            isFocusable = false
            isClickable = true
        }

        actualizar_fecha_revisión_lechera.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        actualizar_ultpartaro_lechera.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        actualizar_ordeño_lechera.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker3,myCalendar3.get(Calendar.YEAR),myCalendar3.get(Calendar.MONTH),myCalendar3.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        boton_actualizar_lechera.setOnClickListener {
            val url="http://192.168.226.77/phpVaqui/actualizar_bovino_lechera.php"
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
                    parametros.put("id", id_lechera?.text.toString())
                    parametros.put("fecha_revisión",actualizar_fecha_revisión_lechera?.text.toString())
                    parametros.put("peso",actualizar_peso_lechera?.text.toString())
                    parametros.put("partos",actualizar_partos_lechera?.text.toString())
                    parametros.put("fecha_último_parto",actualizar_ultpartaro_lechera?.text.toString())
                    parametros.put("fecha_ordeño",actualizar_ordeño_lechera?.text.toString())
                    parametros.put("litros",actualizar_litros_lechera?.text.toString())

                    return parametros
                }
            }
            queue.add(resultPost)
        }

        return ll

    }

    private fun updateLable(myCalendar: Calendar){
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_fecha_revisión_lechera.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable2(myCalendar: Calendar){
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_ordeño_lechera.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable3(myCalendar: Calendar){
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_ultpartaro_lechera.setText(sdf.format(myCalendar.time))
    }*/

}