package com.example.jzheng4.fragmentkotlin

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: android.support.v4.app.Fragment? = null
        when (item.itemId) {
            R.id.nav_home -> selectedFragment = HomeFragment()
            R.id.nav_favorites -> selectedFragment = FavoriteFragment()
            R.id.nav_search -> selectedFragment = SearchFragment()
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()

        true
    }
}
