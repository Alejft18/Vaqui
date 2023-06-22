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

class actualizarTernerosFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var id_ternero: TextView
    private lateinit var actualizar_fecha_revisión_ternero: TextInputEditText
    private lateinit var actualizar_peso_ternero: TextInputEditText

    private lateinit var boton_actualizar_ternero: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_actualizar_terneros, container, false)

        this.id_ternero = ll.findViewById(R.id.id_ternero)
        this.actualizar_fecha_revisión_ternero = ll.findViewById(R.id.actualizar_fecha_revisión_ternero)
        this.actualizar_peso_ternero = ll.findViewById(R.id.actualizar_peso_ternero)

        this.boton_actualizar_ternero = ll.findViewById(R.id.boton_actualizar_ternero)

        val myCalendar= Calendar.getInstance()
        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }

        actualizar_fecha_revisión_ternero.apply {
            isFocusable = false
            isClickable = true
        }

        actualizar_fecha_revisión_ternero.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        boton_actualizar_ternero.setOnClickListener {
            val url="http://192.168.226.77/phpVaqui/actualizar_bovino_ternero.php"
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
                    parametros.put("id", id_ternero?.text.toString())
                    parametros.put("fecha_revisión",actualizar_fecha_revisión_ternero?.text.toString())
                    parametros.put("peso",actualizar_peso_ternero?.text.toString())

                    return parametros
                }
            }
            queue.add(resultPost)
        }

        return ll

    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_fecha_revisión_ternero.setText(sdf.format(myCalendar.time))
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}