package com.passwd.data.repository

import com.passwd.domain.PasswordDto
import io.reactivex.Completable
import io.reactivex.Single

interface PasswordRepository {
    fun savePassword(password: PasswordDto): Completable

    fun deletePassword(password: PasswordDto): Completable

    fun getPasswords(force: Boolean = true): Single<List<PasswordDto>>

    fun editPassword(password: PasswordDto): Completable
}