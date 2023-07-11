package com.example.vaqui.Buscador

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.example.vaqui.R
import com.example.vaqui.adapter.BovinosAdapter
import com.example.vaqui.adapter.BovinosListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class BuscadorBovinoFragment : Fragment(), BovinosListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlBovinosList: RelativeLayout
    private lateinit var imagen_atras_lista_general : ImageView

    private var bovinosList= ArrayList<JSONObject>()

    //metodo para filtar en el searchView
    private fun filterBovinosList(query: String) {
        val filteredList = ArrayList<JSONObject>()

        for (bovino in bovinosList) {
            val id = bovino.optString("id")
            if (id.contains(query, ignoreCase = true)) {
                filteredList.add(bovino)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No se encontró el ID ", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = BovinosAdapter(filteredList, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=BovinosAdapter(bovinosList,this)

        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter=adapter
        Log.d("BuscadorBovinoFragment","Entered to OnViewCreate")

        val searchView = view.findViewById<SearchView>(R.id.searchBusBovi)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterBovinosList(newText)
                return true
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Infla el diseño para este fragmento
        val ll = inflater.inflate(R.layout.fragment_buscador_bovino, container, false)
        this.recycler = ll.findViewById(R.id.rvBuscador1)
        this.imagen_atras_lista_general = ll.findViewById(R.id.imagen_atras_lista_general)

        imagen_atras_lista_general.setOnClickListener {
            findNavController().navigate(R.id.action_generalfragment_to_categorias)
        }

        val url = "http://192.168.123.228:8080/listarGeneral"
        Log.d("BuscadorBovinoFragment","Entered to onCreateView")
        val queue = Volley.newRequestQueue(this.context)
        //queue.timeout = 10000 // aumentar el tiempo de espera a 10 segundos

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
                Log.d("listbovinos", this.bovinosList.toString())

                if (bovinosList != null) {
                    //lo que el usuario va digitando en el searchView
                    filterBovinosList("")

                    recycler.adapter = BovinosAdapter(bovinosList, this)
                    viewAlpha.visibility = View.INVISIBLE
                    pgbar.visibility = View.INVISIBLE
                }

            }catch (e: JSONException){
            }
        }, { error ->
            Log.w("jsonError", error)
        })

        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rvBuscador1)
        this.viewAlpha = ll.findViewById(R.id.view_bovinosList)
        this.pgbar = ll.findViewById(R.id.pgbar_bovinoList)
        this.rlBovinosList = ll.findViewById(R.id.RlBuscador)

        return ll

    }

    override fun onItemClicked(bovinos: JSONObject, position: Int) {

        val bundle = bundleOf("bovinos" to bovinos.toString())
        findNavController().navigate(
            R.id.datos_general,
            bundle
        )
    }
}