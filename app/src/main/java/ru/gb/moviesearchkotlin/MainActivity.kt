package ru.gb.moviesearchkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.moviesearchkotlin.view.searchfragment.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commitNow()
        }
    }
}