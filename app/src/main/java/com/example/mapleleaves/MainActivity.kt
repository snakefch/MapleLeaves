package com.example.mapleleaves

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mapleleaves.databinding.ActivityMainBinding
import com.example.mapleleaves.utils.ActivityCollector
import com.example.mapleleaves.utils.MyObserver
import com.example.mapleleaves.utils.Notifier


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG=this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCollector.addActivity(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        //val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_placeFragment, R.id.navigation_notifications,R.id.navigation_me
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //注释掉ActionBar
        navView.setupWithNavController(navController)

        lifecycle.addObserver(MyObserver(TAG))
        Notifier.init(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    companion object {
        fun startMainActivity(context: Context){
            val intent= Intent(context,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}