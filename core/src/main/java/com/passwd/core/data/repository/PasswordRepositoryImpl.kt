package com.passwd.core.data.repository

import com.passwd.core.domain.model.PasswordModel
import com.passwd.core.domain.repository.PasswordRepository
import com.passwd.core.domain.source.PasswordDataSource
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PasswordRepositoryImpl(private val localDataSource: PasswordDataSource) : PasswordRepository {

    private val passwordCache: MutableList<PasswordModel> = mutableListOf()

    override fun getPasswords(force: Boolean): Single<List<PasswordModel>> {
        if (force || passwordCache.isEmpty()) {
            return localDataSource
                .getPasswords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { updateList(it) }
        }

        return Single.just(passwordCache)
    }

    override fun savePassword(password: PasswordModel): Single<List<PasswordModel>> =
        localDataSource
            .savePassword(password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { updateList(it) }

    override fun deletePassword(password: PasswordModel): Completable =
        localDataSource
            .deletePassword(password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { passwordCache.remove(password) }

    override fun editPassword(password: PasswordModel): Completable =
        localDataSource
            .editPassword(password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { passwordCache.fill(password) }

    private fun updateList(passwords: List<PasswordModel>) {
        passwordCache.clear()
        passwordCache.addAll(passwords)
    }
}