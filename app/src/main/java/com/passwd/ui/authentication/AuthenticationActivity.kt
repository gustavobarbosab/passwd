package com.passwd.ui.authentication

/*
 TODO será adequado posteriormente
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
*/
