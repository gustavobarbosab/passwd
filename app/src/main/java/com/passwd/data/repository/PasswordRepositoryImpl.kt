package com.passwd.data.repository

import com.passwd.data.source.PasswordDataSource
import com.passwd.domain.PasswordDto
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PasswordRepositoryImpl(private val localDataSource: PasswordDataSource) : PasswordRepository {

    private val passwordCache: MutableList<PasswordDto> = mutableListOf()

    override fun getPasswords(force: Boolean): Single<List<PasswordDto>> {
        if (force || passwordCache.isEmpty()) {
            return localDataSource
                .getPasswords()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .doAfterSuccess {
                    passwordCache.clear()
                    passwordCache.addAll(it)
                }
        }

        return Single.just(passwordCache)
    }

    override fun savePassword(password: PasswordDto): Completable =
        localDataSource
            .savePassword(password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate { passwordCache.add(password) }

    override fun deletePassword(password: PasswordDto): Completable =
        localDataSource
            .deletePassword(password)
            .doAfterTerminate { passwordCache.remove(password) }

    override fun editPassword(password: PasswordDto): Completable =
        localDataSource
            .editPassword(password)
            .doAfterTerminate { passwordCache.fill(password) }
}