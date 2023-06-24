package com.example.vaqui.Categorias

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
    private lateinit var cateEngorde : CardView
    private lateinit var btn_perfil_categoria : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @SuppressLint("MissingInflatedId")
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
        this.cateEngorde = ll.findViewById(R.id.cateEngorde)
        this.btn_perfil_categoria = ll.findViewById(R.id.btn_perfil_categoria)

        btn_perfil_categoria.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_container)
            navController.navigate(R.id.perfilFragment)
        }

        cateGenera.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.generalfragment)
        }

        cateLecheras.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.lecherasfragment)
        }

        cateGesta.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.gestacionfragment)
        }

        cateSecado.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.secadofragment)
        }

        cateSemental.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.torofragment)
        }

        cateTernero.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.ternerofragment)
        }

        cateEngorde.setOnClickListener{
            val navController= Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container)
            navController.navigate(R.id.engordefragment )
        }

        return ll
    }




}