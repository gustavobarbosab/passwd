package com.passwd.core.di

import org.koin.core.module.Module

data class KoinModuleConfig(
    val id: String,
    val name: String,
    val modules: List<Module>
)