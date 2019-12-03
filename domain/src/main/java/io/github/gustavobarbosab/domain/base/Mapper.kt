package io.github.gustavobarbosab.domain.base

interface Mapper<FIRST, SECOND> {
    fun transform(value: FIRST): SECOND
    fun inverse(value: SECOND): FIRST
}