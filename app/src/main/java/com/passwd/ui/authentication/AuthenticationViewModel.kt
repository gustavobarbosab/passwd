package com.passwd.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.passwd.ui.authentication.model.AuthenticationState

class AuthenticationViewModel : ViewModel() {

    private val _viewState: MutableLiveData<AuthenticationState> = MutableLiveData()
    val viewState: LiveData<AuthenticationState>
        get() = _viewState

    fun onPasswordComplete(password: String) {
        if (password == "1384")
            _viewState.value = AuthenticationState.Success
        else
            _viewState.value = AuthenticationState.Failure
    }
}