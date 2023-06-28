package com.example.vaqui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vaqui.adapter.ToroAdapter
import com.example.vaqui.adapter.ToroListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class RecyclerView_Toro : Fragment(), ToroListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlToroList: RelativeLayout
    private var toroList = ArrayList<JSONObject>()

    //metodo para filtar en el searchView
    private fun filterToroList(query: String) {
        val filteredList = ArrayList<JSONObject>()

        for (toro in toroList) {
            val id = toro.optString("id")
            if (id.contains(query, ignoreCase = true)) {
                filteredList.add(toro)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No se encontró el ID ", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = ToroAdapter(filteredList, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val adapter = ToroAdapter(toroList,this)
        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter = adapter
        Log.d("RecyclerView_Toro", "Entered to OnViewCreate")

        val searchView = view.findViewById<SearchView>(R.id.searchBusToro)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterToroList(newText)
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
        val ll = inflater.inflate(R.layout.fragment_recycler_view__toro, container, false)
        this.recycler = ll.findViewById(R.id.rvBuscadorToro)
        val url = "http://192.168.252.187:8080/listarToros"
        Log.d("RecyclerView_Toro", "Entered to onCreateView")
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url,{ response ->
            val jsonArray = JSONArray(response)

            this.toroList = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l){
                    toroList.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("listtoro", this.toroList.toString())

                if (toroList != null){
                    //lo que el usuario va digitando en el searchView
                    filterToroList("")

                    recycler.adapter = ToroAdapter(toroList, this)
                    viewAlpha.visibility = View.INVISIBLE
                    pgbar.visibility = View.INVISIBLE
                }
            }catch (e: JSONException){
            }
        }, { error ->
            Log.w("jsonError", error)
        })

        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rvBuscadorToro)
        this.viewAlpha = ll.findViewById(R.id.view_toroList)
        this.pgbar = ll.findViewById(R.id.pgbar_toroList)
        this.rlToroList = ll.findViewById(R.id.RlBuscadorToro)

        return ll
    }

    override fun onItemClicked(toros: JSONObject, position: Int) {
        val bundle = bundleOf("toros" to toros.toString())
        findNavController().navigate(
            R.id.datos_sementales,
            bundle
        )
    }
}