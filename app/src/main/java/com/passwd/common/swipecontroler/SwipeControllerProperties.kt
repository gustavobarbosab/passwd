package com.passwd.common.swipecontroler

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class SwipeControllerProperties(
        val leftButton: ButtonProperties,
        val rightButton: ButtonProperties
)

data class ButtonProperties(
        @ColorRes val color: Int,
        @StringRes val text: Int,
        val action: (position: Int) -> Unit
)