package com.passwd.core.domain.repository

import com.passwd.core.domain.model.PasswordModel
import io.reactivex.Completable
import io.reactivex.Single

interface PasswordRepository {
    fun savePassword(password: PasswordModel): Single<List<PasswordModel>>
    fun deletePassword(password: PasswordModel): Completable
    fun getPasswords(force: Boolean = true): Single<List<PasswordModel>>
    fun editPassword(password: PasswordModel): Completable
}