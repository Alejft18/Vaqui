package com.example.vaqui

import android.annotation.SuppressLint
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
import java.util.HashMap

class actualizarEmpleadoFragment : Fragment(), AdapterView.OnItemSelectedListener{

    private lateinit var id_empleado: TextView
    private lateinit var actualizar_documento_empleado: TextInputEditText
    private lateinit var actualizar_nombre: TextInputEditText
    private lateinit var actualizar_apellido: TextInputEditText
    private lateinit var actualizar_numero_cel: TextInputEditText
    private lateinit var actualizar_cargo: TextInputEditText

    private lateinit var boton_actualizar_empleado: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_actualizar_empleado, container, false)

        this.id_empleado = ll.findViewById(R.id.id_empleado)
        this.actualizar_documento_empleado = ll.findViewById(R.id.actualizar_documento_empleado)
        this.actualizar_nombre = ll.findViewById(R.id.actualizar_nombre)
        this.actualizar_apellido = ll.findViewById(R.id.actualizar_apellido)
        this.actualizar_numero_cel = ll.findViewById(R.id.actualizar_numero_cel)
        this.actualizar_cargo = ll.findViewById(R.id.actualizar_cargo)

        this.boton_actualizar_empleado = ll.findViewById(R.id.boton_actualizar_empleado)

        boton_actualizar_empleado.setOnClickListener {
            val url="http://192.168.226.77/phpVaqui/actualizar_empleado.php"
            val queue = Volley.newRequestQueue(getActivity())
            val resultPost= object  : StringRequest(
                Request.Method.POST,url,
                Response.Listener { response->
                    Toast.makeText(getActivity(), "Se modificó el empleado", Toast.LENGTH_LONG).show()
                },{error->
                    Toast.makeText(getActivity(), " No Se modificó el empleado", Toast.LENGTH_LONG).show()
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val parametros  = HashMap<String,String>()
                    //id posicionar
                    parametros.put("id", id_empleado?.text.toString())
                    parametros.put("documento",actualizar_documento_empleado?.text.toString())
                    parametros.put("nombres",actualizar_nombre?.text.toString())
                    parametros.put("apellidos",actualizar_apellido?.text.toString())
                    parametros.put("numero_celular",actualizar_numero_cel?.text.toString())
                    parametros.put("cargo",actualizar_cargo?.text.toString())

                    return parametros
                }
            }
            queue.add(resultPost)
        }

        return ll
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}