package com.example.vaqui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class actualizarSecadoFragment : Fragment() {

    private lateinit var id_secado: TextView
    private lateinit var actualizar_fecha_revisión_secado : TextInputEditText
    private lateinit var actualizar_peso_secado : TextInputEditText
    private lateinit var actualizar_fecha_ordeno_secado : TextInputEditText
    private lateinit var actualizar_ultpartaro_secado: TextInputEditText
    private lateinit var imagen_atras_actualizar_secado : ImageView
    private val categoria = "secado"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //traigo la informacion de datos_secado()
        val idSecado = arguments?.getString("id_secado")
        val fechaRevision =  arguments?.getString("fecha_revision")
        val pesoSecado = arguments?.getString("peso_secado")
        val fechaOrdeno = arguments?.getString("fecha_ordeno_secado")
        val fechaUltiParto = arguments?.getString("fecha_ultiParto")

        val ll = inflater.inflate(R.layout.fragment_actualizar_secado, container, false)

        this.id_secado = ll.findViewById(R.id.id_actualizar_secado)
        this.actualizar_fecha_revisión_secado = ll.findViewById(R.id.actualizar_fecha_revisión_secado)
        this.actualizar_peso_secado = ll.findViewById(R.id.actualizar_peso_secado)
        this.actualizar_fecha_ordeno_secado = ll.findViewById(R.id.actualizar_fecha_ordeno_secado)
        this.actualizar_ultpartaro_secado = ll.findViewById(R.id.actualizar_ultpartaro_secado)
        this.imagen_atras_actualizar_secado = ll.findViewById(R.id.imagen_atras_actualizar_secado)

        imagen_atras_actualizar_secado.setOnClickListener {
            findNavController().navigate(R.id.action_actualizarSecadoFragment_to_categorias)
        }


        //logica de los calendarios
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

        actualizar_fecha_revisión_secado.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        actualizar_fecha_ordeno_secado.apply {
            isFocusable = false
            isClickable = true
        }

        actualizar_ultpartaro_secado.apply {
            isFocusable = false
            isClickable = true
        }

        actualizar_fecha_revisión_secado.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        actualizar_fecha_ordeno_secado.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        actualizar_ultpartaro_secado.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker3,myCalendar3.get(Calendar.YEAR),myCalendar3.get(Calendar.MONTH),myCalendar3.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        //sobrepongo los datos en los inputs
        id_secado.text = idSecado
        actualizar_fecha_revisión_secado.setText(fechaRevision)
        actualizar_peso_secado.setText(pesoSecado)
        actualizar_fecha_ordeno_secado.setText(fechaOrdeno)
        actualizar_ultpartaro_secado.setText(fechaUltiParto)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botonEnviar: Button = view.findViewById(R.id.boton_actualizar_secado)
        botonEnviar.setOnClickListener { clickUpdateSecado(view) }
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_fecha_revisión_secado.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable2(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_fecha_ordeno_secado.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable3(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_ultpartaro_secado.setText(sdf.format(myCalendar.time))
    }

    private fun clickUpdateSecado(view: View) {
        val url="http://192.168.234.187:8080/actualizarSecado/${id_secado.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.PUT, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca en secado actualizada", Toast.LENGTH_SHORT).show()

                val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                navController.navigate(R.id.action_actualizarSecadoFragment_to_categorias)

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Bovino no actualizado", Toast.LENGTH_SHORT).show()
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json" // Establece el tipo de contenido como JSON
                return headers
            }
            override fun getBodyContentType(): String {
                return "application/json"
            }
            override fun getBody(): ByteArray {
                val params = JSONObject()
                params.put("id",id_secado.text.toString())
                params.put("fecha_ultimo_parto", actualizar_ultpartaro_secado.text.toString())
                params.put("peso_kilos", actualizar_peso_secado.text.toString())
                params.put("fecha_revision", actualizar_fecha_revisión_secado.text.toString())
                params.put("fecha_ordeno", actualizar_fecha_ordeno_secado.text.toString())
                params.put("categoria",categoria)
                return  params.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }

}