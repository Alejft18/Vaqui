package com.example.vaqui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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
    }


    private fun setupBottonNavMenu(navController: NavController){
        val bottoNav = findViewById<BottomNavigationView>(R.id.btnMenu)
        bottoNav?.setupWithNavController(navController)
    }
}