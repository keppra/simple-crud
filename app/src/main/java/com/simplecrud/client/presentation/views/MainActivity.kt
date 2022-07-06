package com.simplecrud.client.presentation.views

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simplecrud.base.presentation.BaseActivity
import com.simplecrud.client.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        /*val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.summaryFragment,
            R.id.listUsersFragment,
            R.id.addUserFragment,
            R.id.removeUserFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)*/

        bottomNavigationView.setupWithNavController(navController)
    }
}