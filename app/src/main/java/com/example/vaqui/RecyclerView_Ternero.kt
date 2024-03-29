package com.example.vaqui

import android.annotation.SuppressLint
import com.example.vaqui.adapter.TernerosListener
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
import com.example.vaqui.adapter.TerneroAdapter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.log


class RecyclerView_Ternero : Fragment(), TernerosListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlTerneroList: RelativeLayout
    private lateinit var imagen_atras_lista_ternero : ImageView
    private var terneroList = ArrayList<JSONObject>()

    //metodo para filtar en el searchView
    private fun filterTernerosList(query: String) {
        val filteredList = ArrayList<JSONObject>()

        for (ternero in terneroList) {
            val id = ternero.optString("id")
            if (id.contains(query, ignoreCase = true)) {
                filteredList.add(ternero)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No se encontró el ID ", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = TerneroAdapter(filteredList, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val adapter = TerneroAdapter(terneroList,this)
        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter = adapter
        Log.d("RecyclerView_Ternero", "Entered to OnViewCreate")

        val searchView = view.findViewById<SearchView>(R.id.searchBusTernero)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterTernerosList(newText)
                return true
            }
        })
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_recycler_view__ternero, container, false)
        this.recycler = ll.findViewById(R.id.rvBuscadorTerne)
        this.imagen_atras_lista_ternero = ll.findViewById(R.id.imagen_atras_lista_ternero)

        imagen_atras_lista_ternero.setOnClickListener {
            findNavController().navigate(R.id.action_ternerofragment_to_categorias)
        }

        val url = "https://vaquijpa2-production.up.railway.app/listarTerneros"
        Log.d("RecyclerView_Ternero", "Entered to onCreateView")
        val queue = Volley.newRequestQueue(this.context)


        val stringRequest = StringRequest(Request.Method.GET, url,{ response ->
            val jsonArray = JSONArray(response)

            this.terneroList = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l){
                    terneroList.add(jsonArray[i] as JSONObject)
                i++
            }
            Log.d("listternero", this.terneroList.toString())

            if (terneroList != null){
                //
                filterTernerosList("")

                recycler.adapter = TerneroAdapter(terneroList, this)
                viewAlpha.visibility = View.INVISIBLE
                pgbar.visibility = View.INVISIBLE
            }
    }catch (e: JSONException){
    }
        }, { error ->
            Log.w("jsonError", error)
        })

        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rvBuscadorTerne)
        this.viewAlpha = ll.findViewById(R.id.view_ternerosList)
        this.pgbar = ll.findViewById(R.id.pgbar_terneroList)
        this.rlTerneroList = ll.findViewById(R.id.RlBuscadorTernero)

        return ll
    }

    override fun onItemClicked(terneros: JSONObject, position: Int) {

        val bundle = bundleOf("terneros" to terneros.toString())
        findNavController().navigate(
            R.id.datos_terneros,
            bundle
        )
    }
}