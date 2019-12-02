package com.passwd.data.source.local

import com.passwd.data.database.dao.PasswordDao
import com.passwd.data.source.PasswordDataSource
import com.passwd.domain.PasswordDto
import io.reactivex.Completable
import io.reactivex.Single

class PasswordDataSourceImpl(private val passwordDao: PasswordDao) : PasswordDataSource {

    override fun deletePassword(password: PasswordDto): Completable = passwordDao.delete(password)

    override fun getPasswords(): Single<List<PasswordDto>> = passwordDao.getAll()

    override fun editPassword(password: PasswordDto): Completable = passwordDao.update(password)

    override fun savePassword(password: PasswordDto): Completable = passwordDao.insert(password)
}