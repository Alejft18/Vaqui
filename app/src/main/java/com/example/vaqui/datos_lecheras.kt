package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject


class datos_lecheras : DialogFragment() {
    private lateinit var tvBarra_lechera : Toolbar
    private lateinit var recycler : RecyclerView
    private lateinit var viewAlpha : View
    private lateinit var rlLecheras : RelativeLayout

    private lateinit var id_lechera : TextView
    private lateinit var fecha_revision_lechera : TextView
    private lateinit var peso_lechera : TextView
    private lateinit var partos_lechera : TextView
    private lateinit var fecha_ultiParto_leche : TextView
    private lateinit var fecha_ordeno_leche : TextView
    private lateinit var litros_producidos : TextView
    private lateinit var catego_lechera : TextView

    private lateinit var btn_actualizar_lecheras : Button
    private lateinit var btn_cambiar_categoria_lechera : Button
    private val funcionEliminar = "eliminarLechera/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, androidx.appcompat.R.style.AlertDialog_AppCompat)
    }

    @SuppressLint("MissingInflateId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll=  inflater.inflate(R.layout.fragment_datos_lecheras, container, false)
        this.tvBarra_lechera = ll.findViewById(R.id.tvBarra_lechera)

        this.id_lechera = ll.findViewById(R.id.id_lechera)
        this.fecha_revision_lechera = ll.findViewById(R.id.fecha_revision_lechera)
        this.peso_lechera = ll.findViewById(R.id.peso_lechera)
        this.partos_lechera = ll.findViewById(R.id.partos_lechera)
        this.fecha_ultiParto_leche = ll.findViewById(R.id.fecha_ultiParto_leche)
        this.fecha_ordeno_leche = ll.findViewById(R.id.fecha_ordeno_leche)
        this.litros_producidos = ll.findViewById(R.id.litros_producidos)
        this.catego_lechera = ll.findViewById(R.id.catego_lechera)

        this.btn_actualizar_lecheras = ll.findViewById(R.id.btn_actualizar_lecheras)
        this.btn_cambiar_categoria_lechera = ll.findViewById(R.id.btn_cambiar_categoria_lechera)

        this.recycler = ll.findViewById(R.id.lecheras_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_lecheras)
        this.rlLecheras = ll.findViewById(R.id.rl_lecheras)

        btn_actualizar_lecheras.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_lechera",id_lechera.text.toString())
            bundle.putString("fecha_revision_lechera",fecha_revision_lechera.text.toString())
            bundle.putString("peso_lechera",peso_lechera.text.toString())
            bundle.putString("partos_lechera",partos_lechera.text.toString())
            bundle.putString("fecha_ultiParto_leche",fecha_ultiParto_leche.text.toString())
            bundle.putString("fecha_ordeno_leche",fecha_ordeno_leche.text.toString())
            bundle.putString("litros_producidos",litros_producidos.text.toString())

            val actualizarLecherasFragment = actualizarLecherasFragment()
            actualizarLecherasFragment.arguments = bundle
            findNavController().navigate(R.id.action_datos_lecheras_to_actualizarLecherasFragment, bundle)
        }

        btn_cambiar_categoria_lechera.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_cambio",id_lechera.text.toString())
            bundle.putString("funcion_eliminar",funcionEliminar)
            val elegirCambioCategoriaLecheraFragment = ElegirCambioCategoriaLecheraFragment()
            elegirCambioCategoriaLecheraFragment.arguments = bundle
            findNavController().navigate(R.id.action_datos_lecheras_to_elegirCambioCategoriaLecheraFragment, bundle)
        }


        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tvBarra_lechera.navigationIcon= ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_close_24)
        this.tvBarra_lechera.setNavigationOnClickListener {
            dismiss()
        }

        val lecheras = JSONObject(arguments?.getString("lecheras"))
            this.id_lechera.text=lecheras.getString("id")
            this.litros_producidos.text=lecheras.getString("litros_producidos")
            this.fecha_ordeno_leche.text=lecheras.getString("fecha_ordeno")
            this.peso_lechera.text=lecheras.getString("peso_kilos")
            this.fecha_revision_lechera.text=lecheras.getString("fecha_revision")
            this.fecha_ultiParto_leche.text=lecheras.getString("fecha_parto")
            this.partos_lechera.text=lecheras.getString("cant_partos")
            this.catego_lechera.text=lecheras.getString("categoria")
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}