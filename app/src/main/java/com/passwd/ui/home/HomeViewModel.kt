package com.passwd.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.passwd.common.Event
import com.passwd.ui.home.model.HomeBanner
import com.passwd.ui.home.model.HomeItemPassword
import com.passwd.ui.home.model.HomeMapper
import io.github.gustavobarbosab.domain.interactor.passwordList.PasswordListUseCase
import io.github.gustavobarbosab.domain.model.PasswordModel

class HomeViewModel(private val useCase: PasswordListUseCase,
                    private val mapper: HomeMapper) : ViewModel() {

    private val _passwordList: MutableLiveData<List<HomeItemPassword>> = MutableLiveData()
    val passwordList: LiveData<List<HomeItemPassword>>
        get() = _passwordList

    private val _showCreatePasswordDialog: MutableLiveData<Event<Unit>> = MutableLiveData()
    val showCreatePasswordDialog: LiveData<Event<Unit>>
        get() = _showCreatePasswordDialog

    private val _viewState: MutableLiveData<Event<HomeStates>> = MutableLiveData()
    val viewState: LiveData<Event<HomeStates>>
        get() = _viewState

    private val _error: MutableLiveData<Event<String>> = MutableLiveData()
    val error: LiveData<Event<String>>
        get() = _error

    val banner: HomeBanner = HomeBanner()

    init {
        // TODO sera alterado quando salvarmos os dados do usuario
        banner.username = "Gustavo"
        fetchPasswords(true)
    }

    @SuppressLint("CheckResult")
    fun fetchPasswords(force: Boolean) {
        useCase
                .onFetchPasswords(force)
                .subscribe(this::onSuccess, this::onError)
    }

    fun createPassword() {
        _showCreatePasswordDialog.value = Event(Unit)
    }

    @SuppressLint("CheckResult")
    fun deletePassword(password: HomeItemPassword) {
        useCase
                .onDeletePassword(mapper.transform(password))
                .doOnComplete { _viewState.value = Event(HomeStates.DeleteSuccess) }
                .subscribe { fetchPasswords(true) }
    }

    @SuppressLint("CheckResult")
    fun undoDeleteLastPassword() {
        useCase
                .undoDelete()
                .subscribe(this::onSuccess, this::onError)
    }

    private fun onSuccess(passwords: List<PasswordModel>) {
        _passwordList.value = mapper.mapToItemList(passwords)
        banner.passwordCount = passwords.size
    }

    private fun onError(error: Throwable) {
        _error.value = Event("Houve um erro!")
    }

    override fun onCleared() {
        useCase.disposeAll()
        super.onCleared()
    }
}