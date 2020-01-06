package com.passwd.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.passwd.ui.splash.model.SplashState

class SplashViewModel : ViewModel() {
    private val _viewState: MutableLiveData<SplashState> = MutableLiveData()
    val viewState: LiveData<SplashState>
        get() = _viewState

    init {
        // TODO fazer validações para redirecionamento
        _viewState.value = SplashState.GoToHome
    }
}
