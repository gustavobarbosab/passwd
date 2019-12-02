package com.passwd.common.extension

import android.app.Activity
import android.widget.Toast

fun Activity.showShortToast(message: String?) {
    message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
}