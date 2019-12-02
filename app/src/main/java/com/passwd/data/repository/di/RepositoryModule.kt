package com.passwd.data.repository.di

import com.passwd.data.repository.PasswordRepository
import com.passwd.data.repository.PasswordRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        single<PasswordRepository> { PasswordRepositoryImpl(get()) }
    }
}