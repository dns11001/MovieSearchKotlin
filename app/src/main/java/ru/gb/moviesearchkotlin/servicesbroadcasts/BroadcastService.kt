package ru.gb.moviesearchkotlin.servicesbroadcasts

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import ru.gb.moviesearchkotlin.R
import java.net.URL
import javax.net.ssl.HttpsURLConnection

open class BroadcastService : IntentService("") {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onHandleIntent(p0: Intent?) {
        isOnline(this)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                Toast.makeText(this, getString(R.string.internet_good), Toast.LENGTH_LONG).show()
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                Toast.makeText(this, getString(R.string.internet_good), Toast.LENGTH_LONG).show()
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                Toast.makeText(this, getString(R.string.internet_good), Toast.LENGTH_LONG).show()
                return true
            }
        }
        Toast.makeText(this, getString(R.string.internet_bad), Toast.LENGTH_LONG).show()
        return false
    }

}