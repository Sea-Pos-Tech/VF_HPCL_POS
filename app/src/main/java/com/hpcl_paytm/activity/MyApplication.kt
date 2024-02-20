package com.hpcl_paytm.activity

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

    }

    companion object{
        var appContext: Context? = null
    }
}