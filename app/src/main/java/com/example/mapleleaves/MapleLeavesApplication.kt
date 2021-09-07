package com.example.mapleleaves

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MapleLeavesApplication:Application() {

    companion object{

        const val TOKEN="nOBJYAceSQ8xZU3r"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }

}