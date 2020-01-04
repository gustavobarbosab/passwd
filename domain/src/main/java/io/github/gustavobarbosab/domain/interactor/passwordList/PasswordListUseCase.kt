package io.github.gustavobarbosab.domain.interactor.passwordList

import io.github.gustavobarbosab.domain.model.PasswordModel
import io.reactivex.Completable
import io.reactivex.Single

interface PasswordListUseCase {
    fun onFetchPasswords(force: Boolean): Single<List<PasswordModel>>
    fun onDeletePassword(password: PasswordModel): Completable
    fun onCreatePassword(password: PasswordModel): Single<List<PasswordModel>>
    fun undoDelete(): Single<List<PasswordModel>>
    fun disposeAll()
}