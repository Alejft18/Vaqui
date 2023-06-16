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
import com.example.vaqui.adapter.EngordeAdapter
import com.example.vaqui.adapter.UsuariosAdapter
import com.example.vaqui.adapter.UsuariosListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class RecyclerView_EmpleadosFragment : Fragment(), UsuariosListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlEmpleList: RelativeLayout
    private var empleList= ArrayList<JSONObject>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter= UsuariosAdapter(empleList,this)

        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager

        recycler.adapter=adapter
        Log.d("RecyclerView_Empleados","Entered to OnViewCreate")
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Infla el diseño para este fragmento
        val ll = inflater.inflate(R.layout.fragment_recycler_view__empleados, container, false)
        this.recycler = ll.findViewById(R.id.rvEmple)
        val url = "http://192.168.78.187:8080/listarEmpleados"
        Log.d("RecyclerView_Empleados","Entered to onCreateView")
        val queue = Volley.newRequestQueue(this.context)
        //queue.timeout = 10000 // aumentar el tiempo de espera a 10 segundos

        val stringRequest = StringRequest(Request.Method.GET, url,{ response ->
            val jsonArray = JSONArray(response)

            this.empleList= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    empleList.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("listEmpleado", this.empleList.toString())

                if (empleList != null) {
                    recycler.adapter = UsuariosAdapter(empleList, this)
                    viewAlpha.visibility = View.INVISIBLE
                    pgbar.visibility = View.INVISIBLE
                }

            }catch (e: JSONException){
            }
        }, { error ->
            Log.w("jsonError", error)
        })

        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rvEmple)
        this.viewAlpha = ll.findViewById(R.id.view_empleList)
        this.pgbar = ll.findViewById(R.id.pgbar_empleist)
        this.rlEmpleList = ll.findViewById(R.id.RlEmpleado)

        return ll

    }

    override fun onItemClicked(usuarios: JSONObject, position: Int) {
        val bundle = bundleOf("usuarios" to usuarios.toString())
        findNavController().navigate(
            R.id.detalleEmpleadoFragment,
            bundle
        )
    }
}