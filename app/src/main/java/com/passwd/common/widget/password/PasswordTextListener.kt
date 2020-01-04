package com.passwd.common.widget.password

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View

abstract class PasswordTextListener : TextWatcher, View.OnKeyListener {
    private var lastCharSequence: CharSequence? = null

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s != lastCharSequence) {
            if (s.isNotEmpty()) nextEditText(s)
            lastCharSequence = s
        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) previousEditText()
        return false;
    }

    open fun nextEditText(text: CharSequence) {}

    open fun previousEditText() {}
}