package com.example.vaqui

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
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vaqui.adapter.BovinosAdapter
import com.example.vaqui.adapter.GestacionAdapter
import com.example.vaqui.adapter.GestacionListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RecyclerView_Gestacion : Fragment(), GestacionListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlGestacionList: RelativeLayout
    private var gestacionList= ArrayList<JSONObject>()
    private lateinit var imagen_atras_lista_gestacion : ImageView

    //metodo para filtar en el searchView
    private fun filterGestacionList(query: String) {
        val filteredList = ArrayList<JSONObject>()

        for (gestacion in gestacionList) {
            val id = gestacion.optString("id")
            if (id.contains(query, ignoreCase = true)) {
                filteredList.add(gestacion)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No se encontró el ID ", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = GestacionAdapter(filteredList, this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=GestacionAdapter(gestacionList,this)

        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter=adapter
        Log.d("RecyclerView_Gestacion","Entered to OnViewCreate")

        val searchView = view.findViewById<SearchView>(R.id.searchBusBovigestacion)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterGestacionList(newText)
                return true
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Infla el diseño para este fragmento
        val ll = inflater.inflate(R.layout.fragment_recycler_view__gestacion, container, false)
        this.recycler = ll.findViewById(R.id.rvGestacion)
        this.imagen_atras_lista_gestacion = ll.findViewById(R.id.imagen_atras_lista_gestacion)

        imagen_atras_lista_gestacion.setOnClickListener {
            findNavController().navigate(R.id.action_gestacionfragment_to_categorias)
        }

        val url = "http://192.168.208.187:8080/listarGestacion"
        Log.d("RecyclerView_Gestacion","Entered to onCreateView")
        val queue = Volley.newRequestQueue(this.context)
        //queue.timeout = 10000 // aumentar el tiempo de espera a 10 segundos

        val stringRequest = StringRequest(Request.Method.GET, url,{ response ->
            val jsonArray = JSONArray(response)

            this.gestacionList= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    gestacionList.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("listgestacion", this.gestacionList.toString())

                if (gestacionList != null) {
                    //lo que el usuario va digitando en el searchView
                    filterGestacionList("")

                    recycler.adapter = GestacionAdapter(gestacionList, this)
                    viewAlpha.visibility = View.INVISIBLE
                    pgbar.visibility = View.INVISIBLE
                }

            }catch (e: JSONException){
            }
        }, { error ->
            Log.w("jsonError", error)
        })

        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rvGestacion)
        this.viewAlpha = ll.findViewById(R.id.view_gestacionList)
        this.pgbar = ll.findViewById(R.id.pgbar_gestacionList)
        this.rlGestacionList = ll.findViewById(R.id.RlGestacion)

        return ll

    }

    override fun onItemClicked(gestacion: JSONObject, position: Int) {
        val bundle = bundleOf("gestacion" to gestacion.toString())
        findNavController().navigate(
            R.id.datos_gestacion,
            bundle
        )
    }
}