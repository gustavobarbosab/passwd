package com.passwd.ui.authentication.model

sealed class AuthenticationState {
    object Success : AuthenticationState()
    object Failure : AuthenticationState()
}