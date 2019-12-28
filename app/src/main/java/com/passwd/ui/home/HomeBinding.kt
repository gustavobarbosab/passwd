package com.passwd.ui.home

import android.graphics.Color
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.passwd.R

class HomeBinding {

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

        @JvmStatic
        @BindingAdapter("viewBackground")
        fun viewBackground(view: View,
                           color: Int) {
            view.setBackgroundColor(color)
        }
    }
}