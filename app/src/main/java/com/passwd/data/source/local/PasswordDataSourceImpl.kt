package com.passwd.data.source.local

import com.passwd.data.database.dao.PasswordDao
import com.passwd.data.source.PasswordDataSource
import com.passwd.domain.PasswordModel
import com.passwd.domain.mapper.PasswordMapper
import io.reactivex.Completable
import io.reactivex.Single

class PasswordDataSourceImpl(private val passwordDao: PasswordDao) : PasswordDataSource {

    override fun deletePassword(password: PasswordModel): Completable = passwordDao.delete(PasswordMapper.mapToDto(password))

    override fun getPasswords(): Single<List<PasswordModel>> = passwordDao.getAll().map { PasswordMapper.mapToModel(it) }

    override fun editPassword(password: PasswordModel): Completable = passwordDao.update(PasswordMapper.mapToDto(password))

    override fun savePassword(password: PasswordModel): Completable = passwordDao.insert(PasswordMapper.mapToDto(password))
}