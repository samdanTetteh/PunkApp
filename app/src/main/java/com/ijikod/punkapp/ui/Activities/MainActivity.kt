package com.ijikod.punkapp.ui.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.ijikod.punkapp.R
import com.ijikod.punkapp.ui.Fragments.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(navHostFragment.navController.graph)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = FragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        // setup toolbar with navigation component
        toolbar.setupWithNavController(navHostFragment.navController, appBarConfiguration)
    }

}




