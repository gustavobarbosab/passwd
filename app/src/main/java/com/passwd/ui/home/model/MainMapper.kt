package com.passwd.ui.home.model

import io.github.gustavobarbosab.domain.base.Mapper
import io.github.gustavobarbosab.domain.model.PasswordModel

class MainMapper : Mapper<MainItemPassword, PasswordModel> {
    override fun transform(value: MainItemPassword): PasswordModel =
        PasswordModel(
            value.id,
            value.name,
            value.password,
            value.color
        )

    override fun inverse(value: PasswordModel): MainItemPassword =
        MainItemPassword(
            value.id!!,
            value.name,
            value.password,
            value.color
        )

    fun mapToItemList(passwordModel: List<PasswordModel>): List<MainItemPassword> =
        passwordModel.map { inverse(it) }
}