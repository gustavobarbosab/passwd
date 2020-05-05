package com.passwd.core.di

import org.koin.core.module.Module

data class ModuleConfig(
    val id: String,
    val name: String,
    val module: Module
)