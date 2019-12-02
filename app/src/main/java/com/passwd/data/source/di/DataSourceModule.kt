package com.passwd.data.source.di

import com.passwd.data.source.PasswordDataSource
import com.passwd.data.source.local.PasswordDataSourceImpl
import org.koin.dsl.module

object DataSourceModule {
    val module = module {
        factory<PasswordDataSource> { PasswordDataSourceImpl(get()) }
    }
}