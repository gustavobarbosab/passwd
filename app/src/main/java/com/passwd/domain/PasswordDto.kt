package com.passwd.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password")
data class PasswordDto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long?,
    @ColumnInfo(name = "color") val color: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "password") val password: String?
)