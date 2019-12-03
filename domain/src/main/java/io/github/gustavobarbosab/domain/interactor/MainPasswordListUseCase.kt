package io.github.gustavobarbosab.domain.interactor

import io.github.gustavobarbosab.domain.model.PasswordModel
import io.github.gustavobarbosab.domain.repository.PasswordRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class MainPasswordListUseCase(private val repository: PasswordRepository) {

    private val compositeDisposable = CompositeDisposable()

    fun onFetchPasswords(force: Boolean): Single<List<PasswordModel>> =
        repository
            .getPasswords(force)
            .doOnSubscribe { compositeDisposable.add(it) }

    fun onDeletePassword(password: PasswordModel): Completable =
        repository
            .deletePassword(password)
            .doOnSubscribe { compositeDisposable.add(it) }

    fun onCreatePassword(password: PasswordModel): Single<List<PasswordModel>> =
        repository
            .savePassword(password)
            .doOnSubscribe { compositeDisposable.add(it) }

    fun disposeAll() {
        compositeDisposable.dispose()
    }
}