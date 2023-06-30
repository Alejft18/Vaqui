package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
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

class datos_terneros : DialogFragment() {
    private lateinit var tvBarra_ternero : Toolbar
    private lateinit var recycler : RecyclerView
    private lateinit var viewAlpha : View
    private lateinit var rlTernero : RelativeLayout

    private lateinit var id_ternero : TextView
    private lateinit var peso_ternero : TextView
    private lateinit var id_madre_ternero : TextView
    private lateinit var fecha_revision_ternero : TextView
    private lateinit var catego_ternero : TextView

    private lateinit var btn_actualizar_ternero : Button
    private lateinit var btn_cambiar_categoria_ternero : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, androidx.appcompat.R.style.AlertDialog_AppCompat)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll= inflater.inflate(R.layout.fragment_datos_terneros, container, false)
        this.tvBarra_ternero = ll.findViewById(R.id.tvBarra_ternero)

        this.id_ternero= ll.findViewById(R.id.id_ternero)
        this.peso_ternero= ll.findViewById(R.id.peso_ternero)
        this.id_madre_ternero= ll.findViewById(R.id.id_madre_Ternero)
        this.fecha_revision_ternero= ll.findViewById(R.id.fecha_revision_ternero)
        this.catego_ternero = ll.findViewById(R.id.catego_ternero)

        this.btn_actualizar_ternero = ll.findViewById(R.id.btn_actualizar_ternero)
        this.btn_cambiar_categoria_ternero = ll.findViewById(R.id.btn_cambiar_categoria_ternero)

        this.recycler = ll.findViewById(R.id.terneros_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_Terneros)
        this.rlTernero = ll.findViewById(R.id.rl_Terneros)

        btn_actualizar_ternero.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_ternero",id_ternero.text.toString())
            bundle.putString("peos_ternero",peso_ternero.text.toString())
            bundle.putString("id_madre",id_madre_ternero.text.toString())
            bundle.putString("fecha_revision",fecha_revision_ternero.text.toString())

            val actualizarTernerosFragment = actualizarTernerosFragment()
            actualizarTernerosFragment.arguments = bundle
            findNavController().navigate(R.id.action_datos_terneros_to_actualizarTernerosFragment,bundle)
        }

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tvBarra_ternero.navigationIcon= ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_close_24)
        this.tvBarra_ternero.setNavigationOnClickListener {
            dismiss()
        }

    val terneros = JSONObject(arguments?.getString("terneros"))
        this.id_ternero.text=terneros.getString("id")
        this.id_madre_ternero.text=terneros.getString("id_madre")
        this.peso_ternero.text=terneros.getString("peso_kilos")
        this.fecha_revision_ternero.text=terneros.getString("fecha_revision")
        this.catego_ternero.text=terneros.getString("categoria")

    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }



}
