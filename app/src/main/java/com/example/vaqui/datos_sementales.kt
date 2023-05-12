package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class datos_sementales : DialogFragment() {
    private lateinit var tvBarra_toro : Toolbar
    private lateinit var recycler : RecyclerView
    private lateinit var viewAlpha : View
    private lateinit var rltoro : RelativeLayout

    private lateinit var id_toro : TextView
    private lateinit var peso_toro : TextView
    private lateinit var fecha_extraccion_toro : TextView
    private lateinit var vacas_montadas_toro : TextView
    private lateinit var fecha_revision_toro : TextView
    private lateinit var catego_toro : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll= inflater.inflate(R.layout.fragment_datos_sementales, container, false)
        this.tvBarra_toro = ll.findViewById(R.id.tvBarra_toro)

        this.id_toro = ll.findViewById(R.id.id_toro)
        this.peso_toro = ll.findViewById(R.id.peso_toro)
        this.fecha_extraccion_toro = ll.findViewById(R.id.fecha_extraccion_toro)
        this.vacas_montadas_toro = ll.findViewById(R.id.vacas_montadas_toro)
        this.fecha_revision_toro = ll.findViewById(R.id.fecha_revision_toro)
        this.catego_toro = ll.findViewById(R.id.catego_toro)

        this.recycler = ll.findViewById(R.id.toros_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_Toros)
        this.rltoro = ll.findViewById(R.id.rl_Toros)

        return ll
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tvBarra_toro.navigationIcon= ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_close_24)
        this.tvBarra_toro.setNavigationOnClickListener {
            dismiss()
        }

    val toros = JSONObject(arguments?.getString("toros"))
        this.id_toro.text=toros.getString("id")
        this.peso_toro.text=toros.getString("peso_kilos")
        this.fecha_extraccion_toro.text=toros.getString("fecha_extraccion")
        this.vacas_montadas_toro.text=toros.getString("vacas_montadas")
        this.fecha_revision_toro.text=toros.getString("fecha_revision")
        this.catego_toro.text=toros.getString("categoria")

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}