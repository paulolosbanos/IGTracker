package com.atlas.igtracker

import android.app.Application
import com.atlas.igtracker.di.providerModule
import com.atlas.igtracker.di.stateModule
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IGTrackerApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@IGTrackerApplication)
            modules(providerModule, stateModule)
        }
    }
}