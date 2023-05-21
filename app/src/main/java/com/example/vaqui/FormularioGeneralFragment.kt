package com.example.vaqui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText


class FormularioGeneralFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var txtRaza: TextInputEditText
    private lateinit var txtFechaNacimiento: TextInputEditText

    private lateinit var genero: Spinner
    private lateinit var procedencia: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_formulario_general, container, false)
        txtRaza=view.findViewById(R.id.ingreso_raza)
        genero=view.findViewById(R.id.spinner_genero)
        txtFechaNacimiento=view.findViewById(R.id.ingreso_fecha_general)
        procedencia=view.findViewById(R.id.spinner_procedencia)

        val spinnerData1= arrayOf("Seleccione el genero","masculino","femenino")
        val spinnerData2= arrayOf("Seleccione la procedencia","de la finca","de otra finca")

        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData1)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val positionDisable1=0
        adapter1.getView(positionDisable1,null,genero)?.isEnabled=true
        genero.adapter = adapter1


        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData2)
        val positionDisable2=0
        adapter1.getView(positionDisable2,null,procedencia)?.isEnabled=true
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        procedencia.adapter = adapter2

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genero.onItemSelectedListener = this
        procedencia.onItemSelectedListener = this

        val botonEnviar: Button = view.findViewById(R.id.boton_enviar_general)
        botonEnviar.setOnClickListener { clickAddGeneral(view) }
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


    private fun clickAddGeneral(view: View) {
        val url="http://192.168.226.187/phpVaqui/agregar_bovino_general.php"
        val queue = Volley.newRequestQueue(requireContext())
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(requireContext(), "Bovino ingresado exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener{
                Toast.makeText(requireContext(), "Bovino no agregado", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()

                parametros.put("raza",txtRaza?.text.toString())
                parametros.put("genero",genero?.selectedItem.toString())
                parametros.put("fecha_nacimiento",txtFechaNacimiento?.text.toString())
                parametros.put("procedencia",procedencia?.selectedItem.toString())
                Log.d("error", "$parametros")
                Log.d("error", "error")
                return parametros

            }
        }
        queue.add(resultadoPost)
    }
}
