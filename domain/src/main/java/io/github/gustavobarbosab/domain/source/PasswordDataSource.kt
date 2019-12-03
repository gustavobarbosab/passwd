package io.github.gustavobarbosab.domain.source

import io.github.gustavobarbosab.domain.model.PasswordModel
import io.reactivex.Completable
import io.reactivex.Single

interface PasswordDataSource {
    fun savePassword(password: PasswordModel): Single<List<PasswordModel>>

    fun deletePassword(password: PasswordModel): Completable

    fun getPasswords(): Single<List<PasswordModel>>

    fun editPassword(password: PasswordModel): Completable
}