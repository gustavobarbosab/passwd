package com.passwd.core.domain.passwordList

import com.passwd.core.domain.model.PasswordModel
import com.passwd.core.domain.repository.PasswordRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class PasswordListUseCaseImpl(private val repository: PasswordRepository) : PasswordListUseCase {

    private val compositeDisposable = CompositeDisposable()

    private var lastPasswordDeleted: PasswordModel? = null

    override fun fetchPasswords(force: Boolean): Single<List<PasswordModel>> =
        repository
            .getPasswords(force)
            .doOnSubscribe { compositeDisposable.add(it) }

    override fun deletePassword(password: PasswordModel): Completable {
        lastPasswordDeleted = password
        return repository
            .deletePassword(password)
            .doOnSubscribe { compositeDisposable.add(it) }
    }

    override fun createPassword(password: PasswordModel): Single<List<PasswordModel>> =
        repository
            .savePassword(password)
            .doOnSubscribe { compositeDisposable.add(it) }

    override fun undoDelete(): Single<List<PasswordModel>> =
        lastPasswordDeleted?.let {
            createPassword(it)
        } ?: Single.error(UnsupportedOperationException())

    override fun disposeAll() {
        compositeDisposable.clear()
    }
}