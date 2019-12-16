package com.passwd.data.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password")
data class PasswordDto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "password") val password: String?
)