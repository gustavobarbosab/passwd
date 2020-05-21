package com.passwd.createpassword.di

import com.passwd.createpassword.CreatePasswordViewModel
import com.passwd.core.domain.passwordList.PasswordListUseCase
import com.passwd.core.domain.passwordList.PasswordListUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object CreatePasswordModule {

    val module = module {
        viewModel { CreatePasswordViewModel(get()) }
    }
}