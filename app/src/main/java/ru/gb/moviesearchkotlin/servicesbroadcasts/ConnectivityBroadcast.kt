package ru.gb.moviesearchkotlin.servicesbroadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ConnectivityBroadcast : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i("***", "Broadcast Receiver: Working fine")
        Toast.makeText(p0, "Broadcast Receiver: AIRPLANE Mode", Toast.LENGTH_LONG).show()
    }
}