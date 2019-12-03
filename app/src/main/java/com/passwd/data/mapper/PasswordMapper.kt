package com.passwd.data.mapper

import com.passwd.data.database.dto.PasswordDto
import io.github.gustavobarbosab.domain.base.Mapper
import io.github.gustavobarbosab.domain.model.PasswordModel

class PasswordMapper : Mapper<PasswordDto, PasswordModel> {
    override fun transform(value: PasswordDto): PasswordModel =
        PasswordModel(
            value.id!!,
            value.name!!,
            value.password!!,
            value.color!!
        )

    override fun inverse(value: PasswordModel): PasswordDto =
        PasswordDto(
            value.id,
            value.color,
            value.name,
            value.password
        )

    fun map(passwordDto: List<PasswordDto>): List<PasswordModel> =
        passwordDto.map { transform(it) }

    fun unmap(passwordModel: List<PasswordModel>): List<PasswordDto> =
        passwordModel.map { inverse(it) }
}