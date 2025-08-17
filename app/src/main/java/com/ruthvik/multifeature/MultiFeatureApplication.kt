package com.ruthvik.multifeature

import android.app.Application
import com.ruthvik.multifeature.container.AppContainer
import com.ruthvik.multifeature.container.AppDataContainer

class MultiFeatureApplication: Application() {

    lateinit var appDataContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appDataContainer = AppDataContainer(context = this)
    }
}