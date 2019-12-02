package com.passwd.data.source

import com.passwd.domain.PasswordModel
import io.reactivex.Completable
import io.reactivex.Single

interface PasswordDataSource {

    fun savePassword(password: PasswordModel): Completable

    fun deletePassword(password: PasswordModel): Completable

    fun getPasswords(): Single<List<PasswordModel>>

    fun editPassword(password: PasswordModel): Completable
}