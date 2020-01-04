package com.passwd.ui.home

sealed class HomeStates {
    object FetchSuccess: HomeStates()
    object DeleteSuccess: HomeStates()
    object ShowLoading: HomeStates()
    object HideLoading: HomeStates()
}