package com.example.vaqui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView


class ElegirCambioCategoriaSecadoFragment : Fragment() {
    private lateinit var card_cambio_lecheras_secado : CardView
    private lateinit var card_cambio_engorde_secado : CardView
    private lateinit var card_cambio_ternero_secado : CardView
    private lateinit var card_cambio_gestacion_secado : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_categoria_secado, container, false)

        this.card_cambio_lecheras_secado = ll.findViewById(R.id.card_cambio_lecheras_secado)
        this.card_cambio_engorde_secado = ll.findViewById(R.id.card_cambio_engorde_secado)
        this.card_cambio_ternero_secado = ll.findViewById(R.id.card_cambio_ternero_secado)
        this.card_cambio_gestacion_secado = ll.findViewById(R.id.card_cambio_gestacion_secado)

        return ll
    }

}