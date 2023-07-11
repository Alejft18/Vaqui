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

class actualizarLecherasFragment : Fragment(){

    private lateinit var id_lechera: TextView
    private lateinit var actualizar_fecha_revisión_lechera: TextInputEditText
    private lateinit var actualizar_peso_lechera: TextInputEditText
    private lateinit var actualizar_cantidad_partos_lechera: TextInputEditText
    private lateinit var actualizar_ultpartaro_lechera: TextInputEditText
    private lateinit var actualizar_ordeno_lechera: TextInputEditText
    private lateinit var actualizar_litros_lechera: TextInputEditText
    private lateinit var imagen_atras_actualizar_lechera : ImageView
    private val categoria = "lechera"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val idGestracion = arguments?.getString("id_lechera")
        val fecha_revision = arguments?.getString("fecha_revision_lechera")
        val peso = arguments?.getString("peso_lechera")
        val partos = arguments?.getString("partos_lechera")
        val ultiParto = arguments?.getString("fecha_ultiParto_leche")
        val fechaOrdeno = arguments?.getString("fecha_ordeno_leche")
        val litros = arguments?.getString("litros_producidos")

        val ll = inflater.inflate(R.layout.fragment_actualizar_lecheras, container, false)

        this.id_lechera = ll.findViewById(R.id.id_actualizar_lecheras)
        this.actualizar_fecha_revisión_lechera = ll.findViewById(R.id.actualizar_fecha_revisión_lechera)
        this.actualizar_peso_lechera = ll.findViewById(R.id.actualizar_peso_lechera)
        this.actualizar_cantidad_partos_lechera = ll.findViewById(R.id.actualizar_cantidad_partos_lechera)
        this.actualizar_ultpartaro_lechera = ll.findViewById(R.id.actualizar_ultparto_lechera)
        this.actualizar_ordeno_lechera = ll.findViewById(R.id.actualizar_ordeño_lechera)
        this.actualizar_litros_lechera = ll.findViewById(R.id.actualizar_litros_lechera)
        this.imagen_atras_actualizar_lechera = ll.findViewById(R.id.imagen_atras_actualizar_lechera)

        imagen_atras_actualizar_lechera.setOnClickListener {
            findNavController().navigate(R.id.action_actualizarLecherasFragment_to_categorias)
        }


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

        actualizar_ordeno_lechera.apply {
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

        actualizar_ordeno_lechera.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker3,myCalendar3.get(Calendar.YEAR),myCalendar3.get(Calendar.MONTH),myCalendar3.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        //sobrepongo los datos en el formulario
        id_lechera.text = idGestracion
        actualizar_peso_lechera.setText(peso)
        actualizar_cantidad_partos_lechera.setText(partos)
        actualizar_fecha_revisión_lechera.setText(fecha_revision)
        actualizar_ultpartaro_lechera.setText(ultiParto)
        actualizar_ordeno_lechera.setText(fechaOrdeno)
        actualizar_litros_lechera.setText(litros)


        return ll

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botonEnviar: Button = view.findViewById(R.id.boton_actualizar_lechera)
        botonEnviar.setOnClickListener { clickUpdateLechera(view) }
    }

    private fun updateLable(myCalendar: Calendar){
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_fecha_revisión_lechera.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable2(myCalendar: Calendar){
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_ordeno_lechera.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable3(myCalendar: Calendar){
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_ultpartaro_lechera.setText(sdf.format(myCalendar.time))
    }

    private fun clickUpdateLechera(view: View) {
        val url="http://192.168.56.187:8080/actualizarLechera/${id_lechera.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.PUT, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca lechera actualizado", Toast.LENGTH_SHORT).show()

                val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                navController.navigate(R.id.action_actualizarLecherasFragment_to_categorias)

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
                params.put("id",id_lechera.text.toString())
                params.put("litros_producidos",actualizar_litros_lechera?.text.toString())
                params.put("fecha_ordeno",actualizar_ordeno_lechera.text.toString())
                params.put("peso_kilos",actualizar_peso_lechera?.text.toString())
                params.put("fecha_revision",actualizar_fecha_revisión_lechera?.text.toString())
                params.put("fecha_parto", actualizar_ultpartaro_lechera.text.toString())
                params.put("cant_partos", actualizar_cantidad_partos_lechera.text.toString())
                params.put("categoria",categoria)
                return  params.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }

}