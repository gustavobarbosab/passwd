package com.passwd.createpassword.di

import com.passwd.createpassword.CreatePasswordDialog
import com.passwd.createpassword.CreatePasswordViewModel
import io.github.gustavobarbosab.domain.interactor.passwordList.PasswordListUseCase
import io.github.gustavobarbosab.domain.interactor.passwordList.PasswordListUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object CreatePasswordModule {

    const val SCOPE_NAME = "SCOPE_NAME.CreatePasswordModule"

    val module = module {
        scope(named(SCOPE_NAME)) {
            viewModel { CreatePasswordViewModel(get()) }
            factory<PasswordListUseCase> { PasswordListUseCaseImpl(get()) }
        }
    }
}