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


class ElegirCambioCategoriaLecheraFragment : Fragment() {
    private lateinit var imagen_atras_cambiar_lechera : ImageView
    private lateinit var card_cambio_ternero_lecheras : CardView
    private lateinit var card_cambio_engorde_lechera : CardView
    private lateinit var card_cambio_secado_lechera : CardView
    private lateinit var card_cambio_gestacion_lechera : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_categoria_lechera, container, false)

        val idCambio = arguments?.getString("id_cambio")
        val funcionEliminar = arguments?.getString("funcion_eliminar")

        this.imagen_atras_cambiar_lechera = ll.findViewById(R.id.imagen_atras_cambiar_lechera)
        this.card_cambio_ternero_lecheras = ll.findViewById(R.id.card_cambio_ternero_lecheras)
        this.card_cambio_engorde_lechera = ll.findViewById(R.id.card_cambio_engorde_lechera)
        this.card_cambio_secado_lechera = ll.findViewById(R.id.card_cambio_secado_lechera)
        this.card_cambio_gestacion_lechera = ll.findViewById(R.id.card_cambio_gestacion_lechera)

        imagen_atras_cambiar_lechera.setOnClickListener {
            findNavController().navigate(R.id.action_elegirCambioCategoriaLecheraFragment_to_categorias)
        }


        card_cambio_ternero_lecheras.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaTerneroFragment = CambioCategoriaToroFragment()
            cambioCategoriaTerneroFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaLecheraFragment_to_cambioCategoriaTerneroFragment,bundle)
        }

        card_cambio_engorde_lechera.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val  cambioCategoriaEngordeFragment=CambioCategoriaEngordeFragment()
            cambioCategoriaEngordeFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaLecheraFragment_to_cambioCategoriaEngordeFragment,bundle)
        }

        card_cambio_secado_lechera.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val  cambioCategoriaSecadoFragment= CambioCategoriaSecadoFragment()
            cambioCategoriaSecadoFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaLecheraFragment_to_cambioCategoriaSecadoFragment,bundle)
        }

        card_cambio_gestacion_lechera.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val  cambioCategoriaGestacionFragment= CambioCategoriaGestacionFragment()
            cambioCategoriaGestacionFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaLecheraFragment_to_cambioCategoriaGestacionFragment,bundle)
        }

        return ll
    }

}