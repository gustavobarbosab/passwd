package com.passwd.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.passwd.ui.authentication.AuthenticationActivity
import com.passwd.ui.splash.SplashActivity

abstract class BaseActivity<B : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: V
    protected lateinit var binding: B

    abstract val layoutId: Int
    abstract fun createViewModel(): V

    private val authHandler = Handler()
    private var isAuthenticated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = createViewModel()
    }

    override fun onResume() {
        super.onResume()
        startAuth()
    }

    private fun startAuth() {
        if (isAuthenticated or isNotAuthenticateScreen()) return
        startActivityForResult(AuthenticationActivity.newIntent(this), AUTH_ACTIVITY)
    }

    private fun updateAuthSuccess() {
        isAuthenticated = true
        authHandler.removeCallbacks {}
        authHandler.postDelayed({ isAuthenticated = false }, AUTH_DELAY)
    }

    private fun isNotAuthenticateScreen() =
        (this is SplashActivity) or
            (this is AuthenticationActivity)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTH_ACTIVITY && resultCode == Activity.RESULT_OK) updateAuthSuccess()
    }

    companion object {
        const val AUTH_ACTIVITY = 123
        const val AUTH_DELAY = 8000L
    }
}