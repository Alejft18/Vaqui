package com.example.vaqui

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
    private var lecherasList= ArrayList<JSONObject>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter= LecherasAdapter(lecherasList,this)

        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter=adapter
        Log.d("RecyclerViewLecheras","Entered to OnViewCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Infla el diseño para este fragmento
        val ll = inflater.inflate(R.layout.fragment_recycler_view_lecheras, container, false)
        this.recycler = ll.findViewById(R.id.rvLecheras)
        val url = "http://192.168.226.82/phpVaqui/listar_lecheras.php"
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