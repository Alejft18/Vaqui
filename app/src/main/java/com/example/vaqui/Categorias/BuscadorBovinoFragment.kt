package com.example.vaqui.Buscador

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.example.vaqui.R
import com.example.vaqui.adapter.BovinosAdapter
import com.example.vaqui.adapter.BovinosListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BuscadorBovinoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BuscadorBovinoFragment : Fragment(), BovinosListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha:View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlBovinosList: RelativeLayout
    private lateinit var bavinosList: ArrayList<JSONObject>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("BuscadorBovinoFragment","Entered to onCreateView")
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_buscador_bovino, container, false)
        val url = "http://192.168.89.79/phpVqui/listar_bobinos_general.php"
        val queue = Volley.newRequestQueue(this.context)


        val stringRequest = StringRequest(Request.Method.GET, url,{ response ->
            val jsonArray = JSONArray(response)
            this.bavinosList = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    bavinosList.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("BuscadorBovinoFragment", this.bavinosList.toString())
                this.recycler.adapter = BovinosAdapter(this.bavinosList, this)
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE

            }catch (e: JSONException){
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.rvBuscador)
        this.viewAlpha = ll.findViewById(R.id.view_bovinosList)
        this.pgbar = ll.findViewById(R.id.pgbar_bovinoList)
        this.rlBovinosList = ll.findViewById(R.id.RlBuscador)

        return ll
    }


    override fun onItemClicked(bovinos: JSONObject, position: Int) {
        val bundle = bundleOf("Bovino" to bovinos.toString())
        findNavController().navigate(
            R.id.datos_general,
            bundle
        )
    }
}