package com.example.vaqui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class CambioCategoriaToroFragment : Fragment() {
    private lateinit var imagen_atras_cambio_toro : ImageView
    private lateinit var cambio_toro_peso : TextInputEditText
    private lateinit var cambio_toro_fecha_extraccion : TextInputEditText
    private lateinit var cambio_toro_vacas_montadas : TextInputEditText
    private lateinit var cambio_toro_fecha_revi : TextInputEditText
    private lateinit var id_cambio_toro : TextView
    private lateinit var funcion_eliminar_toro : TextView
    private lateinit var boton_cambio_a_toro : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val idCambioToro = arguments?.getString("id_cambio")
        val funcionEliminar = arguments?.getString("funcion_eliminar")

        val ll = inflater.inflate(R.layout.fragment_cambio_categoria_toro, container, false)
        this.imagen_atras_cambio_toro = ll.findViewById(R.id.imagen_atras_cambio_toro)
        this.cambio_toro_peso = ll.findViewById(R.id.cambio_toro_peso)
        this.cambio_toro_fecha_extraccion = ll.findViewById(R.id.cambio_toro_fecha_extraccion)
        this.cambio_toro_vacas_montadas = ll.findViewById(R.id.cambio_toro_vacas_montadas)
        this.cambio_toro_fecha_revi = ll.findViewById(R.id.cambio_toro_fecha_revi)
        this.id_cambio_toro = ll.findViewById(R.id.id_cambio_toro)
        this.funcion_eliminar_toro = ll.findViewById(R.id.funcion_eliminar_toro)
        this.boton_cambio_a_toro = ll.findViewById(R.id.boton_cambio_a_toro)

        id_cambio_toro.text= idCambioToro
        funcion_eliminar_toro.text = funcionEliminar



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



        // Deshabilito el teclado en las fechas
        cambio_toro_fecha_extraccion.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        cambio_toro_fecha_revi.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }


        cambio_toro_fecha_extraccion.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(
                Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate= Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }


        cambio_toro_fecha_revi.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(
                Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate= Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }


        boton_cambio_a_toro.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cambio de categoria")
            builder.setMessage("¿Seguro que desea realizar el cambio de categoria para el bovino ${idCambioToro}?")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                clickCambioToro()
                eliminarCategoria()


            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        imagen_atras_cambio_toro.setOnClickListener {
            findNavController().navigate(R.id.action_cambioCategoriaToroFragment_to_categorias)
        }

        return ll
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        cambio_toro_fecha_extraccion.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable2(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        cambio_toro_fecha_revi.setText(sdf.format(myCalendar.time))
    }

    private fun clickCambioToro() {
        val url="http://192.168.56.187:8080/agregarToro/${id_cambio_toro.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Toro ingresado exitosamente", Toast.LENGTH_LONG).show()

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Toro no agregado", Toast.LENGTH_LONG).show()
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

                parametros.put("id",id_cambio_toro.text.toString())
                parametros.put("peso_kilos",cambio_toro_peso?.text.toString())
                parametros.put("fecha_extraccion",cambio_toro_fecha_extraccion?.text.toString())
                parametros.put("vacas_montadas",cambio_toro_vacas_montadas?.text.toString())
                parametros.put("fecha_revision",cambio_toro_fecha_revi?.text.toString())
                parametros.put("categoria","toro")

                Log.d("error", "$parametros")
                Log.d("error", "error")
                return parametros.toString().toByteArray(Charsets.UTF_8)

            }
        }
        queue.add(resultadoPost)
    }

    private fun eliminarCategoria() {
        val url = "http://192.168.56.187:8080/${funcion_eliminar_toro.text}${id_cambio_toro.text}"
        val queue = Volley.newRequestQueue(requireContext())

        val request = StringRequest(
            Request.Method.DELETE, url,
            { response ->
                println("Eliminacion exitosa")
                findNavController().navigate(R.id.action_cambioCategoriaToroFragment_to_categorias)
            }
        ) { error ->
            println("Error en la eliminacion: " + error.message)
        }
        queue.add(request)

    }



}