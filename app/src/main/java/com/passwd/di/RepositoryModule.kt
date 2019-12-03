package com.passwd.di

import com.passwd.data.mapper.PasswordMapper
import com.passwd.data.repository.PasswordRepositoryImpl
import io.github.gustavobarbosab.domain.repository.PasswordRepository
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        single<PasswordRepository> { PasswordRepositoryImpl(get()) }
    }
}