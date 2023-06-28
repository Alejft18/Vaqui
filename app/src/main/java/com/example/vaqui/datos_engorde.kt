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
import com.bumptech.glide.Glide
import org.json.JSONObject


class datos_engorde : DialogFragment() {
    private lateinit var tvBarra_engorde : Toolbar
    private lateinit var recycler : RecyclerView
    private lateinit var viewAlpha : View
    private lateinit var rlEngordeList : RelativeLayout
    private lateinit var fc_revision_engorde : TextView
    private lateinit var id_engorde : TextView
    private lateinit var peso_engorde : TextView
    private lateinit var alimento_engorde : TextView
    private lateinit var catego_engorde : TextView

    private lateinit var btn_actualizar_secado : Button
    private lateinit var btn_cambiar_categoria_secado : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll=inflater.inflate(R.layout.fragment_datos_engorde,container,false)
        this.tvBarra_engorde = ll.findViewById(R.id.tvBarra_engorde)

        this.fc_revision_engorde = ll.findViewById(R.id.fc_revision_engorde)
        this.id_engorde = ll.findViewById(R.id.id_engorde)
        this.peso_engorde = ll.findViewById(R.id.peso_engorde)
        this.alimento_engorde = ll.findViewById(R.id.alimento_engorde)
        this.catego_engorde = ll.findViewById(R.id.catego_engorde)


        this.recycler = ll.findViewById(R.id.engorde_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_BovinosEngorde)
        this.rlEngordeList = ll.findViewById(R.id.rl_Bovinosengorde)

        this.btn_actualizar_secado = ll.findViewById(R.id.btn_actualizar_secado)
        this.btn_cambiar_categoria_secado = ll.findViewById(R.id.btn_cambiar_categoria_secado)

        btn_actualizar_secado.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_engorde",id_engorde.text.toString())
            bundle.putString("peso_engorde",peso_engorde.text.toString())
            bundle.putString("alimento_engorde",alimento_engorde.text.toString())
            bundle.putString("fecha_revision_engorde",fc_revision_engorde.text.toString())
            bundle.putString("categoria_engorde",catego_engorde.text.toString())

            val actualizarEngordeFragment = actualizarEngordeFragment()
            actualizarEngordeFragment.arguments = bundle
            findNavController().navigate(R.id.action_datos_engorde_to_actualizarEngordeFragment, bundle)
        }

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.tvBarra_engorde.navigationIcon= ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_close_24)
        this.tvBarra_engorde.setNavigationOnClickListener {
            dismiss()
        }

        val general = JSONObject(arguments?.getString("engorde"))
        this.id_engorde.text=general.getString("id")
        this.peso_engorde.text=general.getString("peso_kilos")
        this.fc_revision_engorde.text=general.getString("fecha_revision")
        this.alimento_engorde.text=general.getString("alimento")
        this.catego_engorde.text=general.getString("categoria")

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}