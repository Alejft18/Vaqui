package com.example.vaqui.Perfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vaqui.R
import org.json.JSONObject

class PerfilFragment : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlBovinosList: RelativeLayout
    private var bovinosList= ArrayList<JSONObject>()

    private lateinit var imagen_atras : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_perfil, container, false)
        this.imagen_atras = ll.findViewById(R.id.imagen_atras_perfil)

        imagen_atras.setOnClickListener {
            findNavController().navigate(R.id.action_perfilFragment_to_inicio)

        }

        return ll
    }


}