package com.passwd.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.passwd.common.Event
import com.passwd.ui.home.model.MainItemPassword
import com.passwd.ui.home.model.MainMapper
import io.github.gustavobarbosab.domain.interactor.passwordList.PasswordListUseCase
import io.github.gustavobarbosab.domain.model.PasswordModel

class MainViewModel(private val useCase: PasswordListUseCase,
                    private val mapper: MainMapper) : ViewModel() {

    private val _passwordList: MutableLiveData<List<MainItemPassword>> = MutableLiveData()
    val passwordList: LiveData<List<MainItemPassword>>
        get() = _passwordList

    private val _showCreatePasswordDialog: MutableLiveData<Event<Unit>> = MutableLiveData()
    val showCreatePasswordDialog: LiveData<Event<Unit>>
        get() = _showCreatePasswordDialog

    private val _error: MutableLiveData<Event<String>> = MutableLiveData()
    val error: LiveData<Event<String>>
        get() = _error

    @SuppressLint("CheckResult")
    fun fetchPasswords(force: Boolean) {
        useCase
                .onFetchPasswords(force)
                .subscribe(this::onSuccess, this::onError)
    }

    fun createPassword() {
        _showCreatePasswordDialog.value = Event(Unit)
    }

    private fun onSuccess(passwords: List<PasswordModel>) {
        _passwordList.value = mapper.mapToItemList(passwords)
    }

    private fun onError(error: Throwable) {
        _error.value = Event("Houve um erro!")
    }

    override fun onCleared() {
        useCase.disposeAll()
        super.onCleared()
    }
}