package com.passwd.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.passwd.ui.home.HomeActivity
import com.passwd.ui.splash.SplashActivity

abstract class BaseActivity<B : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: V
    protected lateinit var binding: B

    abstract val layoutId: Int
    abstract fun createViewModel(): V

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
        val isSplash = this is SplashActivity
        if (isSplash) return
        startActivityForResult(HomeActivity.newIntent(this), 1231231)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTH_ACTIVITY
            && resultCode == Activity.RESULT_OK) {
            // TODO
        }
    }

    companion object {
        const val AUTH_ACTIVITY = 1231231
    }
}