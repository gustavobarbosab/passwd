package com.passwd.home.di

import com.passwd.core.domain.passwordList.PasswordListUseCase
import com.passwd.core.domain.passwordList.PasswordListUseCaseImpl
import com.passwd.home.HomeRecyclerAdapter
import com.passwd.home.HomeViewModel
import com.passwd.home.model.HomeMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object HomeModule {
    val module = module {
        factory <PasswordListUseCase> { PasswordListUseCaseImpl(get()) }
        factory { HomeRecyclerAdapter() }
        factory{ HomeMapper() }
        viewModel { HomeViewModel(get(), get()) }
    }
}