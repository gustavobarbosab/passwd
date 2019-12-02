package com.passwd.data.repository

import com.passwd.data.source.PasswordDataSource
import com.passwd.domain.PasswordModel
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
                .compose { workThread(it) }
                .doAfterSuccess {
                    passwordCache.clear()
                    passwordCache.addAll(it)
                }
        }

        return Single.just(passwordCache)
    }

    override fun savePassword(password: PasswordModel): Completable =
        localDataSource
            .savePassword(password)
            .compose { workThread(it) }
            .doAfterTerminate { passwordCache.add(password) }

    override fun deletePassword(password: PasswordModel): Completable =
        localDataSource
            .deletePassword(password)
            .compose { workThread(it) }
            .doAfterTerminate { passwordCache.remove(password) }

    override fun editPassword(password: PasswordModel): Completable =
        localDataSource
            .editPassword(password)
            .compose { workThread(it) }
            .doAfterTerminate { passwordCache.fill(password) }

    private fun <T> workThread(single: Single<T>) = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    private fun workThread(single: Completable) = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}