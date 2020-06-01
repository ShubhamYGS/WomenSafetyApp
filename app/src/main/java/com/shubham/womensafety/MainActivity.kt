package com.shubham.womensafety

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.firebase.ui.auth.AuthUI
import com.shubham.womensafety.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)

        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout_user ->
                    AuthUI.getInstance().signOut(this)
//                R.id.guardianInfo->
//                    Navigation.createNavigateOnClickListener(R.id.action_dashBoardFragment_to_guardianInfo)
//                R.id.mapsActivity->
//                    findNavController(R.id.action_dashBoardFragment_to_mapsActivity)
            }
            true
        }
    }

    override fun onSupportNavigateUp():Boolean{
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}
