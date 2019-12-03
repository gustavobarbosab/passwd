package com.passwd.ui.home.di

import com.passwd.ui.home.MainActivity
import com.passwd.ui.home.MainRecyclerAdapter
import com.passwd.ui.home.MainViewModel
import com.passwd.ui.home.model.MainMapper
import io.github.gustavobarbosab.domain.interactor.MainPasswordListUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MainModule {
    val module = module {
        scope(named<MainActivity>()) {
            viewModel { MainViewModel(get(), get()) }
            scoped { MainRecyclerAdapter() }
            factory { MainMapper() }
            factory { MainPasswordListUseCase(get()) }
        }
    }
}