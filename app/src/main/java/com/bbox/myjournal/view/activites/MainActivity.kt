package com.bbox.myjournal.view.activites

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.bbox.myjournal.R
import com.bbox.myjournal.databinding.ActivityMainBinding
import com.bbox.myjournal.base.BasePermissionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BasePermissionActivity() {

    companion object {
        const val TAG = "MainActivity"
        var currentFragmentId: Int = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate() {
        super.onPostCreate()

        // Bind layout
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        graph.setStartDestination(R.id.splash_fragment)

        navHostFragment.navController.graph = graph

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            currentFragmentId = destination.id
        }

    }

}