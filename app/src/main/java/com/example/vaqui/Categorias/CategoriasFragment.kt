package com.example.vaqui.Categorias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import com.example.vaqui.R

class CategoriasFragment : Fragment() {
    private lateinit var cateGenera : CardView
    private lateinit var cateLecheras : CardView
    private lateinit var cateGesta : CardView
    private lateinit var cateSecado : CardView
    private lateinit var cateSemental : CardView
    private lateinit var cateTernero : CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_categorias, container, false)

        this.cateGenera = ll.findViewById(R.id.cateGeneral)
        this.cateLecheras = ll.findViewById(R.id.cateLecheras)
        this.cateGesta = ll.findViewById(R.id.cateGesta)
        this.cateSecado = ll.findViewById(R.id.cateSecado)
        this.cateSemental = ll.findViewById(R.id.cateSemental)
        this.cateTernero = ll.findViewById(R.id.cateTernero)

        cateGenera.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.generalfragment)
        }

        /*cateLecheras.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.lecherasfragment)
        }

        cateGesta.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.gestafragment)
        }

        cateSecado.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.secadoFragment)
        }

        cateSemental.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.sementalfragment)
        }

        cateTernero.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.ternerofragment)
        }*/

        return ll
    }




}