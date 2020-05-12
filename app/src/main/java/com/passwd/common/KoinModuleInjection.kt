package com.passwd.common

import com.passwd.core.di.ModuleConfig
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import java.lang.RuntimeException

class KoinModuleInjection(private val moduleConfig: ModuleConfig) {

    var moduleScope: Scope

    init {
        val modules = moduleConfig.modules
        if (modules.isEmpty()) throw RuntimeException("Por favor, insira uma lista de modulos")
        loadKoinModules(modules)
        moduleScope =
            GlobalContext.get().koin.getOrCreateScope(moduleConfig.id, named(moduleConfig.name))

    }

    fun unloadModules() {
        val modules = moduleConfig.modules
        moduleScope.close()
        unloadKoinModules(modules)
    }
}