package com.top1shvetsvadim1.testebs.data.checkInternetStatus

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.widget.Toast

class CheckInternetStatus {

    fun checkInternet(context : Context){
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network : Network) {
                Toast.makeText(
                    context,
                    "Проверьте интернет подключение",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}