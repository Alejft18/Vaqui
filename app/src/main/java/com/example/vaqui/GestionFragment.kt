package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation


class GestionFragment : Fragment() {
    private lateinit var card_buscar_bovi : CardView
    private lateinit var card_ingresar_bovino: CardView

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

        card_buscar_bovi.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.generalfragment2)
        }

        card_ingresar_bovino.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.formulario_general)
        }

        return ll
    }

}