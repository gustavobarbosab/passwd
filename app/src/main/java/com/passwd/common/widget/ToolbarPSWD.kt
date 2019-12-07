package com.passwd.common.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.passwd.R

class ToolbarPSWD @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    protected val textTitle: AppCompatTextView

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_main, this)
        setPadding(0, 0, 0, 0)
        setContentInsetsAbsolute(0, 0)
        title = ""

        textTitle = findViewById(R.id.toolbar_title)

        setupArguments(attrs)
    }

    private fun setupArguments(attrs: AttributeSet?) {
        attrs?.let { attributes ->
            val typedArray = context
                .theme
                .obtainStyledAttributes(attributes, R.styleable.passwdToolbar, 0, 0)

            try {
                val text = typedArray.getString(R.styleable.passwdToolbar_toolbarTitle)
                if (!TextUtils.isEmpty(text)) {
                    setTitleToolbar(text!!)
                }
            } finally {
                typedArray.recycle()
            }
        }
    }

    override fun setTitle(resId: Int) {
    }

    override fun setTitle(title: CharSequence?) {
    }

    fun setTitleToolbar(title: String) {
        textTitle.text = title

    }

    fun setTitleToolbar(title: Int) {
        textTitle.setText(title)
    }
}