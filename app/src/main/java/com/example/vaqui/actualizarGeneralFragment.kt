package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.navigation.fragment.findNavController


class actualizarGeneralFragment : Fragment() {
    private lateinit var imagen_atras_actualizar_general : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ll =  inflater.inflate(R.layout.fragment_actualizar_general, container, false)
        this.imagen_atras_actualizar_general = ll.findViewById(R.id.imagen_atras_actualizar_general)

       imagen_atras_actualizar_general.setOnClickListener {
            findNavController().navigate(R.id.action_actualizarGeneralFragment_to_generalfragment)
        }


        return ll
    }

}