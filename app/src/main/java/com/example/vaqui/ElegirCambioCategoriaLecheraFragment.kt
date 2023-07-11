package com.example.vaqui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView


class ElegirCambioCategoriaLecheraFragment : Fragment() {
    private lateinit var card_cambio_ternero_lecheras : CardView
    private lateinit var card_cambio_engorde_lechera : CardView
    private lateinit var card_cambio_secado_lechera : CardView
    private lateinit var card_cambio_gestacion_lechera : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_categoria_lechera, container, false)

        this.card_cambio_ternero_lecheras = ll.findViewById(R.id.card_cambio_ternero_lecheras)
        this.card_cambio_engorde_lechera = ll.findViewById(R.id.card_cambio_engorde_lechera)
        this.card_cambio_secado_lechera = ll.findViewById(R.id.card_cambio_secado_lechera)
        this.card_cambio_gestacion_lechera = ll.findViewById(R.id.card_cambio_gestacion_lechera)

        return ll
    }

}