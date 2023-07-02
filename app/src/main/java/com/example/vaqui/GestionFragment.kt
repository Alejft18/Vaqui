package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation


class GestionFragment : Fragment() {
    private lateinit var card_buscar_bovi : CardView
    private lateinit var card_ingresar_bovino : CardView
    private lateinit var btn_perfil_gestion : ImageView
    private lateinit var card_actualizar_bovino : CardView
    private lateinit var card_eliminar_bovino : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_gestion, container, false)
        this.card_buscar_bovi = ll.findViewById(R.id.card_buscar_bovi)
        this.card_ingresar_bovino = ll.findViewById(R.id.card_ingresar_bovino)
        this.card_actualizar_bovino = ll.findViewById(R.id.card_actualizar_bovino)
        this.card_eliminar_bovino = ll.findViewById(R.id.card_eliminar_bovino)
        this.btn_perfil_gestion = ll.findViewById(R.id.btn_perfil_gestion)

        ll.isFocusableInTouchMode = true
        ll.requestFocus()
        ll.setOnKeyListener { v, keyCode, event ->
            keyCode == KeyEvent.KEYCODE_BACK
        }


        btn_perfil_gestion.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_container)
            navController.navigate(R.id.perfilFragment)
        }
        card_buscar_bovi.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.generalfragment2)
        }

        card_ingresar_bovino.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.formularioGeneralFragment)
        }

        card_actualizar_bovino.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("¿Quieres actualizar datos de un bovino?")
            builder.setMessage("Si deseas actualizar datos de un bovino sigue los siguientes pasos: \n1. Dirijete a la lista correspondiente del bovino al que quieres hacerle cambios. \n2. Busca al bovino. \n3. Dale al boton boton de actualizar. \n4. ingresa los nuevos datos y guarda")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        card_eliminar_bovino.setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("¿Quieres eliminar a un bovino?")
            builder.setMessage("Si deseas eliminar a un bovino sigue los siguientes pasos: \n1. Dirijete a la lista de general. \n2. Busca al bovino. \n3. Dale al boton de eliminar y acepta la comprobación. \nRecuerda que solo podras eliminar bovinos desde la categoria de general")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return ll
    }

}