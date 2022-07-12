package ru.gb.moviesearchkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.moviesearchkotlin.databinding.ActivityMainBinding
import ru.gb.moviesearchkotlin.view.searchfragment.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commitNow()
        }

    }

}