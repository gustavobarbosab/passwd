package com.passwd.core.di

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class KoinModuleInjection(private val koinModuleConfig: KoinModuleConfig) {

    private val koin
        get() = GlobalContext.get().koin

    var primaryScope: Scope

    init {
        val modules = koinModuleConfig.modules
        if (modules.isEmpty()) throw RuntimeException("Por favor, insira pelo menos um modulo")

        loadKoinModules(modules)

        primaryScope = createScope(
            koinModuleConfig.primaryScopeId,
            koinModuleConfig.primaryScopeName
        )

        koinModuleConfig.owner?.lifecycle?.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    koinModuleConfig.owner = null
                    source.lifecycle.removeObserver(this)
                    unloadModules()
                    logInfo(msg = "Foi destruidoo!")
                }
            }
        })
        logInfo(msg = "Foi iniciado!")
    }

    fun getScope(scopeId: String) = koin.getScope(scopeId)

    fun createScope(primaryScopeId: String, scopeName: String) =
        koin
            .getOrCreateScope(
                primaryScopeId,
                named(scopeName)
            )


    fun unloadModules() {
        unloadKoinModules(koinModuleConfig.modules)
    }
}