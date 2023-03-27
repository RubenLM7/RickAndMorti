package com.example.rickandmorti

import android.app.Application
import com.example.rickandmorti.di.initDI

class App: Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        instance = this
    }
}