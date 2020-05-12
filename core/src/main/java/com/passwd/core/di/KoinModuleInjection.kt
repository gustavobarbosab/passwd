package com.passwd.core.di

import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class KoinModuleInjection(private val koinModuleConfig: KoinModuleConfig) {

    var moduleScope: Scope

    init {
        val modules = koinModuleConfig.modules
        if (modules.isEmpty()) throw RuntimeException("Por favor, insira uma lista de modulos")
        loadKoinModules(modules)
        moduleScope =
            GlobalContext.get().koin.getOrCreateScope(koinModuleConfig.id, named(koinModuleConfig.name))

    }

    fun unloadModules() {
        val modules = koinModuleConfig.modules
        moduleScope.close()
        unloadKoinModules(modules)
    }
}