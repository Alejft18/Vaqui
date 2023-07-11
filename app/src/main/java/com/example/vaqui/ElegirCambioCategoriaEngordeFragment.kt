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


class ElegirCambioCategoriaEngordeFragment : Fragment() {
    private lateinit var imagen_atras_elegir_cambio_engorde : ImageView
    private lateinit var card_cambio_lecheras_engorde : CardView
    private lateinit var card_cambio_ternero_engorde : CardView
    private lateinit var card_cambio_secado_engorde : CardView
    private lateinit var card_cambio_gestacion_engorde : CardView
    private lateinit var card_cambio_toro_engorde : CardView

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
        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_categoria_engorde, container, false)
        this.imagen_atras_elegir_cambio_engorde = ll.findViewById(R.id.imagen_atras_elegir_cambio_engorde)
        this.card_cambio_lecheras_engorde = ll.findViewById(R.id.card_cambio_lecheras_engorde)
        this.card_cambio_ternero_engorde = ll.findViewById(R.id.card_cambio_ternero_engorde)
        this.card_cambio_secado_engorde = ll.findViewById(R.id.card_cambio_secado_engorde)
        this.card_cambio_gestacion_engorde = ll.findViewById(R.id.card_cambio_gestacion_engorde)
        this.card_cambio_toro_engorde = ll.findViewById(R.id.card_cambio_toro_engorde)

        imagen_atras_elegir_cambio_engorde.setOnClickListener {
            findNavController().navigate(R.id.action_elegirCambioCategoriaEngordeFragment_to_categorias)
        }

        card_cambio_lecheras_engorde.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaLecheraFragment= CambioCategoriaLecheraFragment()
            cambioCategoriaLecheraFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaEngordeFragment_to_cambioCategoriaLecheraFragment,bundle)
        }

        card_cambio_ternero_engorde.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaTerneroFragment=CambioCategoriaTerneroFragment()
            cambioCategoriaTerneroFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaEngordeFragment_to_cambioCategoriaTerneroFragment,bundle)
        }

        card_cambio_secado_engorde.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaSecadoFragment = CambioCategoriaSecadoFragment()
            cambioCategoriaSecadoFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaEngordeFragment_to_cambioCategoriaSecadoFragment,bundle)
        }

        card_cambio_gestacion_engorde.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaGestacionFragment = CambioCategoriaGestacionFragment()
            cambioCategoriaGestacionFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaEngordeFragment_to_cambioCategoriaGestacionFragment, bundle)
        }

        card_cambio_toro_engorde.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaToroFragment = CambioCategoriaToroFragment()
            cambioCategoriaToroFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaEngordeFragment_to_cambioCategoriaToroFragment,bundle)
        }




        return ll
    }

}