package com.atlas.igtracker.splash.state

import com.atlas.igtracker.base.intent.Intent

sealed class SplashState {
    object Active: SplashState()
    object Closed: SplashState()
    object AccessTokenExpired: SplashState()
    object CheckAccessToken: SplashState()
}