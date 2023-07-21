package com.example.vaqui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
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


class actualizarGeneralFragment : Fragment(), AdapterView.OnItemSelectedListener {
            private lateinit var id_actualizar_general : TextView
    private lateinit var actualizar_raza : TextInputEditText
    private lateinit var actualizar_spinner_genero : TextView
    private lateinit var actualizar_fecha_general : EditText
    private lateinit var actualizar_spinner_procedencia : Spinner

    private lateinit var imagen_atras_actualizar_general : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //traigo los valores de datos_general
        val idGeneral = arguments?.getString("id_general")
        val razaGeneral = arguments?.getString("raza_general")
        val generoGeneral = arguments?.getString("genero_general")
        val fechaGeneral = arguments?.getString("fecha_general")
        val procedenciaGeneral = arguments?.getString("procedencia_general")


        val ll =  inflater.inflate(R.layout.fragment_actualizar_general, container, false)
        this.imagen_atras_actualizar_general = ll.findViewById(R.id.imagen_atras_actualizar_general)
        this.id_actualizar_general = ll.findViewById(R.id.id_actualizar_general)
        this.actualizar_raza = ll.findViewById(R.id.actualizar_raza)
        this.actualizar_spinner_genero = ll.findViewById(R.id.actualizar_spinner_genero)
        this.actualizar_fecha_general = ll.findViewById(R.id.actualizar_fecha_general)
        this.actualizar_spinner_procedencia = ll.findViewById(R.id.actualizar_spinner_procedencia)



        //logica para la fecha de nacimiento
        val myCalendar= Calendar.getInstance()

        val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar)
        }

        // Deshabilito el teclado para la fecha de nacimiento
        actualizar_fecha_general.apply {
            isFocusable = false
            isClickable = true   // Hacer el EditText clickeable
        }

        actualizar_fecha_general.setOnClickListener {
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

        //Logica del spinner
        val spinnerData2= arrayOf("Seleccione la procedencia","de la finca","de otra finca")


        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData2)
        val positionDisable2=0
        adapter2.getView(positionDisable2,null,actualizar_spinner_procedencia)?.isEnabled=true
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        actualizar_spinner_procedencia.adapter = adapter2

        //pongo los datos obtenidos en los inputs
        id_actualizar_general.text = idGeneral
        actualizar_raza.setText(razaGeneral)
        actualizar_spinner_genero.setText(generoGeneral)
        actualizar_fecha_general.setText(fechaGeneral)
        val procedenciaPosition = spinnerData2.indexOf(procedenciaGeneral)
        if (procedenciaPosition >= 0) {
            actualizar_spinner_procedencia.setSelection(procedenciaPosition)
        }

        imagen_atras_actualizar_general.setOnClickListener{
            findNavController().navigate(R.id.action_actualizarGeneralFragment_to_categorias)
        }

        return ll
    }

    //formato de la fecha
    private fun updateLable(myCalendar: Calendar){
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_fecha_general.setText(sdf.format(myCalendar.time))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actualizar_spinner_procedencia.onItemSelectedListener = this

        val botonActualizar: Button = view.findViewById(R.id.boton_actualizar_general)
        botonActualizar.setOnClickListener { clickUpdateGeneral(view) }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = parent?.getItemAtPosition(position).toString()
        if (selectedItem == "Seleccione el genero"){
            Toast.makeText(requireContext(),"Por favor, selecciona una opción válida",Toast.LENGTH_SHORT).show()

        }
        if (selectedItem == "Seleccione la procedencia"){
            Toast.makeText(requireContext(),"Por favor, selecciona una opción válida",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun clickUpdateGeneral(view: View) {
        val url="https://192.168.74.187:8080/actualizarBovinoGeneral"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.PUT, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Bovino actualizado exitosamente", Toast.LENGTH_LONG).show()

                val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
                navController.navigate(R.id.action_actualizarGeneralFragment_to_categorias)

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
                params.put("id",id_actualizar_general.text.toString())
                params.put("raza",actualizar_raza?.text.toString())
                params.put("genero",actualizar_spinner_genero?.text.toString())
                params.put("fecha_nacimiento",actualizar_fecha_general?.text.toString())
                params.put("procedencia",actualizar_spinner_procedencia?.selectedItem.toString())
                return  params.toString().toByteArray(Charsets.UTF_8)
            }
        }
        queue.add(resultadoPost)
    }
}