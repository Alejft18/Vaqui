package com.example.vaqui

import android.annotation.SuppressLint
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
import java.util.Calendar
import java.util.HashMap
import java.util.Locale

class CambioCategoriaLecheraFragment : Fragment() {
    private lateinit var imagen_cambio_categoria_lechera : ImageView
    private lateinit var cambio_lechera_litros : TextInputEditText
    private lateinit var cambio_lechera_fecha_ordeno : TextInputEditText
    private lateinit var cambio_lechera_peso : TextInputEditText
    private lateinit var cambio_lechera_fecha_revi : TextInputEditText
    private lateinit var cambio_lechera_fecha_parto : TextInputEditText
    private lateinit var cambio_lechera_cantidad_partos : TextInputEditText
    private lateinit var id_cambio_lechera : TextView
    private lateinit var funcion_eliminar_lechera : TextView
    private lateinit var boton_cambio_a_lechera : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val idCambioLechera = arguments?.getString("id_cambio")
        val funcionEliminar = arguments?.getString("funcion_eliminar")

        val ll = inflater.inflate(R.layout.fragment_cambio_categoria_lechera, container, false)
        this.imagen_cambio_categoria_lechera = ll.findViewById(R.id.imagen_cambio_categoria_lechera)
        this.cambio_lechera_litros = ll.findViewById(R.id.cambio_lechera_litros)
        this.cambio_lechera_fecha_ordeno = ll.findViewById(R.id.cambio_lechera_fecha_ordeno)
        this.cambio_lechera_peso = ll.findViewById(R.id.cambio_lechera_peso)
        this.cambio_lechera_fecha_revi = ll.findViewById(R.id.cambio_lechera_fecha_revi)
        this.cambio_lechera_fecha_parto = ll.findViewById(R.id.cambio_lechera_fecha_parto)
        this.cambio_lechera_cantidad_partos = ll.findViewById(R.id.cambio_lechera_cantidad_partos)
        this.id_cambio_lechera = ll.findViewById(R.id.id_cambio_lechera)
        this.funcion_eliminar_lechera = ll.findViewById(R.id.funcion_eliminar_lechera)
        this.boton_cambio_a_lechera = ll.findViewById(R.id.boton_cambio_a_lechera)

        id_cambio_lechera.text = idCambioLechera
        funcion_eliminar_lechera.text = funcionEliminar

        imagen_cambio_categoria_lechera.setOnClickListener {
            findNavController().navigate(R.id.action_cambioCategoriaLecheraFragment_to_categorias)
        }


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

        // Deshabilito el teclado en las fechas
        cambio_lechera_fecha_ordeno.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        cambio_lechera_fecha_revi.apply {
            isFocusable = false
            isClickable = true
        }

        cambio_lechera_fecha_parto.apply {
            isFocusable = false
            isClickable = true
        }


        cambio_lechera_fecha_ordeno.setOnClickListener {
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

        cambio_lechera_fecha_revi.setOnClickListener {
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

        cambio_lechera_fecha_parto.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker3,myCalendar3.get(Calendar.YEAR),myCalendar3.get(
                Calendar.MONTH),myCalendar3.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate= Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        boton_cambio_a_lechera.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cambio de categoria")
            builder.setMessage("¿Seguro que desea realizar el cambio de categoria para el bovino ${idCambioLechera}?")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                clickCambioLechera()



            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }


        return ll
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        cambio_lechera_fecha_ordeno.setText(sdf.format(myCalendar.time))

    }
    private fun updateLable2(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        cambio_lechera_fecha_revi.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable3(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        cambio_lechera_fecha_parto.setText(sdf.format(myCalendar.time))
    }


    private fun clickCambioLechera() {
        val url= "http://192.168.180.187:8080/agregarLecheras/${id_cambio_lechera.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca Lechera agregada con exito", Toast.LENGTH_LONG).show()
                eliminarCategoria()

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Vaca Lechera no agregada", Toast.LENGTH_LONG).show()
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
                parametros.put("id",id_cambio_lechera.text.toString())
                parametros.put("litros_producidos",cambio_lechera_litros?.text.toString())
                parametros.put("fecha_ordeno",cambio_lechera_fecha_ordeno?.text.toString())
                parametros.put("peso_kilos",cambio_lechera_peso?.text.toString())
                parametros.put("fecha_revision",cambio_lechera_fecha_revi?.text.toString())
                parametros.put("fecha_parto",cambio_lechera_fecha_parto?.text.toString())
                parametros.put("cant_partos",cambio_lechera_cantidad_partos?.text.toString())
                parametros.put("categoria","lechera")

                Log.d("error", "$parametros")
                Log.d("error", "error")
                return parametros.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }

    private fun eliminarCategoria() {
        val url = "http://192.168.180.187:8080/${funcion_eliminar_lechera.text}${id_cambio_lechera.text}"
        val queue = Volley.newRequestQueue(requireContext())

        val request = StringRequest(
            Request.Method.DELETE, url,
            { response ->
                println("Eliminacion exitosa")
                findNavController().navigate(R.id.action_cambioCategoriaLecheraFragment_to_categorias)
            }
        ) { error ->
            println("Error en la eliminacion: " + error.message)
        }
        queue.add(request)

    }


}