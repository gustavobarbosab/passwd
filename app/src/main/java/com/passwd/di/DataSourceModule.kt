package com.passwd.di

import com.passwd.data.mapper.PasswordMapper
import com.passwd.data.source.local.PasswordDataSourceImpl
import io.github.gustavobarbosab.domain.source.PasswordDataSource
import org.koin.dsl.module

object DataSourceModule {
    val module = module {
        factory { PasswordMapper() }
        factory<PasswordDataSource> { PasswordDataSourceImpl(get(), get()) }
    }
}