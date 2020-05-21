package com.passwd.core.domain.passwordList

import com.passwd.core.domain.model.PasswordModel
import io.reactivex.Completable
import io.reactivex.Single

interface PasswordListUseCase {
    fun fetchPasswords(force: Boolean): Single<List<PasswordModel>>
    fun deletePassword(password: PasswordModel): Completable
    fun createPassword(password: PasswordModel): Single<List<PasswordModel>>
    fun undoDelete(): Single<List<PasswordModel>>
    fun disposeAll()
}