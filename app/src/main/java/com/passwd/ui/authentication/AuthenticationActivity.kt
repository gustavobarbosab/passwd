package com.passwd.ui.authentication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.passwd.R
import com.passwd.databinding.ActivityAuthenticationBinding
import com.passwd.ui.authentication.model.AuthenticationState
import com.passwd.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_authentication.authContainer
import kotlinx.android.synthetic.main.activity_authentication.passwordTextField
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding, AuthenticationViewModel>() {

    override val layoutId: Int = R.layout.activity_authentication
    override var requireAuthentication: Boolean = false

    private lateinit var snackBarError: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = currentScope.getViewModel(this)
        snackBarError = Snackbar.make(authContainer, R.string.authentication_message_error, Snackbar.LENGTH_LONG)
        passwordTextField.onPasswordComplete = viewModel::onPasswordComplete
        observeStates()
    }

    private fun observeStates() {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                AuthenticationState.Success -> onAuthenticationSuccess()
                AuthenticationState.Failure -> onAuthenticationFailure()
            }
        })
    }

    override fun onBackPressed() {}

    private fun onAuthenticationSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun onAuthenticationFailure() {
        snackBarError.show()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, AuthenticationActivity::class.java)
    }
}
