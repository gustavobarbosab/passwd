package com.passwd.core.di

import com.passwd.core.data.repository.PasswordRepositoryImpl
import com.passwd.core.domain.repository.PasswordRepository
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        single<PasswordRepository> { PasswordRepositoryImpl(get()) }
    }
}