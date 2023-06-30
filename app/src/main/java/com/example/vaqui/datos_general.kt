package com.example.vaqui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject


class datos_general : DialogFragment() {
    private lateinit var tvBarra_general : Toolbar
    private lateinit var id_general : TextView
    private lateinit var fecha_general : TextView
    private lateinit var raza_general : TextView
    private lateinit var genero_general : TextView
    private lateinit var procedencia : TextView
    private lateinit var img_general : ImageView

    private lateinit var btn_detalle_actualizar_general: Button
    private lateinit var btn_eliminar_general : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll=inflater.inflate(R.layout.fragment_datos_general,container,false)
        this.tvBarra_general = ll.findViewById(R.id.tvBarra_general)

        this.id_general = ll.findViewById(R.id.id_general)
        this.raza_general = ll.findViewById(R.id.raza_general)
        this.genero_general = ll.findViewById(R.id.genero_general)
        this.fecha_general = ll.findViewById(R.id.fecha_general)
        this.procedencia = ll.findViewById(R.id.procedencia)
        this.img_general = ll.findViewById(R.id.img_general)
        this.btn_detalle_actualizar_general = ll.findViewById(R.id.btn_detalle_actualizar_general)
        this.btn_eliminar_general = ll.findViewById(R.id.btn_eliminar_general)

        btn_detalle_actualizar_general.setOnClickListener {
            //Envio los datos al formulario de actualizar
            val bundle = Bundle()
            bundle.putString("id_general", id_general.text.toString())
            bundle.putString("raza_general", raza_general.text.toString())
            bundle.putString("genero_general", genero_general.text.toString())
            bundle.putString("fecha_general", fecha_general.text.toString())
            bundle.putString("procedencia_general", procedencia.text.toString())

            val actualizarGeneralFragment = actualizarGeneralFragment()
            actualizarGeneralFragment.arguments = bundle
            findNavController().navigate(R.id.action_datos_general_to_actualizarGeneralFragment, bundle)
        }

        btn_eliminar_general.setOnClickListener {

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("¿Deseas eliminar al bovino ${id_general.text}?")
            builder.setMessage("Si eliminas este bovino en este apartado tambien lo hara en la categoria en la que se encuentra")
            builder.setPositiveButton("Aceptar") { dialog, which ->

                val url = "http://192.168.234.187:8080/eliminarBovinoGeneral/${id_general.text}"
                val queue = Volley.newRequestQueue(requireContext())

                val request = StringRequest(
                    Request.Method.DELETE, url,
                    { response ->
                        Toast.makeText(requireContext(),"eliminacion exitosa", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                ) { error ->
                    Toast.makeText(requireContext(), "Error al eliminar el bovino", Toast.LENGTH_SHORT).show()
                    println("Error en la solicitud: " + error.message)
                }
                queue.add(request)

            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }


        return ll
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tvBarra_general.navigationIcon=ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_close_24)
        this.tvBarra_general.setNavigationOnClickListener {
            dismiss()
        }

    val general = JSONObject(arguments?.getString("bovinos"))
        this.id_general.text=general.getString("id")
        this.raza_general.text=general.getString("raza")
        this.genero_general.text=general.getString("genero")
        this.fecha_general.text=general.getString("fecha_nacimiento")
        this.procedencia.text=general.getString("procedencia")

        Glide.with(this)
            .load(general.getString("imagen"))
            .into(this.img_general)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}