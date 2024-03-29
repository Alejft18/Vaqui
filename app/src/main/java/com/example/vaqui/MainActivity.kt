package com.example.vaqui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vaqui.Perfil.PerfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment? ?: return
        val navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupBottonNavMenu(navController)



        //Boton del perfil
        val btn_perfil = findViewById<ImageView>(R.id.btn_perfil)
        btn_perfil.setOnClickListener {
            val navController = Navigation.findNavController(this,R.id.nav_host_fragment_container)
            navController.navigate(R.id.perfilFragment)
        }


    }

    private fun setupBottonNavMenu(navController: NavController){
        val bottoNav = findViewById<BottomNavigationView>(R.id.btnMenu)
        bottoNav?.setupWithNavController(navController)
    }
}
