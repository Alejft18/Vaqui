package com.example.vaqui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController


class fragment_home_estadisticas_empleado : Fragment() {
    private lateinit var card_gestion_empleados : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_home_estadisticas_empleado, container, false)
        this.card_gestion_empleados = ll.findViewById(R.id.card_gestion_empleados)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card_gestion_empleados.setOnClickListener{
            findNavController().navigate(R.id.action_inicio_to_gestionEmpleadosFragment)
        }


    }


}