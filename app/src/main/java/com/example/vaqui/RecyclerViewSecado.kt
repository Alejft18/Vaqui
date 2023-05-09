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
import com.example.vaqui.Moldels.Secado
import com.example.vaqui.adapter.BovinosAdapter
import com.example.vaqui.adapter.BovinosListener
import com.example.vaqui.adapter.SecadoAdapter
import com.example.vaqui.adapter.SecadoListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class RecyclerViewSecado : Fragment(), SecadoListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlSecadoList: RelativeLayout
    private var secadoList= ArrayList<JSONObject>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter=SecadoAdapter(secadoList,this)

        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter=adapter
        Log.d("RecyclerViewSecado","Entered to OnViewCreate")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_recycler_view_secado, container, false)
        this.recycler = ll.findViewById(R.id.rvBuscadorSecado)
        val url = "http://192.168.16.78/phpVaqui/listar_secado.php"
        Log.d("RecyclerViewSecado","Entered to onCreateView")
        val queue = Volley.newRequestQueue(this.context)
        //queue.timeout = 10000 // aumentar el tiempo de espera a 10 segundos

        val stringRequest = StringRequest(Request.Method.GET, url,{ response ->
            val jsonArray = JSONArray(response)

            this.secadoList= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    secadoList.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("listsecado", this.secadoList.toString())

                if (secadoList != null) {
                    recycler.adapter = SecadoAdapter(secadoList, this)
                    viewAlpha.visibility = View.INVISIBLE
                    pgbar.visibility = View.INVISIBLE
                }

            }catch (e: JSONException){
            }
        }, { error ->
            Log.w("jsonError", error)
        })

        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rvBuscadorSecado)
        this.viewAlpha = ll.findViewById(R.id.view_secadoList)
        this.pgbar = ll.findViewById(R.id.pgbar_secadolist)
        this.rlSecadoList = ll.findViewById(R.id.RlBuscadorSecado)

        return ll

    }

    override fun onItemClicked(secado: JSONObject, position: Int) {

        val bundle = bundleOf("Secado" to secado.toString())
        /*findNavController().navigate(
            R.id.datos_secado,
            bundle
        )*/
    }
}