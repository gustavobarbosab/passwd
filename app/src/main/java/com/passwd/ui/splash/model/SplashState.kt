package com.passwd.ui.splash.model

sealed class SplashState {
    object GoToHome : SplashState()
    object GoToRegister : SplashState()
}