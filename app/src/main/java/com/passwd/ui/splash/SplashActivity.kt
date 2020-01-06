package com.passwd.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.passwd.R
import com.passwd.databinding.ActivitySplashBinding
import com.passwd.ui.base.BaseActivity
import com.passwd.ui.home.HomeActivity
import com.passwd.ui.splash.model.SplashState
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val layoutId: Int = R.layout.activity_splash
    override fun createViewModel(): SplashViewModel = currentScope.getViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeStates()
    }

    private fun observeStates() {
        viewModel
            .viewState
            .observe(this, Observer {
                when (it) {
                    SplashState.GoToHome -> redirectToHome()
                    SplashState.GoToRegister -> redirectToRegister()
                }
            })
    }

    private fun redirectToHome() {
        Handler().postDelayed({
            startActivity(HomeActivity.newIntent(this))
            finish()
        }, DELAY_TO_REDIRECT)
    }

    private fun redirectToRegister() {
    }

    companion object {
        const val DELAY_TO_REDIRECT = 2500L
    }
}

