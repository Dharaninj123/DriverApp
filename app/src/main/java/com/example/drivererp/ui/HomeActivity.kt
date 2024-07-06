package com.example.drivererp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.drivererp.R
import com.example.drivererp.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarHomeErp.toolbar)

        val drawer: DrawerLayout = binding.drawerLayout
        val navigationView: NavigationView = binding.navView

        // Initialize navigation components
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home,
            R.id.nav_driverId,
            R.id.nav_aadhaar,
            R.id.nav_license,
            R.id.nav_insuranceCopy,
            R.id.nav_Documents,
            R.id.nav_about,
            R.id.nav_logout
        )
            .setOpenableLayout(drawer)
            .build()

        val navController = findNavController(R.id.nav_host_fragment_content_home_erp)
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        setupWithNavController(navigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_erp)
        return navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}
