package com.passwd.common.widget.password

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.passwd.R
import kotlinx.android.synthetic.main.component_password_text_field.view.textFour
import kotlinx.android.synthetic.main.component_password_text_field.view.textOne
import kotlinx.android.synthetic.main.component_password_text_field.view.textThree
import kotlinx.android.synthetic.main.component_password_text_field.view.textTwo

class PasswordTextField @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    var onPasswordComplete: (password: String) -> Unit = {}

    private val firstWatcher = object : PasswordTextListener() {
        override fun nextEditText(text: CharSequence) {
            changeToEditText(textOne, textTwo)
        }
    }

    private val secondWatcher = object : PasswordTextListener() {
        override fun nextEditText(text: CharSequence) {
            changeToEditText(textTwo, textThree)
        }

        override fun previousEditText() {
            changeToEditText(textTwo, textOne)
        }
    }

    private val thirdWatcher = object : PasswordTextListener() {
        override fun nextEditText(text: CharSequence) {
            changeToEditText(textThree, textFour)
        }

        override fun previousEditText() {
            changeToEditText(textThree, textTwo)
        }
    }

    private val fourthWatcher = object : PasswordTextListener() {
        override fun nextEditText(text: CharSequence) {
            emitPassword()
        }

        override fun previousEditText() {
            changeToEditText(textFour, textThree)
        }
    }

    private val onEditTextFocus = OnFocusChangeListener { v, hasFocus ->
        if (hasFocus) {
            clearText(v as AppCompatEditText)
        }
    }

    private fun clearText(editText: AppCompatEditText) {
        editText.text?.clear()
    }

    private fun emitPassword() {
        val password = password()
        if (isPasswordComplete(password)) {
            onPasswordComplete(password)
        }
    }

    init {
        LayoutInflater.from(this.context).inflate(R.layout.component_password_text_field, this)
        gravity = Gravity.CENTER

        textOne.addTextChangedListener(firstWatcher)
        textTwo.addTextChangedListener(secondWatcher)
        textThree.addTextChangedListener(thirdWatcher)
        textFour.addTextChangedListener(fourthWatcher)

        textOne.setOnKeyListener(firstWatcher)
        textTwo.setOnKeyListener(secondWatcher)
        textThree.setOnKeyListener(thirdWatcher)
        textFour.setOnKeyListener(fourthWatcher)

        textOne.onFocusChangeListener = onEditTextFocus
        textTwo.onFocusChangeListener = onEditTextFocus
        textThree.onFocusChangeListener = onEditTextFocus
        textFour.onFocusChangeListener = onEditTextFocus
    }

    fun changeToEditText(actualEditText: AppCompatEditText,
                         nextEditText: AppCompatEditText) {
        actualEditText.clearFocus()
        nextEditText.requestFocus()
        nextEditText.isCursorVisible = true
    }

    fun password(): String {
        val valueOne = textOne.text
        val valueTwo = textTwo.text
        val valueThree = textThree.text
        val valueFour = textFour.text
        return "$valueOne$valueTwo$valueThree$valueFour"
    }

    private fun isPasswordComplete(password: String) = password.length == 4
}