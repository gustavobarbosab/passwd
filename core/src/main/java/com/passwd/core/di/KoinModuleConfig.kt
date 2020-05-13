package com.passwd.core.di

import org.koin.core.module.Module

data class KoinModuleConfig(
    val primaryScopeId: String,
    val primaryScopeName: String,
    val modules: List<Module>
)