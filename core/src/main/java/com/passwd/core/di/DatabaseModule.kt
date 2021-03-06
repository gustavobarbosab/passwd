package com.passwd.core.di

import android.content.Context
import androidx.room.Room
import com.passwd.core.data.database.PasswdDatabase
import org.koin.dsl.module

object DatabaseModule {
    val module = module {
        single { Room.databaseBuilder(get<Context>().applicationContext, PasswdDatabase::class.java, "database-passwd").build() }

        single { get<PasswdDatabase>().passwordDao() }
    }
}