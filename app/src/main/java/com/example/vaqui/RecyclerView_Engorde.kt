package com.example.vaqui

import android.annotation.SuppressLint
import android.media.Image
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
import com.example.vaqui.adapter.EngordeAdapter
import com.example.vaqui.adapter.EngordeListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class RecyclerView_Engorde : Fragment(), EngordeListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlEngordeList: RelativeLayout
    private lateinit var imagen_atras_lista_engorde : ImageView
    private var engordeList= ArrayList<JSONObject>()

    private fun filterEngordeList(query: String) {
        val filteredList = ArrayList<JSONObject>()

        for (engorde in engordeList) {
            val id = engorde.optString("id")
            if (id.contains(query, ignoreCase = true)) {
                filteredList.add(engorde)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No se encontró el ID ", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = EngordeAdapter(filteredList, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=EngordeAdapter(engordeList,this)

        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter=adapter
        Log.d("RecyclerView_Engorde","Entered to OnViewCreate")

        val searchView = view.findViewById<SearchView>(R.id.searchBusBoviengorde)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterEngordeList(newText)
                return true
            }
        })
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Infla el diseño para este fragmento
        val ll = inflater.inflate(R.layout.fragment_recycler_view__engorde, container, false)
        this.recycler = ll.findViewById(R.id.rvEngorde)
        this.imagen_atras_lista_engorde = ll.findViewById(R.id.imagen_atras_lista_engorde)

        imagen_atras_lista_engorde.setOnClickListener {
            findNavController().navigate(R.id.action_engordefragment_to_categorias)
        }

        val url = "http://192.168.56.187:8080/listarEngorde"
        Log.d("RecyclerView_Engorde","Entered to onCreateView")
        val queue = Volley.newRequestQueue(this.context)
        //queue.timeout = 10000 // aumentar el tiempo de espera a 10 segundos

        val stringRequest = StringRequest(Request.Method.GET, url,{ response ->
            val jsonArray = JSONArray(response)

            this.engordeList= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    engordeList.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("listengorde", this.engordeList.toString())

                if (engordeList != null) {

                    //lo que el usuario va digitando en el searchView
                    filterEngordeList("")

                    recycler.adapter = EngordeAdapter(engordeList, this)
                    viewAlpha.visibility = View.INVISIBLE
                    pgbar.visibility = View.INVISIBLE
                }

            }catch (e: JSONException){
            }
        }, { error ->
            Log.w("jsonError", error)
        })

        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rvEngorde)
        this.viewAlpha = ll.findViewById(R.id.view_engordeList)
        this.pgbar = ll.findViewById(R.id.pgbar_engordeList)
        this.rlEngordeList = ll.findViewById(R.id.RlEngorde)

        return ll

    }

    override fun onItemClicked(engorde: JSONObject, position: Int) {

        val bundle = bundleOf("engorde" to engorde.toString())
        findNavController().navigate(R.id.datos_engorde, bundle)
    }


}