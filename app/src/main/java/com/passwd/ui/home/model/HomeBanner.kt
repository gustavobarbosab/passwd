package com.passwd.ui.home.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class HomeBanner (
        private var _username: String = "",
        private var _passwordCount: Int = 0
): BaseObservable() {

    var username: String
        @Bindable
        get() = _username
        set(value) {
            _username = value
            notifyPropertyChanged(BR.username)
        }

    var passwordCount: Int
        @Bindable
        get() = _passwordCount
        set(value) {
            _passwordCount = value
            notifyPropertyChanged(BR.passwordCount)
        }
}