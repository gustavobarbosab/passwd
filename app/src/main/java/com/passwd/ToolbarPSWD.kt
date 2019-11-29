package com.passwd

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar

class ToolbarPSWD @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.toolbar_main,this)
        setPadding(0,0,0,0)
        setContentInsetsAbsolute(0,0)
        invalidate()
    }
}