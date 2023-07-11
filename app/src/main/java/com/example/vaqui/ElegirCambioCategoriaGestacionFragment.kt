package com.example.vaqui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView


class ElegirCambioCategoriaGestacionFragment : Fragment() {
    private lateinit var card_cambio_lecheras_gestacion : CardView
    private lateinit var card_cambio_engorde_gestacion : CardView
    private lateinit var card_cambio_secado_gestacion : CardView
    private lateinit var card_cambio_ternero_gestacion : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_categoria_gestacion, container, false)

        this.card_cambio_lecheras_gestacion = ll.findViewById(R.id.card_cambio_lecheras_gestacion)
        this.card_cambio_engorde_gestacion = ll.findViewById(R.id.card_cambio_engorde_gestacion)
        this.card_cambio_secado_gestacion = ll.findViewById(R.id.card_cambio_secado_gestacion)
        this.card_cambio_ternero_gestacion = ll.findViewById(R.id.card_cambio_ternero_gestacion)


        return ll
    }

}