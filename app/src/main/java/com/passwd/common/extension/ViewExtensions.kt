package com.passwd.common.extension

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import androidx.core.content.ContextCompat

fun Context.toPx(dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    resources.displayMetrics
)

fun Context.convertColorToHex(colorId: Int) = "#${Integer.toHexString(ContextCompat.getColor(this, colorId) and 0x00ffffff)}"

fun convertHexToColor(hex: String) = Color.parseColor(hex)