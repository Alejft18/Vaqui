package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONObject

class datos_gestacion : DialogFragment() {
    private lateinit var tvBarra_gestacion : Toolbar
    private lateinit var recycler : RecyclerView
    private lateinit var viewAlpha : View
    private lateinit var rlGestacionList : RelativeLayout

    private lateinit var fecha_revision_gestacion : TextView
    private lateinit var id_gestacion : TextView
    private lateinit var peso_gestacion : TextView
    private lateinit var fecha_aproxPartoGesta : TextView
    private lateinit var fecha_ultiPartoGesta : TextView
    private lateinit var fecha_inseminacionGesta : TextView
    private lateinit var catego_gestacion : TextView

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
        val ll=inflater.inflate(R.layout.fragment_datos_gestacion,container,false)
        this.tvBarra_gestacion = ll.findViewById(R.id.tvBarra_gestacion)

        this.fecha_revision_gestacion = ll.findViewById(R.id.fecha_revision_gestacion)
        this.id_gestacion = ll.findViewById(R.id.id_gestacion)
        this.peso_gestacion = ll.findViewById(R.id.peso_gestacion)
        this.fecha_aproxPartoGesta = ll.findViewById(R.id.fecha_aproxPartoGesta)
        this.fecha_ultiPartoGesta = ll.findViewById(R.id.fecha_ultiPartoGesta)
        this.fecha_inseminacionGesta = ll.findViewById(R.id.fecha_inseminacionGesta)
        this.catego_gestacion = ll.findViewById(R.id.catego_gestacion)

        this.recycler = ll.findViewById(R.id.gestacion_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_gestacion)
        this.rlGestacionList = ll.findViewById(R.id.rl_gestacion)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tvBarra_gestacion.navigationIcon= ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_close_24)
        this.tvBarra_gestacion.setNavigationOnClickListener {
            dismiss()
        }

        val gestacion = JSONObject(arguments?.getString("gestacion"))
        this.id_gestacion.text=gestacion.getString("id")
        this.peso_gestacion.text=gestacion.getString("peso_kilos")
        this.fecha_inseminacionGesta.text=gestacion.getString("fecha_inseminacion")
        this.fecha_aproxPartoGesta.text=gestacion.getString("fecha_aproxParto")
        this.fecha_ultiPartoGesta.text=gestacion.getString("fecha_ultimoParto")
        this.fecha_revision_gestacion.text=gestacion.getString("fecha_revision")
        this.catego_gestacion.text=gestacion.getString("categoria")

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}