package com.passwd.data.source

import com.passwd.domain.PasswordDto
import io.reactivex.Completable
import io.reactivex.Single

interface PasswordDataSource {

    fun savePassword(password: PasswordDto): Completable

    fun deletePassword(password: PasswordDto): Completable

    fun getPasswords(): Single<List<PasswordDto>>

    fun editPassword(password: PasswordDto): Completable
}