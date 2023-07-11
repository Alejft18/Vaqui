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


class ElegirCambioCategoriaGestacionFragment : Fragment() {
    private lateinit var imagen_atras_cambiar_gestacion : ImageView
    private lateinit var card_cambio_lecheras_gestacion : CardView
    private lateinit var card_cambio_engorde_gestacion : CardView
    private lateinit var card_cambio_secado_gestacion : CardView
    private lateinit var card_cambio_ternero_gestacion : CardView

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

        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_categoria_gestacion, container, false)

        this.imagen_atras_cambiar_gestacion = ll.findViewById(R.id.imagen_atras_cambiar_gestacion)
        this.card_cambio_lecheras_gestacion = ll.findViewById(R.id.card_cambio_lecheras_gestacion)
        this.card_cambio_engorde_gestacion = ll.findViewById(R.id.card_cambio_engorde_gestacion)
        this.card_cambio_secado_gestacion = ll.findViewById(R.id.card_cambio_secado_gestacion)
        this.card_cambio_ternero_gestacion = ll.findViewById(R.id.card_cambio_ternero_gestacion)

        imagen_atras_cambiar_gestacion.setOnClickListener {
            findNavController().navigate(R.id.action_elegirCambioCategoriaGestacionFragment_to_categorias)
        }

        card_cambio_lecheras_gestacion.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaLecheraFragment = CambioCategoriaLecheraFragment()
            cambioCategoriaLecheraFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaGestacionFragment_to_cambioCategoriaLecheraFragment,bundle)
        }

        card_cambio_engorde_gestacion.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioEngordeFragment = CambioCategoriaEngordeFragment()
            cambioEngordeFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaGestacionFragment_to_cambioCategoriaEngordeFragment,bundle)
        }

        card_cambio_secado_gestacion.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaSecadoFragment = CambioCategoriaSecadoFragment()
            cambioCategoriaSecadoFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaGestacionFragment_to_cambioCategoriaSecadoFragment,bundle)
        }

        card_cambio_ternero_gestacion.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaTerneroFragment = CambioCategoriaTerneroFragment()
            cambioCategoriaTerneroFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaGestacionFragment_to_cambioCategoriaTerneroFragment,bundle)
        }



        return ll
    }

}