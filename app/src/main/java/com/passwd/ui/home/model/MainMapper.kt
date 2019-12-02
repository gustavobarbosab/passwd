package com.passwd.ui.home.model

import com.passwd.domain.PasswordDto

class MainMapper {
    companion object {
        fun map(passwordDto: PasswordDto): MainItemPassword =
            MainItemPassword(
                passwordDto.id,
                passwordDto.name,
                passwordDto.password,
                passwordDto.color
            )
    }
}