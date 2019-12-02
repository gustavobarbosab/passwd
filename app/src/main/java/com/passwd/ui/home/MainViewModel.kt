package com.passwd.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.passwd.R
import com.passwd.data.repository.PasswordRepository
import com.passwd.domain.PasswordModel
import com.passwd.ui.home.model.MainItemPassword
import com.passwd.ui.home.model.MainMapper
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(val repository: PasswordRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _passwordList: MutableLiveData<List<MainItemPassword>> = MutableLiveData()
    val passwordList: LiveData<List<MainItemPassword>>
        get() = _passwordList

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    fun fetchPasswords(force: Boolean = true) {
        compositeDisposable.add(
            repository
                .getPasswords(force)
                .map { MainMapper.mapToMainItemList(it) }
                .subscribe({
                    _passwordList.value = it
                }, {
                    // TODO mapear mensagens depois
                    _error.value = "Houve um erro!"
                }))
    }

    fun createFakePassword() {
        // TODO implementação para testar a criação da lista
        compositeDisposable.add(
            repository
                .savePassword(PasswordModel(name = "Teste 123", password = "12312", color = R.color.colorAccent))
                .subscribe({
                    fetchPasswords(true)
                }, {
                    _error.value = "Houve um erro!"
                })
        )
    }
}