package com.example.vaqui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*


class ActualizarGeneralFragment : Fragment(),AdapterView.OnItemSelectedListener {
    private lateinit var id_general: TextView
    private lateinit var actualizar_raza: TextInputEditText
    private lateinit var actualizar_spinner_genero: Spinner
    private lateinit var actualizar_fecha_general: EditText
    private lateinit var actualizar_spinner_procedencia: Spinner

    private lateinit var boton_actualizar_general: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll= inflater.inflate(R.layout.fragment_actualizar_general, container, false)

        this.id_general=ll.findViewById(R.id.id_general)
        this.actualizar_raza=ll.findViewById(R.id.actualizar_raza)
        this.actualizar_spinner_genero=ll.findViewById(R.id.actualizar_spinner_genero)
        this.actualizar_fecha_general=ll.findViewById(R.id.actualizar_fecha_general)
        this.actualizar_spinner_procedencia=ll.findViewById(R.id.actualizar_spinner_procedencia)

        this.boton_actualizar_general=ll.findViewById(R.id.boton_actualizar_general)




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

        //Logica de los spinners
        val spinnerData1= arrayOf("Seleccione el genero","masculino","femenino")
        val spinnerData2= arrayOf("Seleccione la procedencia","de la finca","de otra finca")

        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData1)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val positionDisable1=0
        adapter1.getView(positionDisable1,null,actualizar_spinner_genero)?.isEnabled=true
        actualizar_spinner_genero.adapter = adapter1


        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData2)
        val positionDisable2=0
        adapter1.getView(positionDisable2,null,actualizar_spinner_procedencia)?.isEnabled=true
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        actualizar_spinner_procedencia.adapter = adapter2




        boton_actualizar_general.setOnClickListener {
            val url="http://192.168.226.187/phpVaqui/actualizar_bovino_general.php"
            val queue = Volley.newRequestQueue(getActivity())
            val resultPost= object  : StringRequest(
                Request.Method.POST,url,
                Response.Listener { response->
                    Toast.makeText(getActivity(), "Se modificó la vaca general",Toast.LENGTH_LONG).show()
                },{error->
                    Toast.makeText(getActivity(), " No Se modificó la vaca general",Toast.LENGTH_LONG).show()
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val parametros  = HashMap<String,String>()
                    //id posicionar
                    parametros.put("id", id_general?.text.toString())
                    parametros.put("raza",actualizar_raza?.text.toString())
                    parametros.put("genero",actualizar_spinner_genero?.selectedItem.toString())
                    parametros.put("fecha_nacimiento",actualizar_fecha_general?.text.toString())
                    parametros.put("procedencia",actualizar_spinner_procedencia?.selectedItem.toString())

                    return parametros
                }
            }
            queue.add(resultPost)
        }

        return ll
    }

    //formato de la fecha
    private fun updateLable(myCalendar: Calendar){
        val myformat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myformat,Locale("es","CO"))
        actualizar_fecha_general.setText(sdf.format(myCalendar.time))
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actualizar_spinner_genero.onItemSelectedListener = this
        actualizar_spinner_procedencia.onItemSelectedListener = this
    }





}