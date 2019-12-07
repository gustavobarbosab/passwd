package com.passwd.common.widget.colorselector

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.HorizontalScrollView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.passwd.common.extension.toPx

class ColorSelector @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : HorizontalScrollView(context, attrs, defStyleAttr) {

    var containerLL: LinearLayoutCompat = LinearLayoutCompat(context)
        .apply {
            orientation = LinearLayoutCompat.HORIZONTAL
            layoutParams = LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        }

    init {
        this.addView(containerLL)
    }

    fun setupColorList(colors: Array<Int>) {
        val params = createItemLayoutParams()

        colors.forEach {
            val itemView = createColorItemView(it)
            containerLL.addView(itemView, params)
        }
    }

    private fun createItemLayoutParams(): LinearLayoutCompat.LayoutParams {
        val size = context.toPx(25F).toInt()
        val margin = context.toPx(2F).toInt()

        val params = LinearLayoutCompat.LayoutParams(size, size)
        params.setMargins(margin, 0, margin, 0)
        return params
    }

    private fun createColorItemView(color: Int): View {
        val colorItem = View(context)
        colorItem.background = getDrawableColor(color)
        return colorItem
    }

    private fun getDrawableColor(colorView: Int): Drawable {
        val array = IntArray(2)
        array[0] = ContextCompat.getColor(context, colorView)
        array[1] = ContextCompat.getColor(context, colorView)
        return GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, array).apply {
            shape = GradientDrawable.OVAL
        }
    }
}