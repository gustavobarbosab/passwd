package com.passwd.core.domain.source

import com.passwd.core.domain.model.PasswordModel
import io.reactivex.Completable
import io.reactivex.Single

interface PasswordDataSource {
    fun savePassword(password: PasswordModel): Single<List<PasswordModel>>

    fun deletePassword(password: PasswordModel): Completable

    fun getPasswords(): Single<List<PasswordModel>>

    fun editPassword(password: PasswordModel): Completable
}