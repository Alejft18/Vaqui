package com.example.vaqui.Buscador

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.example.vaqui.R
import com.example.vaqui.adapter.BovinosAdapter
import com.example.vaqui.adapter.BovinosListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.log

class BuscadorBovinoFragment : Fragment(), BovinosListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlBovinosList: RelativeLayout
    private var bovinosList= ArrayList<JSONObject>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("BuscadorBovinoFragment","Entered to onCreateView")
        // Infla el diseño para este fragmento
        val ll = inflater.inflate(R.layout.fragment_buscador_bovino, container, false)

        this.recycler = ll.findViewById(R.id.rvBuscador)
        this.viewAlpha = ll.findViewById(R.id.view_bovinosList)
        this.pgbar = ll.findViewById(R.id.pgbar_bovinoList)
        this.rlBovinosList = ll.findViewById(R.id.RlBuscador)

        val url = "http://192.168.1.79/phpVqui/listar_bobinos_general.php"
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url,{ response ->
            val jsonArray = JSONArray(response)
            this.bovinosList= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    bovinosList.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("BuscadorBovinoFragment", this.bovinosList.toString())
                this.recycler.adapter = BovinosAdapter(this.bovinosList, this)
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE

            }catch (e: JSONException){
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=BovinosAdapter(bovinosList,this)
        recycler.adapter=adapter
    }


    override fun onItemClicked(bovinos: JSONObject, position: Int) {
        val bundle = bundleOf("Bovino" to bovinos.toString())
        findNavController().navigate(
            R.id.datos_general,
            bundle
        )
    }
}