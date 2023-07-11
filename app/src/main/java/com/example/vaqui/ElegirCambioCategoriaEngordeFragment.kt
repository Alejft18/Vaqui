package com.example.vaqui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView


class ElegirCambioCategoriaEngordeFragment : Fragment() {
    private lateinit var card_cambio_lecheras_engorde : CardView
    private lateinit var card_cambio_ternero_engorde : CardView
    private lateinit var card_cambio_secado_engorde : CardView
    private lateinit var card_cambio_gestacion_engorde : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_elegir_cambio_categoria_engorde, container, false)
        this.card_cambio_lecheras_engorde = ll.findViewById(R.id.card_cambio_lecheras_engorde)
        this.card_cambio_ternero_engorde = ll.findViewById(R.id.card_cambio_ternero_engorde)
        this.card_cambio_secado_engorde = ll.findViewById(R.id.card_cambio_secado_engorde)
        this.card_cambio_gestacion_engorde = ll.findViewById(R.id.card_cambio_gestacion_engorde)

        return ll
    }

}