package com.passwd.core.data.mapper

import com.passwd.core.data.database.dto.PasswordDto
import com.passwd.core.domain.base.Mapper
import com.passwd.core.domain.model.PasswordModel

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