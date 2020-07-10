package com.atlas.igtracker.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.atlas.igtracker.R
import com.atlas.igtracker.base.view.StateSubscriber
import com.atlas.igtracker.splash.intent.SplashIntentFactory
import com.atlas.igtracker.splash.model.SplashModelStore
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.android.ext.android.inject

class SplashActivity: AppCompatActivity(), StateSubscriber {

    private val callbackManager: CallbackManager by inject()
    private val splashIntentFactory: SplashIntentFactory by inject()
    private val splashModelStore: SplashModelStore by inject()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        splashModelStore.process(splashIntentFactory.buildAccessTokenCheckIntent())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun Observable<Any>.subscribeToViewStates(): Disposable {
        TODO("Not yet implemented")
    }

    override fun Observable<Any>.subscribeToPassiveDataStates(): Disposable {
        TODO("Not yet implemented")
    }
}