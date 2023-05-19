package com.example.vaqui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vaqui.Perfil.PerfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    val genero = arrayOf("Macho","Hembra")
    val procedencia = arrayOf("De este finca","De otra finca")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.genero)
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,genero)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext,"select genero is = " + genero[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        val spinnerP = findViewById<Spinner>(R.id.procedencia)
        val arrayAdapterP = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,procedencia)
        spinnerP.adapter = arrayAdapterP
        spinnerP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext, "select procedencia is = " + procedencia[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment? ?: return
        val navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupBottonNavMenu(navController)

        val btnNavegar = findViewById<ImageButton>(R.id.btn_perfil)
        btnNavegar.setOnClickListener{
            val destino = PerfilFragment()
            val navController = Navigation.findNavController(this,R.id.nav_host_fragment_container)
            navController.navigate(R.id.perfilFragment)
        }
    }

    private fun setupBottonNavMenu(navController: NavController){
        val bottoNav = findViewById<BottomNavigationView>(R.id.btnMenu)
        bottoNav?.setupWithNavController(navController)
    }
}
