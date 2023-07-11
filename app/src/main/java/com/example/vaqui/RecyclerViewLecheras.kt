package com.example.vaqui

import android.annotation.SuppressLint
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
import com.example.vaqui.adapter.LecherasAdapter
import com.example.vaqui.adapter.LecherasListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RecyclerViewLecheras : Fragment(), LecherasListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlLecherasList: RelativeLayout
    private lateinit var imagen_atras_lista_lecheras : ImageView
    private var lecherasList= ArrayList<JSONObject>()

    //metodo para filtar en el searchView
    private fun filterLecherasList(query: String) {
        val filteredList = ArrayList<JSONObject>()

        for (lechera in lecherasList) {
            val id = lechera.optString("id")
            if (id.contains(query, ignoreCase = true)) {
                filteredList.add(lechera)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No se encontró el ID", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = LecherasAdapter(filteredList, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter= LecherasAdapter(lecherasList,this)

        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter=adapter
        Log.d("RecyclerViewLecheras","Entered to OnViewCreate")

        val searchView = view.findViewById<SearchView>(R.id.searchBusBoviLecheras)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterLecherasList(newText)
                return true
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Infla el diseño para este fragmento
        val ll = inflater.inflate(R.layout.fragment_recycler_view_lecheras, container, false)
        this.recycler = ll.findViewById(R.id.rvLecheras)
        this.imagen_atras_lista_lecheras = ll.findViewById(R.id.imagen_atras_lista_lecheras)

        imagen_atras_lista_lecheras.setOnClickListener {
            findNavController().navigate(R.id.action_lecherasfragment_to_categorias)
        }

        val url = "http://192.168.180.187:8080/listarLecheras"
        Log.d("RecyclerViewLecheras","Entered to onCreateView")
        val queue = Volley.newRequestQueue(this.context)
        //queue.timeout = 10000 // aumentar el tiempo de espera a 10 segundos

        val stringRequest = StringRequest(Request.Method.GET, url,{ response ->
            val jsonArray = JSONArray(response)

            this.lecherasList= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    lecherasList.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("listlecheras", this.lecherasList.toString())

                if (lecherasList != null) {

                    //lo que el usuario va digitando en el searchView
                    filterLecherasList("")

                    recycler.adapter = LecherasAdapter(lecherasList, this)
                    viewAlpha.visibility = View.INVISIBLE
                    pgbar.visibility = View.INVISIBLE
                }

            }catch (e: JSONException){
            }
        }, { error ->
            Log.w("jsonError", error)
        })

        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rvLecheras)
        this.viewAlpha = ll.findViewById(R.id.view_lecherasList)
        this.pgbar = ll.findViewById(R.id.pgbar_lecherasList)
        this.rlLecherasList = ll.findViewById(R.id.RlLecheras)

        return ll

    }

    override fun onItemClicked(lecheras: JSONObject, position: Int) {
        val bundle = bundleOf("lecheras" to lecheras.toString())
        findNavController().navigate(
            R.id.datos_lecheras,
            bundle
        )
    }
}