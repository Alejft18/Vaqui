package com.example.vaqui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController


class elegir_cambio_machoFragment : Fragment() {
    private lateinit var card_cambio_engorde_macho : CardView
    private lateinit var card_cambio_toro : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val idCambio = arguments?.getString("id_cambio")
        val funcionEliminar = arguments?.getString("funcion_eliminar")

        println("id: $idCambio ,funcion a hacer: $funcionEliminar")

        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_macho, container, false)
        this.card_cambio_engorde_macho = ll.findViewById(R.id.card_cambio_engorde_macho)
        this.card_cambio_toro = ll.findViewById(R.id.card_cambio_toro)

        card_cambio_engorde_macho.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaEngordeFragment = CambioCategoriaEngordeFragment()
            cambioCategoriaEngordeFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegir_cambio_machoFragment_to_cambioCategoriaEngordeFragment,bundle)

        }

        card_cambio_toro.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaToroFragment = CambioCategoriaToroFragment()
            cambioCategoriaToroFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegir_cambio_machoFragment_to_cambioCategoriaToroFragment,bundle)
        }



        return ll
    }


}