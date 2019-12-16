package com.passwd.ui.create.di

import com.passwd.ui.create.CreatePasswordDialog
import com.passwd.ui.create.CreatePasswordViewModel
import io.github.gustavobarbosab.domain.interactor.passwordList.PasswordListUseCase
import io.github.gustavobarbosab.domain.interactor.passwordList.PasswordListUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object CreatePasswordModule {
    val module = module {
        scope(named<CreatePasswordDialog>()) {
            viewModel { CreatePasswordViewModel(get()) }
            factory<PasswordListUseCase> { PasswordListUseCaseImpl(get()) }
        }
    }
}