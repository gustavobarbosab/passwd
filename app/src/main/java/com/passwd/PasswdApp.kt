package com.passwd

import android.app.Application
import com.passwd.data.database.di.DatabaseModule
import com.passwd.data.repository.di.RepositoryModule
import com.passwd.data.source.di.DataSourceModule
import com.passwd.di.AppModule
import com.passwd.ui.home.di.MainModule
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
                    MainModule.module
                )
            )
        }
    }
}

