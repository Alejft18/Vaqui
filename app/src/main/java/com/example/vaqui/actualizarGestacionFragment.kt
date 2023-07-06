package com.example.vaqui

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

class actualizarGestacionFragment : Fragment(){
    private lateinit var id_gestacion: TextView
    private lateinit var actualizar_fecha_revisión_gestacion : TextInputEditText
    private lateinit var actualizar_peso_gestacion : TextInputEditText
    private lateinit var actualizar_aproxparto_gestacion : TextInputEditText
    private lateinit var actualizar_ultpartaro_gestacion : TextInputEditText
    private lateinit var actualizar_inseminacion_gestacion : TextInputEditText
    private lateinit var imagen_atras_actualizar_gestacion : ImageView
    private val categoria = "gestacion"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_actualizar_gestacion, container, false)

        //traigo los datos de datos_gestacion
        val idGestacion = arguments?.getString("id_gestacion")
        val pesoGestacion = arguments?.getString("peso_gestacion")
        val fechaInsemi = arguments?.getString("fecha_insemi_gestacion")
        val fechaAprox = arguments?.getString("fecha_aprox_parto_gestacion")
        val fechaUltiParto = arguments?.getString("fecha_ulti_parto_gestacion")
        val fechaRevision = arguments?.getString("fecha_revision_gestacion")

        this.id_gestacion = ll.findViewById(R.id.id_actualizar_gestacion)
        this.actualizar_fecha_revisión_gestacion = ll.findViewById(R.id.actualizar_fecha_revisión_gestacion)
        this.actualizar_peso_gestacion = ll.findViewById(R.id.actualizar_peso_gestacion)
        this.actualizar_aproxparto_gestacion = ll.findViewById(R.id.actualizar_aproxparto_gestacion)
        this.actualizar_ultpartaro_gestacion = ll.findViewById(R.id.actualizar_ultparto_gestacion)
        this.actualizar_inseminacion_gestacion = ll.findViewById(R.id.actualizar_inseminacion_gestacion)
        this.imagen_atras_actualizar_gestacion = ll.findViewById(R.id.imagen_atras_actualizar_gestacion)


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

        val myCalendar4= Calendar.getInstance()
        val datePicker4= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar4.set(Calendar.YEAR,year)
            myCalendar4.set(Calendar.MONTH,month)
            myCalendar4.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable4(myCalendar4)
        }

        // Deshabilito el teclado en las fechas
        actualizar_inseminacion_gestacion.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        actualizar_aproxparto_gestacion.apply {
            isFocusable = false
            isClickable = true
        }

        actualizar_ultpartaro_gestacion.apply {
            isFocusable = false
            isClickable = true
        }

        actualizar_fecha_revisión_gestacion.apply {
            isFocusable = false
            isClickable = true
        }

        actualizar_inseminacion_gestacion.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        actualizar_aproxparto_gestacion.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH))

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        actualizar_ultpartaro_gestacion.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker3,myCalendar3.get(Calendar.YEAR),myCalendar3.get(Calendar.MONTH),myCalendar3.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        actualizar_fecha_revisión_gestacion.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker4,myCalendar4.get(Calendar.YEAR),myCalendar4.get(Calendar.MONTH),myCalendar4.get(Calendar.DAY_OF_MONTH))

            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        //sobrepongo los datos en el formulario
        id_gestacion.text = idGestacion
        actualizar_peso_gestacion.setText(pesoGestacion)
        actualizar_inseminacion_gestacion.setText(fechaInsemi)
        actualizar_aproxparto_gestacion.setText(fechaAprox)
        actualizar_ultpartaro_gestacion.setText(fechaUltiParto)
        actualizar_fecha_revisión_gestacion.setText(fechaRevision)

        imagen_atras_actualizar_gestacion.setOnClickListener {
            findNavController().navigate(R.id.action_actualizarGestacionFragment_to_categorias)
        }



        return ll

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botonEnviar: Button = view.findViewById(R.id.boton_actualizar_gestacion)
        botonEnviar.setOnClickListener { clickUpdateGestacion(view) }
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_inseminacion_gestacion.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable2(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_aproxparto_gestacion.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable3(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es", "CO"))
        actualizar_ultpartaro_gestacion.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable4(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_fecha_revisión_gestacion.setText(sdf.format(myCalendar.time))

    }
    private fun clickUpdateGestacion(view: View) {
        val url="http://192.168.208.187:8080/actualizarGestacion/${id_gestacion.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.PUT, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Bovino actualizado en gestacion exitosamente", Toast.LENGTH_SHORT).show()

                val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                navController.navigate(R.id.action_actualizarGestacionFragment_to_categorias)

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
                params.put("id",id_gestacion.text.toString())
                params.put("peso_kilos",actualizar_peso_gestacion?.text.toString())
                params.put("fecha_inseminacion",actualizar_inseminacion_gestacion.text.toString())
                params.put("fecha_aproxParto",actualizar_aproxparto_gestacion.text.toString())
                params.put("fecha_ultimoParto",actualizar_ultpartaro_gestacion.text.toString())
                params.put("fecha_revision",actualizar_fecha_revisión_gestacion?.text.toString())
                params.put("categoria",categoria)
                return  params.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }


}