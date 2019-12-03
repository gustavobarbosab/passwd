package com.passwd.data.source.local

import com.passwd.data.database.dao.PasswordDao
import com.passwd.data.mapper.PasswordMapper
import io.github.gustavobarbosab.domain.model.PasswordModel
import io.github.gustavobarbosab.domain.source.PasswordDataSource
import io.reactivex.Completable
import io.reactivex.Single

class PasswordDataSourceImpl(private val passwordDao: PasswordDao,
                             private val mapper: PasswordMapper) : PasswordDataSource {

    override fun deletePassword(password: PasswordModel): Completable =
        passwordDao
            .delete(mapper.inverse(password))

    override fun getPasswords(): Single<List<PasswordModel>> =
        passwordDao
            .getAll()
            .map { mapper.map(it) }

    override fun editPassword(password: PasswordModel): Completable =
        passwordDao
            .update(mapper.inverse(password))

    override fun savePassword(password: PasswordModel): Single<List<PasswordModel>> =
        passwordDao
            .insert(mapper.inverse(password))
            .andThen(getPasswords())
}