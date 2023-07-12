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


class ElegirCambioCategoriaSecadoFragment : Fragment() {
    private lateinit var imagen_atras_cambio_secado : ImageView
    private lateinit var card_cambio_lecheras_secado : CardView
    private lateinit var card_cambio_engorde_secado : CardView
    private lateinit var card_cambio_ternero_secado : CardView
    private lateinit var card_cambio_gestacion_secado : CardView

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
        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_categoria_secado, container, false)

        this.imagen_atras_cambio_secado = ll.findViewById(R.id.imagen_atras_cambio_secado)
        this.card_cambio_lecheras_secado = ll.findViewById(R.id.card_cambio_lecheras_secado)
        this.card_cambio_engorde_secado = ll.findViewById(R.id.card_cambio_engorde_secado)
        this.card_cambio_ternero_secado = ll.findViewById(R.id.card_cambio_ternero_secado)
        this.card_cambio_gestacion_secado = ll.findViewById(R.id.card_cambio_gestacion_secado)


        imagen_atras_cambio_secado.setOnClickListener {
            findNavController().navigate(R.id.action_elegirCambioCategoriaSecadoFragment_to_categorias)
        }


        card_cambio_lecheras_secado.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaLecheraFragment = CambioCategoriaLecheraFragment()
            cambioCategoriaLecheraFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaSecadoFragment_to_cambioCategoriaLecheraFragment,bundle)
        }

        card_cambio_engorde_secado.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioCategoriaEngordeFragment = CambioCategoriaEngordeFragment()
            cambioCategoriaEngordeFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaSecadoFragment_to_cambioCategoriaEngordeFragment,bundle)
        }

        card_cambio_ternero_secado.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio", idCambio.toString())
            bundle.putString("funcion_eliminar", funcionEliminar.toString())
            val cambioCategoriaTerneroFragment = CambioCategoriaTerneroFragment()
            cambioCategoriaTerneroFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaSecadoFragment_to_cambioCategoriaTerneroFragment,bundle)
        }

        card_cambio_gestacion_secado.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",idCambio.toString())
            bundle.putString("funcion_eliminar",funcionEliminar.toString())
            val cambioGestionEmpleadosFragment = CambioCategoriaGestacionFragment()
            cambioGestionEmpleadosFragment.arguments = bundle
            findNavController().navigate(R.id.action_elegirCambioCategoriaSecadoFragment_to_cambioCategoriaGestacionFragment,bundle)

        }

        return ll
    }

}