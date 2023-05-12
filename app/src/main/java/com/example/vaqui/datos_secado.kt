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


class datos_secado : DialogFragment() {
    private lateinit var tvBarra_secado : Toolbar
    private lateinit var recycler : RecyclerView
    private lateinit var viewAlpha : View
    private lateinit var rlSecado : RelativeLayout
    private lateinit var id_secado : TextView
    private lateinit var fecha_revision_secado : TextView
    private lateinit var peso_secado : TextView
    private lateinit var fecha_ordeno_secado : TextView
    private lateinit var fecha_ultiParto_seca : TextView
    private lateinit var catego_secado : TextView


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
        val ll= inflater.inflate(R.layout.fragment_datos_secado, container, false)
        this.tvBarra_secado = ll.findViewById(R.id.tvBarra_secado)

        this.id_secado = ll.findViewById(R.id.id_secado)
        this.fecha_revision_secado = ll.findViewById(R.id.fecha_revision_secado)
        this.peso_secado = ll.findViewById(R.id.peso_secado)
        this.fecha_ordeno_secado= ll.findViewById(R.id.fecha_ordeño_secado)
        this.fecha_ultiParto_seca = ll.findViewById(R.id.fecha_ultiParto_seca)
        this.catego_secado = ll.findViewById(R.id.catego_secado)

        this.recycler = ll.findViewById(R.id.secado_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_secado)
        this.rlSecado= ll.findViewById(R.id.rl_secado)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tvBarra_secado.navigationIcon= ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_close_24)
        this.tvBarra_secado.setNavigationOnClickListener {
            dismiss()
        }

    val secado = JSONObject(arguments?.getString("secado"))
        this.id_secado.text=secado.getString("id")
        this.fecha_ultiParto_seca.text=secado.getString("fecha_ultParto")
        this.peso_secado.text=secado.getString("peso_kilos")
        this.fecha_revision_secado.text=secado.getString("fecha_revision")
        this.fecha_ordeno_secado.text=secado.getString("fecha_ordeño")
        this.catego_secado.text=secado.getString("categoria")

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }


}