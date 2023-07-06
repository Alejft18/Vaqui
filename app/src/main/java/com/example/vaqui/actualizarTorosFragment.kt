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

class actualizarTorosFragment : Fragment() {

    private lateinit var id_actualizar_toro: TextView
    private lateinit var actualizar_peso_toro: TextInputEditText
    private lateinit var actualizar_fecha_revisión_toro: TextInputEditText
    private lateinit var actualizar_vacas_montadas_toro: TextInputEditText
    private lateinit var actualizar_extraccion_toro: TextInputEditText
    private lateinit var imagen_atras_actualizar_toro : ImageView
    private val categoria = "toro"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //traigo los datos de datos_sementales
        val idToro = arguments?.getString("id_toro")
        val peso = arguments?.getString("peso_toro")
        val extraccion = arguments?.getString("fecha_extraccion")
        val vacasMontadas = arguments?.getString("vacas_montadas")
        val revision = arguments?.getString("fecha_revision")

        val ll = inflater.inflate(R.layout.fragment_actualizar_toros, container, false)

        this.id_actualizar_toro = ll.findViewById(R.id.id_actualizar_toro)
        this.actualizar_fecha_revisión_toro = ll.findViewById(R.id.actualizar_fecha_revisión_toro)
        this.actualizar_peso_toro = ll.findViewById(R.id.actualizar_peso_toro)
        this.actualizar_vacas_montadas_toro = ll.findViewById(R.id.actualizar_vacas_montadas_toro)
        this.actualizar_extraccion_toro = ll.findViewById(R.id.actualizar_extraccion_toro)
        this.imagen_atras_actualizar_toro = ll.findViewById(R.id.imagen_atras_actualizar_toro)

        imagen_atras_actualizar_toro.setOnClickListener {
            findNavController().navigate(R.id.action_actualizarTorosFragment_to_categorias)
        }


        //Logica de los calendarios
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


        actualizar_extraccion_toro.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }


        actualizar_fecha_revisión_toro.setOnClickListener {
            val dialog = DatePickerDialog(requireContext(),datePicker2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH))
            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate=Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        //sobrepongo los datos en el formulario
        id_actualizar_toro.text = idToro
        actualizar_peso_toro.setText(peso)
        actualizar_extraccion_toro.setText(extraccion)
        actualizar_vacas_montadas_toro.setText(vacasMontadas)
        actualizar_fecha_revisión_toro.setText(revision)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botonEnviar: Button = view.findViewById(R.id.boton_actualizar_toro)
        botonEnviar.setOnClickListener { clickUpdateToro(view) }
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_extraccion_toro.setText(sdf.format(myCalendar.time))
    }

    private fun updateLable2(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_fecha_revisión_toro.setText(sdf.format(myCalendar.time))
    }


    private fun clickUpdateToro(view: View) {
        val url="http://192.168.208.187:8080/actualizarToro/${id_actualizar_toro.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.PUT, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Toro actualizado", Toast.LENGTH_LONG).show()

                val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                navController.navigate(R.id.action_actualizarTorosFragment_to_categorias)

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Toro no actualizado", Toast.LENGTH_LONG).show()
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
                params.put("id", id_actualizar_toro?.text.toString())
                params.put("fecha_revision",actualizar_fecha_revisión_toro?.text.toString())
                params.put("peso_kilos",actualizar_peso_toro?.text.toString())
                params.put("vacas_montadas",actualizar_vacas_montadas_toro?.text.toString())
                params.put("fecha_extraccion",actualizar_extraccion_toro?.text.toString())

                return  params.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }

}