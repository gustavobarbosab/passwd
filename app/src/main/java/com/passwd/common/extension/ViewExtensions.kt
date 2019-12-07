package com.passwd.common.extension

import android.content.Context
import android.util.TypedValue

fun Context.toPx(dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    resources.displayMetrics
)