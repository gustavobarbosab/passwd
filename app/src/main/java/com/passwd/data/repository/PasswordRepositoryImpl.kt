package com.passwd.data.repository

import com.passwd.common.extension.workIOThread
import io.github.gustavobarbosab.domain.model.PasswordModel
import io.github.gustavobarbosab.domain.repository.PasswordRepository
import io.github.gustavobarbosab.domain.source.PasswordDataSource
import io.reactivex.Completable
import io.reactivex.Single

class PasswordRepositoryImpl(private val localDataSource: PasswordDataSource) : PasswordRepository {

    private val passwordCache: MutableList<PasswordModel> = mutableListOf()

    override fun getPasswords(force: Boolean): Single<List<PasswordModel>> {
        if (force || passwordCache.isEmpty()) {
            return localDataSource
                .getPasswords()
                .workIOThread()
                .doOnSuccess { updateList(it) }
        }

        return Single.just(passwordCache)
    }

    override fun savePassword(password: PasswordModel): Single<List<PasswordModel>> =
        localDataSource
            .savePassword(password)
            .workIOThread()
            .doOnSuccess { updateList(it) }

    override fun deletePassword(password: PasswordModel): Completable =
        localDataSource
            .deletePassword(password)
            .workIOThread()
            .doOnComplete { passwordCache.remove(password) }

    override fun editPassword(password: PasswordModel): Completable =
        localDataSource
            .editPassword(password)
            .workIOThread()
            .doOnComplete { passwordCache.fill(password) }

    private fun updateList(passwords: List<PasswordModel>) {
        passwordCache.clear()
        passwordCache.addAll(passwords)
    }
}