package com.passwd.ui.home

import android.text.method.PasswordTransformationMethod
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter

class MainBinding {

    companion object {
        @JvmStatic
        @BindingAdapter("passwordText")
        fun showAndHidePassword(checkbox: CheckBox,
                                passwordText: TextView) {
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) passwordText.transformationMethod = PasswordTransformationMethod()
                else passwordText.transformationMethod = null
            }
        }
    }
}