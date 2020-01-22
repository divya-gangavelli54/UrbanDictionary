package com.urbandictionary

import android.app.Application

class AppGlobal : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @get:Synchronized
        var instance: AppGlobal? = null
            private set
    }
}