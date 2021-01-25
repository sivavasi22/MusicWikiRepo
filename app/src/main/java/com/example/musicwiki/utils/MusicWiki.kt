package com.example.musicwiki.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build




class MusicWiki : Application(), Connectivity.ConnectivityReceiverListener {
    var isConnected: Boolean? = false
    var _context: Context? = null

    companion object {


        var context: Context? = null
            private set

        var INSTANCE: MusicWiki? = null
        @Synchronized
        fun getInstance(): MusicWiki {
            return INSTANCE!!
        }

    }

    init {
        INSTANCE = this
        this.setConnectivityListener(this)
    }

    private var mNetworkReceiver: BroadcastReceiver? = null

    override fun onCreate() {
        super.onCreate()
        _context = applicationContext
        PrefsHelper().instance

        mNetworkReceiver = Connectivity()

        registerNetworkBroadcastForNougat()
        onDestroy()
    }

    private fun registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    private fun unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun setConnectivityListener(listener: Connectivity.ConnectivityReceiverListener) {
        Connectivity.connectivityReceiverListener = listener
    }

    @SuppressLint("NewApi")
    private fun onDestroy() {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val taskList = activityManager.getRunningTasks(10)
        if (taskList.isNotEmpty()) {
            val runningTaskInfo = taskList[0]
            if (runningTaskInfo.topActivity != null && !runningTaskInfo.topActivity!!.className.contains(
                    "com.example.musicwiki")) {
                unregisterNetworkChanges()
            }
        }

    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        this.isConnected = isConnected
    }
}
