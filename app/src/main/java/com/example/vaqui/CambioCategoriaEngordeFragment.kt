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

class CambioCategoriaEngordeFragment : Fragment() {
    private lateinit var imagen_atras_ingreso_cambiar_engorde : ImageView
    private lateinit var cambio_engorde_peso : TextInputEditText
    private lateinit var cambio_engorde_fecha_revi : TextInputEditText
    private lateinit var cambio_engorde_alimento : TextInputEditText
    private lateinit var boton_cambio_a_engorde : Button
    private lateinit var id_cambio_engorde : TextView
    private lateinit var funcion_eliminar_engorde : TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val idCambioEngorde = arguments?.getString("id_cambio")
        val funcionEliminar = arguments?.getString("funcion_eliminar")

        val ll = inflater.inflate(R.layout.fragment_cambio_categoria_engorde, container, false)
        this.imagen_atras_ingreso_cambiar_engorde = ll.findViewById(R.id.imagen_atras_ingreso_cambiar_engorde)
        this.cambio_engorde_peso = ll.findViewById(R.id.cambio_engorde_peso)
        this.cambio_engorde_fecha_revi = ll.findViewById(R.id.cambio_engorde_fecha_revi)
        this.cambio_engorde_alimento = ll.findViewById(R.id.cambio_engorde_alimento)
        this.boton_cambio_a_engorde = ll.findViewById(R.id.boton_cambio_a_engorde)
        this.id_cambio_engorde = ll.findViewById(R.id.id_cambio_engorde)
        this.funcion_eliminar_engorde = ll.findViewById(R.id.funcion_eliminar_engorde)

        id_cambio_engorde.text = idCambioEngorde
        funcion_eliminar_engorde.text = funcionEliminar

        //logica de los calendar
        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }


        cambio_engorde_fecha_revi.apply {
            isFocusable = false
            isClickable = true
        }



        cambio_engorde_fecha_revi.setOnClickListener {
            val dialog = DatePickerDialog(
                requireContext(), datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(
                    Calendar.MONTH
                ), myCalendar.get(Calendar.DAY_OF_MONTH)
            )

            //pongo la fecha maxima como dia actual
            dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis

            //pongo la fecha minima hasta el año 2000
            val minDate = Calendar.getInstance()
            minDate.set(2000, Calendar.JANUARY, 1)
            dialog.datePicker.minDate = minDate.timeInMillis

            dialog.show()
        }

        boton_cambio_a_engorde.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Cambio de categoria")
            builder.setMessage("¿Seguro que desea realizar el cambio de categoria para el bovino ${idCambioEngorde}?")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                clickCambioEngorde()
                eliminarCategoria()


            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        imagen_atras_ingreso_cambiar_engorde.setOnClickListener {
            findNavController().navigate(R.id.action_cambioCategoriaEngordeFragment_to_categorias)
        }

        return ll

    }

    private fun updateLable(myCalendar: Calendar) {
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        cambio_engorde_fecha_revi.setText(sdf.format(myCalendar.time))

    }

    private fun clickCambioEngorde() {
        val url= "http://192.168.180.187:8080/ingresarEngorde/${id_cambio_engorde.text}"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Vaca engorde ingresada exitosamente", Toast.LENGTH_LONG).show()

            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Vaca engorde no agregada", Toast.LENGTH_LONG).show()
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
                parametros.put("id",id_cambio_engorde.toString())
                parametros.put("peso_kilos",cambio_engorde_peso?.text.toString())
                parametros.put("fecha_revision",cambio_engorde_fecha_revi?.text.toString())
                parametros.put("alimento",cambio_engorde_alimento?.text.toString())
                parametros.put("categoria","engorde")

                Log.d("error", "$parametros")
                Log.d("error", "error")

                return parametros.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }

    private fun eliminarCategoria() {
        val url = "http://192.168.180.187:8080/${funcion_eliminar_engorde.text}${id_cambio_engorde.text}"
        val queue = Volley.newRequestQueue(requireContext())

        val request = StringRequest(
            Request.Method.DELETE, url,
            { response ->
                println("Eliminacion exitosa")
                findNavController().navigate(R.id.action_cambioCategoriaEngordeFragment_to_categorias)
            }
        ) { error ->
            println("Error en la eliminacion: " + error.message)
        }
        queue.add(request)

    }

}