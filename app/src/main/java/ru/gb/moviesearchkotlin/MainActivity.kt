package ru.gb.moviesearchkotlin

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.moviesearchkotlin.databinding.ActivityMainBinding
import ru.gb.moviesearchkotlin.servicesbroadcasts.ConnectivityBroadcast
import ru.gb.moviesearchkotlin.view.searchfragment.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val receiver = ConnectivityBroadcast()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commitNow()
        }

    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

}