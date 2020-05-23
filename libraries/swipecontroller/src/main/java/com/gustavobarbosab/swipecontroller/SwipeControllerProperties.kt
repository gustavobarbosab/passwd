package com.gustavobarbosab.swipecontroller

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class SwipeControllerProperties(
        val leftButton: ButtonProperties? = null,
        val rightButton: ButtonProperties? = null
)

data class ButtonProperties(
        @ColorRes val color: Int,
        @StringRes val text: Int,
        val action: (position: Int) -> Unit
)