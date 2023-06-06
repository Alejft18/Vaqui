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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val adapter = ToroAdapter(toroList,this)
        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter = adapter
        Log.d("RecyclerView_Toro", "Entered to OnViewCreate")
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_recycler_view__toro, container, false)
        this.recycler = ll.findViewById(R.id.rvBuscadorToro)
        val url = "http://192.168.226.77:8080/listarToros"
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