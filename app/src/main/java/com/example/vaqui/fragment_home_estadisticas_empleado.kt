package com.example.vaqui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel

class fragment_home_estadisticas_empleado : Fragment() {
    private lateinit var card_gestion_empleados : CardView
    val imageList = ArrayList<SlideModel>()
    private lateinit var promedio_leche : TextView
    private lateinit var cantidad_bovinos : TextView
    private lateinit var cantidad_terneros : TextView
    private lateinit var btn_perfil : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_home_estadisticas_empleado, container, false)
        this.card_gestion_empleados = ll.findViewById(R.id.card_gestion_empleados)
        this.promedio_leche = ll.findViewById(R.id.promedio_leche)
        this.cantidad_bovinos = ll.findViewById(R.id.cantidad_bovinos)
        this.cantidad_terneros = ll.findViewById(R.id.cantidad_terneros)
        this.btn_perfil = ll.findViewById(R.id.btn_perfil)


        ll.isFocusableInTouchMode = true
        ll.requestFocus()
        ll.setOnKeyListener { v, keyCode, event ->
            keyCode == KeyEvent.KEYCODE_BACK
        }


        val imageSlider = ll.findViewById<ImageSlider>(R.id.image_slider)


        imageList.add(SlideModel(R.drawable.imagen_vacas_1))
        imageList.add(SlideModel(R.drawable.imagen_vacas_2))
        imageList.add(SlideModel(R.drawable.imagen_vacas_3))
        imageList.add(SlideModel(R.drawable.imagen_vacas_4))
        imageList.add(SlideModel(R.drawable.imagen_vacas_5))

        imageSlider.setImageList(imageList)

        //Boton del perfil
        btn_perfil.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_container)
            navController.navigate(R.id.perfilFragment)
        }

        promedioLeche()
        cantidadBovinos()
        cantidadTerneros()



        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_gestion_empleados.setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Administrador")
            builder.setMessage("¿Eres administrador?")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                findNavController().navigate(R.id.action_inicio_to_gestionEmpleadosFragment)
            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }
    }

    private fun cantidadTerneros(){
        val url = "https://vaquijpa2-production.up.railway.app/cantidadTerneros"
        val queue = Volley.newRequestQueue(requireContext())

        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                val cantidadTerneros = response.toIntOrNull()
                if (cantidadTerneros != null){
                    cantidad_terneros.text = cantidadTerneros.toString()
                }else{
                    Toast.makeText(requireContext(), "Respuesta inválida del servidor", Toast.LENGTH_LONG).show()
                }
            }
        ) { error ->
            Toast.makeText(requireContext(), "Error al obtener cantidad terneros", Toast.LENGTH_LONG).show()
            println("Error en la solicitud: " + error.message)
        }
        queue.add(request)
    }


    private fun cantidadBovinos(){
        val url = "https://vaquijpa2-production.up.railway.app/cantidadBovinos"
        val queue = Volley.newRequestQueue(requireContext())

        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val cantidadBovinos = response.toIntOrNull()
                if (cantidadBovinos != null){
                    cantidad_bovinos.text = cantidadBovinos.toString()
                }else{
                    Toast.makeText(requireContext(), "Respuesta inválida del servidor", Toast.LENGTH_LONG).show()
                }
            }
        ) { error ->
            Toast.makeText(requireContext(), "Error al obtener cantidad terneros", Toast.LENGTH_LONG).show()
            println("Error en la solicitud: " + error.message)
        }
        queue.add(request)
    }


    private fun promedioLeche(){
        val url = "https://vaquijpa2-production.up.railway.app/promedioLeche"
        val queue = Volley.newRequestQueue(requireContext())

        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val promedioLeche = response.toString()
                promedio_leche.text = promedioLeche
            }
        ) { error ->
            Toast.makeText(requireContext(), "Error al obtener cantidad terneros", Toast.LENGTH_LONG).show()
            println("Error en la solicitud: " + error.message)
        }
        queue.add(request)
    }

}
