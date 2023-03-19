package com.example.noteapplication

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class App: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
//        Hawk.init(this).build()
        MoteDataBase.getDataBase(this)
//        AppDatabese.initDatabase(this)

    }

}