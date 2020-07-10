package com.atlas.igtracker.splash.intent

import android.util.Log
import com.atlas.igtracker.base.intent.Intent
import com.atlas.igtracker.base.intent.intent
import com.atlas.igtracker.splash.model.SplashModelStore
import com.atlas.igtracker.splash.state.SplashState
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker

class SplashIntentFactory(
    private val splashModelStore: SplashModelStore
) {
    fun buildAccessTokenCheckIntent(): Intent<SplashState> {
        return intent {
            fun consume(isTokenValid: Boolean, token: AccessToken?) = chainedIntent {
                if (isTokenValid) {
                    AccessToken.setCurrentAccessToken(token)
                    SplashState.Closed
                } else if (!isTokenValid && token != null) {
                    AccessToken.refreshCurrentAccessTokenAsync()
                    SplashState.Closed
                } else {
                    SplashState.AccessTokenExpired
                }
            }
            object: AccessTokenTracker() {
                override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken) {
                    Log.e("token value", currentAccessToken.token)
                    consume(!currentAccessToken.isExpired, currentAccessToken)
                }
            }
            SplashState.CheckAccessToken
        }
    }

    private fun chainedIntent(block: SplashState.()-> SplashState) = splashModelStore.process(intent(block))
}