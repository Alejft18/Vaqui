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

class actualizarTernerosFragment : Fragment() {

    private lateinit var id_actualizar_ternero: TextView
    private lateinit var actualizar_fecha_revisión_ternero: TextInputEditText
    private lateinit var actualizar_peso_ternero: TextInputEditText
    private lateinit var imagen_atras_actualizar_Ternero : ImageView
    private lateinit var boton_actualizar_ternero: Button
    private val categoria = "ternero"

    private val idMadre = arguments?.getString("id_madre")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val revision = arguments?.getString("fecha_revision")
        val idTernero = arguments?.getString("id_ternero")
        val peso = arguments?.getString("peos_ternero")


        val ll = inflater.inflate(R.layout.fragment_actualizar_terneros, container, false)

        this.id_actualizar_ternero = ll.findViewById(R.id.id_actualizar_ternero)
        this.actualizar_fecha_revisión_ternero = ll.findViewById(R.id.actualizar_fecha_revisión_ternero)
        this.actualizar_peso_ternero = ll.findViewById(R.id.actualizar_peso_ternero)
        this.imagen_atras_actualizar_Ternero = ll.findViewById(R.id.imagen_atras_actualizar_Ternero)
        this.boton_actualizar_ternero = ll.findViewById(R.id.boton_actualizar_ternero)

        imagen_atras_actualizar_Ternero.setOnClickListener {
            findNavController().navigate(R.id.action_actualizarTernerosFragment_to_categorias)
        }

        //logica de los calendarios
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

        //sobrepongo los datos en el formularios
        id_actualizar_ternero.text = idTernero
        actualizar_peso_ternero.setText(peso)
        actualizar_fecha_revisión_ternero.setText(revision)




        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botonEnviar: Button = view.findViewById(R.id.boton_actualizar_ternero)
        botonEnviar.setOnClickListener { clickUpdateTernero(view) }
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat, Locale("es","CO"))
        actualizar_fecha_revisión_ternero.setText(sdf.format(myCalendar.time))
    }

    private fun clickUpdateTernero(view: View) {
        val url="http://192.168.208.187:8080/actualizarTernero/${id_actualizar_ternero.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.PUT, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Ternero actualizado", Toast.LENGTH_LONG).show()

                val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                navController.navigate(R.id.action_actualizarTernerosFragment_to_categorias)

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Ternero no actualizado", Toast.LENGTH_LONG).show()
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
                params.put("id", id_actualizar_ternero?.text.toString())
                params.put("fecha_revision",actualizar_fecha_revisión_ternero.text.toString())
                params.put("peso_kilos",actualizar_peso_ternero.text.toString())
                params.put("id_madre",idMadre)
                params.put("categoria",categoria)

                return  params.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }



}