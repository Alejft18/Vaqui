package com.example.vaqui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController


class ElegirCambioCategoriaTerneroFragment : Fragment() {
    private lateinit var imagen_atras_elegir_cambio_ternero : ImageView
    private lateinit var card_cambio_a_lechera_ternero : CardView
    private lateinit var card_cambio_a_gestacion_ternero : CardView
    private lateinit var card_cambio_a_secado_ternero : CardView
    private lateinit var card_cambio_a_engorde_ternero : CardView
    private lateinit var card_cambio_a_toro_ternero : CardView


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

        val ll =  inflater.inflate(R.layout.fragment_elegir_cambio_categoria_ternero, container, false)
        this.imagen_atras_elegir_cambio_ternero = ll.findViewById(R.id.imagen_atras_elegir_cambio_ternero)
        this.card_cambio_a_lechera_ternero = ll.findViewById(R.id.card_cambio_a_lechera_ternero)
        this.card_cambio_a_gestacion_ternero = ll.findViewById(R.id.card_cambio_a_gestacion_ternero)
        this.card_cambio_a_secado_ternero = ll.findViewById(R.id.card_cambio_a_secado_ternero)
        this.card_cambio_a_engorde_ternero = ll.findViewById(R.id.card_cambio_a_engorde_ternero)
        this.card_cambio_a_toro_ternero = ll.findViewById(R.id.card_cambio_a_toro_ternero)

        imagen_atras_elegir_cambio_ternero.setOnClickListener {
            findNavController().navigate(R.id.action_elegirCambioCategoriaTerneroFragment_to_categorias)
        }

        card_cambio_a_lechera_ternero.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaLecheraFragment = CambioCategoriaLecheraFragment()
            cambioCategoriaLecheraFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaTerneroFragment_to_cambioCategoriaLecheraFragment,bundle)
        }

        card_cambio_a_gestacion_ternero.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaGestacionFragment =CambioCategoriaGestacionFragment()
            cambioCategoriaGestacionFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaTerneroFragment_to_cambioCategoriaGestacionFragment,bundle)
        }

        card_cambio_a_secado_ternero.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaSecadoFragment = CambioCategoriaSecadoFragment()
            cambioCategoriaSecadoFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaTerneroFragment_to_cambioCategoriaSecadoFragment,bundle)
        }

        card_cambio_a_engorde_ternero.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaEngordeFragment = CambioCategoriaEngordeFragment()
            cambioCategoriaEngordeFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaTerneroFragment_to_cambioCategoriaEngordeFragment,bundle)
        }

        card_cambio_a_toro_ternero.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaToroFragment=CambioCategoriaToroFragment()
            cambioCategoriaToroFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaTerneroFragment_to_cambioCategoriaToroFragment,bundle)

        }





        return ll
    }

}