package com.passwd.createpassword

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gustavobarbosab.databinding.Event
import com.gustavobarbosab.extension.workIOThread
import com.passwd.core.domain.model.PasswordModel
import com.passwd.core.domain.passwordList.PasswordListUseCase
import io.reactivex.disposables.CompositeDisposable

class CreatePasswordViewModel(private val passwordListUseCase: PasswordListUseCase) : ViewModel() {

    private val _onGenericError: MutableLiveData<Event<Unit>> = MutableLiveData()
    val genericError: LiveData<Event<Unit>> = _onGenericError

    private val _onFieldValidationError: MutableLiveData<Event<Unit>> = MutableLiveData()
    val validationFieldsError: LiveData<Event<Unit>> = _onFieldValidationError

    private val _onCreatePasswordSuccess: MutableLiveData<Event<Unit>> = MutableLiveData()
    val createPasswordSuccess: LiveData<Event<Unit>> = _onCreatePasswordSuccess

    var passwordName: ObservableField<String> = ObservableField()
    var passwordKey: ObservableField<String> = ObservableField()

    var colorSelected: Int = -1

    fun savePassword() {
        val isKeyValid = validatePasswordKey()
        val isNameValid = validatePasswordName()

        if (isKeyValid && isNameValid) {
            onValidationSuccess()
        } else {
            _onFieldValidationError.value = Event(Unit)
        }
    }

    @SuppressLint("CheckResult")
    private fun onValidationSuccess() {
        val key = passwordKey.get()!!
        val name = passwordName.get()!!

        passwordListUseCase
            .createPassword(PasswordModel(name = name, password = key, color = colorSelected))
            .workIOThread()
            .subscribe({
                _onCreatePasswordSuccess.value = Event(Unit)
            }, {
                _onGenericError.value = Event(Unit)
            })
    }

    private fun validatePasswordKey(): Boolean = passwordKey.get()?.isNotEmpty() == true

    private fun validatePasswordName(): Boolean = passwordName.get()?.isNotEmpty() == true

    override fun onCleared() {
        passwordListUseCase.disposeAll()
        super.onCleared()
    }
}