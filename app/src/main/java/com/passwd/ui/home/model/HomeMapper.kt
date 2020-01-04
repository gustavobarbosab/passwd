package com.passwd.ui.home.model

import io.github.gustavobarbosab.domain.base.Mapper
import io.github.gustavobarbosab.domain.model.PasswordModel

class HomeMapper : Mapper<HomeItemPassword, PasswordModel> {
    override fun transform(value: HomeItemPassword): PasswordModel =
        PasswordModel(
            value.id,
            value.name,
            value.password,
            value.color
        )

    override fun inverse(value: PasswordModel): HomeItemPassword =
        HomeItemPassword(
            value.id!!,
            value.name,
            value.password,
            value.color
        )

    fun mapToItemList(passwordModel: List<PasswordModel>): List<HomeItemPassword> =
        passwordModel.map { inverse(it) }
}