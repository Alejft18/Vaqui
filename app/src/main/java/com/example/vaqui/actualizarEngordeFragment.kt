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

class actualizarEngordeFragment : Fragment(){

    private lateinit var imagen_atras_actualizar_engorde : ImageView
    private lateinit var id_actualizar_engorde : TextView
    private lateinit var actualizar_fecha_revisión_engorde : TextInputEditText
    private lateinit var actualizar_peso_engorde : TextInputEditText
    private lateinit var actualizar_alimento_engorde : TextInputEditText
    private val categoria = "engorde"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //traigo los datos de datos_engorde
        val idEngorde = arguments?.getString("id_engorde")
        val pesoEngorde = arguments?.getString("peso_engorde")
        val alimentoEngorde = arguments?.getString("alimento_engorde")
        val fechaRevisionEngorde = arguments?.getString("fecha_revision_engorde")

        val ll= inflater.inflate(R.layout.fragment_actualizar_engorde, container, false)
        this.imagen_atras_actualizar_engorde = ll.findViewById(R.id.imagen_atras_actualizar_engorde)
        this.id_actualizar_engorde = ll.findViewById(R.id.id_actualizar_engorde)
        this.actualizar_fecha_revisión_engorde = ll.findViewById(R.id.actualizar_fecha_revisión_engorde)
        this.actualizar_peso_engorde = ll.findViewById(R.id.actualizar_peso_engorde)
        this.actualizar_alimento_engorde = ll.findViewById(R.id.actualizar_alimento_engorde)

        val myCalendar= Calendar.getInstance()

        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }


        actualizar_fecha_revisión_engorde.apply {
            isFocusable = false
            isClickable = true
        }



        actualizar_fecha_revisión_engorde.setOnClickListener {
            val dialog = DatePickerDialog(
                requireContext(),
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            )

            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        imagen_atras_actualizar_engorde.setOnClickListener {
            findNavController().navigate(R.id.action_actualizarEngordeFragment_to_categorias)
        }

        //sobrepongo los datos obtenidos de datos_engorde
        id_actualizar_engorde.text = idEngorde
        actualizar_peso_engorde.setText(pesoEngorde)
        actualizar_alimento_engorde.setText(alimentoEngorde)
        actualizar_fecha_revisión_engorde.setText(fechaRevisionEngorde)


        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val botonEnviar: Button = view.findViewById(R.id.boton_actualizar_engorde)
        botonEnviar.setOnClickListener { clickUpdateEngorde(view) }
    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_fecha_revisión_engorde.setText(sdf.format(myCalendar.time))

    }

    private fun clickUpdateEngorde(view: View) {
        val url="http://192.168.208.187:8080/actualizarEngorde/${id_actualizar_engorde.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.PUT, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Bovino actualizado en engorde exitosamente", Toast.LENGTH_LONG).show()

                val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                navController.navigate(R.id.action_actualizarEngordeFragment_to_categorias)

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Bovino no actualizado", Toast.LENGTH_LONG).show()
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
                params.put("id",id_actualizar_engorde.text.toString())
                params.put("peso_kilos",actualizar_peso_engorde?.text.toString())
                params.put("fecha_revision",actualizar_fecha_revisión_engorde?.text.toString())
                params.put("alimento",actualizar_alimento_engorde?.text.toString())
                params.put("categoria",categoria)
                return  params.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }


}