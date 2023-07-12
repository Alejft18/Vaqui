package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController


class ElegirCambioCategoriaToroFragment : Fragment() {
    private lateinit var imagen_atras_elegir_cambio_toro : ImageView
    private lateinit var card_cambio_a_engorde_toro : CardView
    private lateinit var card_cambio_a_ternero_toro : CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val idCambio = arguments?.getString("id_cambio")
        val funcionEliminar = arguments?.getString("funcion_eliminar")

        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_categoria_toro, container, false)
        this.imagen_atras_elegir_cambio_toro = ll.findViewById(R.id.imagen_atras_elegir_cambio_toro)
        this.card_cambio_a_engorde_toro = ll.findViewById(R.id.card_cambio_a_engorde_toro)
        this.card_cambio_a_ternero_toro = ll.findViewById(R.id.card_cambio_a_ternero_toro)

        imagen_atras_elegir_cambio_toro.setOnClickListener {
            findNavController().navigate(R.id.action_elegirCambioCategoriaToroFragment_to_categorias)
        }

        card_cambio_a_engorde_toro.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaEngordeFragment = CambioCategoriaEngordeFragment()
            cambioCategoriaEngordeFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaToroFragment_to_cambioCategoriaEngordeFragment,bundle)

        }

        card_cambio_a_ternero_toro.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaTerneroFragment = CambioCategoriaTerneroFragment()
            cambioCategoriaTerneroFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaToroFragment_to_cambioCategoriaTerneroFragment,bundle)
        }



        return ll
    }

}