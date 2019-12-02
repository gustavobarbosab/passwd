package com.passwd.data.repository

import com.passwd.domain.PasswordModel
import io.reactivex.Completable
import io.reactivex.Single

interface PasswordRepository {
    fun savePassword(password: PasswordModel): Completable

    fun deletePassword(password: PasswordModel): Completable

    fun getPasswords(force: Boolean = true): Single<List<PasswordModel>>

    fun editPassword(password: PasswordModel): Completable
}