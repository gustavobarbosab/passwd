package com.passwd.core.di

import com.passwd.core.data.mapper.PasswordMapper
import com.passwd.core.data.source.local.PasswordDataSourceImpl
import io.github.gustavobarbosab.domain.source.PasswordDataSource
import org.koin.dsl.module

object DataSourceModule {
    val module = module {
        factory { PasswordMapper() }
        factory<PasswordDataSource> { PasswordDataSourceImpl(get(), get()) }
    }
}