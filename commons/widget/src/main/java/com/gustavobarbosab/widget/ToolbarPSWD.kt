package com.gustavobarbosab.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.toolbar_main.view.*

class ToolbarPSWD @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_main, this)
        setPadding(0, 0, 0, 0)
        setContentInsetsAbsolute(0, 0)
        setupArguments(attrs)
    }

    private fun setupArguments(attrs: AttributeSet?) {
        attrs?.let { attributes ->
            val attrsArray = intArrayOf(android.R.attr.title)
            val typeArray = context.obtainStyledAttributes(attributes, attrsArray)
            try {
                val text = typeArray.getString(0)
                title = text
            } finally {
                typeArray.recycle()
            }
        }
    }

    override fun setTitle(textRes: Int) {
        toolbar_title?.setText(textRes)
    }

    override fun setTitle(title: CharSequence?) {
        toolbar_title?.text = title
    }
}