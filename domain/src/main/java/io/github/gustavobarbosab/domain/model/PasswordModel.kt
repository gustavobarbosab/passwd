package io.github.gustavobarbosab.domain.model

data class PasswordModel(
    val id: Long? = null,
    val name: String,
    val password: String,
    val color: Int
)