package com.passwd.ui.authentication

import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.passwd.R
import com.passwd.databinding.ActivityAuthenticationBinding
import com.passwd.ui.base.BaseActivity
import com.passwd.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_authentication.authContainer
import kotlinx.android.synthetic.main.activity_authentication.passwordTextField
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding, AuthenticationViewModel>() {

    override val layoutId: Int = R.layout.activity_authentication
    override fun createViewModel(): AuthenticationViewModel = currentScope.getViewModel(this)

    lateinit var snackbarError: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        snackbarError = Snackbar.make(authContainer, R.string.authentication_message_error, Snackbar.LENGTH_LONG)
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

    private fun onAuthenticationSuccess() {
        // setResult(Activity.RESULT_OK)
        // TODO mudar isso depois
        startActivity(HomeActivity.newIntent(this))
    }

    private fun onAuthenticationFailure() {
        snackbarError.show()
    }
}
