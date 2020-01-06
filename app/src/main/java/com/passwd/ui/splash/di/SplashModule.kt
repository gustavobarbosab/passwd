package com.passwd.ui.splash.di

import com.passwd.ui.splash.SplashActivity
import com.passwd.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object SplashModule {
    val module = module {
        scope(named<SplashActivity>()) {
            viewModel { SplashViewModel() }
        }
    }
}