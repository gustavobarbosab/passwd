package com.passwd.ui.authentication

import android.os.Bundle
import com.passwd.R
import com.passwd.common.extension.showShortToast
import com.passwd.databinding.ActivityAuthenticationBinding
import com.passwd.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_authentication.passwordTextField
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding, AuthenticationViewModel>() {

    override val layoutId: Int = R.layout.activity_authentication
    override fun createViewModel(): AuthenticationViewModel = currentScope.getViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passwordTextField.onPasswordComplete = this::onPasswordComplete
    }

    private fun onPasswordComplete(password: String) {
        showShortToast(password)
    }
}
