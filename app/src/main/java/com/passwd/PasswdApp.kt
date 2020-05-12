package com.passwd

import android.app.Application
import com.passwd.di.AppModule
import com.passwd.di.DataSourceModule
import com.passwd.di.DatabaseModule
import com.passwd.di.RepositoryModule
import com.passwd.ui.authentication.di.AuthenticationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PasswdApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PasswdApp)
            modules(
                listOf(
                    AppModule.module,
                    DatabaseModule.module,
                    DataSourceModule.module,
                    RepositoryModule.module,
                    AuthenticationModule.module
                )
            )
        }
    }
}

