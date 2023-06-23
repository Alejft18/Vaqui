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
    private var engordeList= ArrayList<JSONObject>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=EngordeAdapter(engordeList,this)

        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter=adapter
        Log.d("RecyclerView_Engorde","Entered to OnViewCreate")
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Infla el diseño para este fragmento
        val ll = inflater.inflate(R.layout.fragment_recycler_view__engorde, container, false)
        this.recycler = ll.findViewById(R.id.rvEngorde)
        val url = "http://192.168.123.187:8080/listarEngorde"
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
        findNavController().navigate(
            R.id.datos_engorde,
            bundle
        )
    }


}