package com.passwd.ui.splash

import android.os.Bundle
import com.passwd.R
import com.passwd.databinding.ActivitySplashBinding
import com.passwd.ui.base.BaseActivity
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val layoutId: Int = R.layout.activity_splash
    override fun createViewModel(): SplashViewModel = currentScope.getViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        observeStates()
    }

    private fun observeStates() {
    }
}
