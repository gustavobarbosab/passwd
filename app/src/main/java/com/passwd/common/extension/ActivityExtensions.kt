package com.passwd.common.extension

import android.content.Context
import android.widget.Toast

fun Context.showShortToast(message: String?) {
    message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
}