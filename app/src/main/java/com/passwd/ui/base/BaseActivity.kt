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

abstract class BaseActivity<B : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: V
    protected lateinit var binding: B

    protected abstract var requireAuthentication: Boolean
    abstract val layoutId: Int

    private val authHandler = Handler()
    private var isAuthenticated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onResume() {
        super.onResume()
        startAuth()
    }

    private fun startAuth() {
        if (isAuthenticated or !requireAuthentication) return
        startActivityForResult(AuthenticationActivity.newIntent(this), AUTH_ACTIVITY)
    }

    private fun updateAuthSuccess() {
        isAuthenticated = true
        authHandler.removeCallbacks {}
        authHandler.postDelayed({ isAuthenticated = false }, AUTH_DELAY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTH_ACTIVITY && resultCode == Activity.RESULT_OK) updateAuthSuccess()
    }

    companion object {
        const val AUTH_ACTIVITY = 123
        const val AUTH_DELAY = 8000L
    }
}