package com.passwd.ui.authentication

sealed class AuthenticationState {
    object Success : AuthenticationState()
    object Failure : AuthenticationState()
}