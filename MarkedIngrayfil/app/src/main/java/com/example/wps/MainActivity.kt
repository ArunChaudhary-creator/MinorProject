package com.example.wps

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Ensure this file exists in res/layout

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MapFragment()).commit()
            navView.setCheckedItem(R.id.nav_map)
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            val fragment: Fragment = when (menuItem.itemId) {
                R.id.nav_map -> MapFragment()
                R.id.nav_wifi -> WiFiFragment()
                else -> MapFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment).commit()
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }

    // Embedded MapFragment
    class MapFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_map, container, false)
            val textView = view.findViewById<TextView>(R.id.fragmentText)
            textView?.text = "Map Fragment"
            return view
        }
    }

    // Embedded WiFiFragment
    class WiFiFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_wifi, container, false)
            val textView = view.findViewById<TextView>(R.id.fragmentText)
            textView?.text = "WiFi Fragment"
            return view
        }
    }
}
