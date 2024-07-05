package com.example.drivererp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.drivererp.R
import com.example.drivererp.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private var mAppBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.appBarHomeErp?.toolbar)

        val drawer: DrawerLayout = binding!!.drawerLayout
        val navigationView: NavigationView = binding!!.navView

        // Initialize navigation components
        mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home,
            R.id.nav_about,
            R.id.nav_logout
        )
            .setOpenableLayout(drawer)
            .build()

        val navController = findNavController(this, R.id.nav_host_fragment_content_home_erp)
        setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        setupWithNavController(navigationView, navController)


        @Override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(this, R.id.nav_host_fragment_content_home_erp)
            return (navigateUp(navController, mAppBarConfiguration!!)
                    || super.onSupportNavigateUp())
        }
    }
}
