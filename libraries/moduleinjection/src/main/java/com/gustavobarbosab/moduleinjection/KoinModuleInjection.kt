package com.gustavobarbosab.moduleinjection

import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class KoinModuleInjection(private val modules: List<Module>) {

    private val loadFeatures by lazy { loadKoinModules(modules) }
    fun injectFeatures() {
        if (modules.isNotEmpty()) loadFeatures
    }

    fun unloadModules() {
        if (modules.isNotEmpty()) unloadKoinModules(modules)
    }
}