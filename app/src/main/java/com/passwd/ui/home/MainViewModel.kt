package com.passwd.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.passwd.R
import com.passwd.ui.home.model.MainItemPassword
import com.passwd.ui.home.model.MainMapper
import io.github.gustavobarbosab.domain.interactor.MainPasswordListUseCase
import io.github.gustavobarbosab.domain.model.PasswordModel

@SuppressLint("CheckResult")
class MainViewModel(private val mainUseCase: MainPasswordListUseCase,
                    private val mapper: MainMapper) : ViewModel() {

    private val _passwordList: MutableLiveData<List<MainItemPassword>> = MutableLiveData()
    val passwordList: LiveData<List<MainItemPassword>>
        get() = _passwordList

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    fun fetchPasswords(force: Boolean) {
        mainUseCase
            .onFetchPasswords(force)
            .subscribe(this::onSuccess, this::onError)
    }

    fun createFakePassword() {
        // TODO implementação para testar a criação da lista
        mainUseCase
            .onCreatePassword(PasswordModel(null, "Teste Gustavo", "senha", R.color.colorAccent))
            .subscribe(this::onSuccess, this::onError)
    }

    private fun onSuccess(passwords: List<PasswordModel>) {
        _passwordList.value = mapper.mapToItemList(passwords)
    }

    private fun onError(error: Throwable) {
        _error.value = "Houve um erro!"
    }

    override fun onCleared() {
        mainUseCase.disposeAll()
        super.onCleared()
    }
}