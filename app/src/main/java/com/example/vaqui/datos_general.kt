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
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.vaqui.adapter.BovinosListener
import org.json.JSONObject


class datos_general : DialogFragment() {
    private lateinit var tvBarra_general : Toolbar
    private lateinit var recycler : RecyclerView
    private lateinit var viewAlpha : View
    private lateinit var rlGeneralList : RelativeLayout
    private lateinit var id_general : TextView
    private lateinit var fecha_general : TextView
    private lateinit var peso_general : TextView
    private lateinit var raza_general : TextView
    private lateinit var genero_general : TextView
    private lateinit var procedencia : TextView
    private lateinit var img_general : ImageView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll=inflater.inflate(R.layout.fragment_datos_general,container,false)
        this.tvBarra_general = ll.findViewById(R.id.tvBarra_general)

        this.id_general = ll.findViewById(R.id.id_general)
        this.raza_general = ll.findViewById(R.id.raza_general)
        this.genero_general = ll.findViewById(R.id.genero_general)
        this.fecha_general = ll.findViewById(R.id.fecha_general)
        this.peso_general = ll.findViewById(R.id.peso_general)
        this.procedencia = ll.findViewById(R.id.procedencia)
        this.img_general = ll.findViewById(R.id.img_general)

        this.recycler = ll.findViewById(R.id.bovinos_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_BovinosGeneral)
        this.rlGeneralList = ll.findViewById(R.id.rl_BovinosGeneral)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tvBarra_general.navigationIcon=ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_close_24)
        this.tvBarra_general.setNavigationOnClickListener {
            dismiss()
        }

    val general = JSONObject(arguments?.getString("tbl_general"))
        this.id_general.text=general.getString("id")
        this.raza_general.text=general.getString("raza")
        this.genero_general.text=general.getString("genero")
        this.fecha_general.text=general.getString("fecha_nacimiento")
        this.procedencia.text=general.getString("procedencia")

        Glide.with(this)
            .load(general.getString("imagen"))
            .into(this.img_general)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }




}