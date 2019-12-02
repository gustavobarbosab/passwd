package com.passwd.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.passwd.data.repository.PasswordRepository
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
        compositeDisposable.addAll(
            repository
                .getPasswords(force)
                .subscribe({ passwords ->
                    passwords?.let { _passwordList.value = it.map { passwordDto -> MainMapper.map(passwordDto) } }
                }, {
                    // TODO mapear mensagens depois
                    _error.value = "Houve um erro!"
                }))
    }
}