package com.passwd.domain

data class PasswordModel(
    val id: Long? = null,
    val color: Int,
    val name: String,
    val password: String
)