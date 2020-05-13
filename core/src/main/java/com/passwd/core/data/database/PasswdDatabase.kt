package com.passwd.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.passwd.core.data.database.dao.PasswordDao
import com.passwd.core.data.database.dto.PasswordDto

@Database(entities = [PasswordDto::class], version = 1, exportSchema = false)
abstract class PasswdDatabase : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao
}