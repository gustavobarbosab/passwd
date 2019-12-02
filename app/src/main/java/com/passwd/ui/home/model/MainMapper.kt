package com.passwd.ui.home.model

import com.passwd.domain.PasswordModel

class MainMapper {
    companion object {
        fun mapToMainItem(passwordModel: PasswordModel): MainItemPassword =
            MainItemPassword(
                passwordModel.id,
                passwordModel.name,
                passwordModel.password,
                passwordModel.color
            )

        fun mapToMainItemList(passwordModel: List<PasswordModel>): List<MainItemPassword> =
            passwordModel.map {
                MainItemPassword(
                    it.id,
                    it.name,
                    it.password,
                    it.color
                )
            }
    }
}