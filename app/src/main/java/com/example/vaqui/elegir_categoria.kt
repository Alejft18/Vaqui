package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation


class elegir_categoria : Fragment() {

    private lateinit var card_elegir_lechera : CardView
    private lateinit var card_elegir_gestacion : CardView
    private lateinit var card_elegir_secado : CardView
    private lateinit var card_elegir_engorde : CardView
    private lateinit var card_elegir_sementales : CardView
    private lateinit var card_elegir_terneros : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val ll = inflater.inflate(R.layout.fragment_elegir_categoria, container, false)

        this.card_elegir_lechera = ll.findViewById(R.id.card_elegir_lechera)
        this.card_elegir_gestacion = ll.findViewById(R.id.card_elegir_gestacion)
        this.card_elegir_secado = ll.findViewById(R.id.card_elegir_secado)
        this.card_elegir_engorde = ll.findViewById(R.id.card_elegir_engorde)
        this.card_elegir_sementales = ll.findViewById(R.id.card_elegir_sementales)
        this.card_elegir_terneros = ll.findViewById(R.id.card_elegir_terneros)

        ll.isFocusableInTouchMode = true
        ll.requestFocus()
        ll.setOnKeyListener { v, keyCode, event ->
            keyCode == KeyEvent.KEYCODE_BACK
        }



        card_elegir_lechera.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.action_elegir_categoria_to_fragment_formulario_lecheras)
        }

        card_elegir_gestacion.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.action_elegir_categoria_to_fragment_formulario_gestacion)
        }

        card_elegir_secado.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.action_elegir_categoria_to_fragment_formulario_secado)
        }

        card_elegir_engorde.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.action_elegir_categoria_to_fragment_formulario_engorde)
        }

        card_elegir_sementales.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.action_elegir_categoria_to_fragment_formulario_sementales)
        }

        card_elegir_terneros.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.action_elegir_categoria_to_fragment_formulario_ternero)
        }



        return ll
    }


}