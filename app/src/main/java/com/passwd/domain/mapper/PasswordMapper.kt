package com.passwd.domain.mapper

import com.passwd.data.database.dto.PasswordDto
import com.passwd.domain.PasswordModel

class PasswordMapper {
    companion object {
        fun mapToModel(passwordDto: List<PasswordDto>): List<PasswordModel> =
            passwordDto.map {
                PasswordModel(
                    it.id!!,
                    it.color!!,
                    it.name!!,
                    it.password!!
                )
            }

        fun mapToDto(passwordModel: List<PasswordModel>): List<PasswordDto> =
            passwordModel.map {
                PasswordDto(
                    it.id,
                    it.color,
                    it.name,
                    it.password
                )
            }

        fun mapToDto(passwordModel: PasswordModel): PasswordDto =
            PasswordDto(
                passwordModel.id,
                passwordModel.color,
                passwordModel.name,
                passwordModel.password
            )

        fun mapToModel(passwordDto: PasswordDto): PasswordModel =
            PasswordModel(
                passwordDto.id!!,
                passwordDto.color!!,
                passwordDto.name!!,
                passwordDto.password!!
            )
    }
}