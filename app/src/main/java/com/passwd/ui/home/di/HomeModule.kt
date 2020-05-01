package com.passwd.ui.home.di

import com.passwd.ui.home.HomeRecyclerAdapter
import com.passwd.ui.home.HomeViewModel
import com.passwd.ui.home.model.HomeMapper
import io.github.gustavobarbosab.domain.interactor.passwordList.PasswordListUseCase
import io.github.gustavobarbosab.domain.interactor.passwordList.PasswordListUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object HomeModule {
    const val SCOPE_NAME = "HOMESCOPE"

    val module = module {
        scope(named(SCOPE_NAME)) {
            viewModel { HomeViewModel(get(), get()) }
            scoped { HomeRecyclerAdapter() }
            factory { HomeMapper() }
            factory<PasswordListUseCase> { PasswordListUseCaseImpl(get()) }
        }
    }
}