package com.passwd.common.widget.colorselector

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.passwd.R
import com.passwd.common.extension.toPx

class ColorSelector
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : HorizontalScrollView(context, attrs, defStyleAttr) {

    var colorSelectedClickListener: (color: Int) -> Unit = {}

    private var groupContainer: RadioGroup =
            RadioGroup(context).apply {
                orientation = RadioGroup.HORIZONTAL
                layoutParams = RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT)
            }

    init {
        this.addView(groupContainer)
    }

    fun setupColorList(colors: Array<Int>) {
        val params = createItemLayoutParams()

        colors.forEach {
            val itemView = createItemView(it)
            groupContainer.addView(itemView, params)
        }

        selectFirstColor(colors.firstOrNull())
    }

    private fun selectFirstColor(firstColor: Int?) {
        firstColor?.let {
            colorSelectedClickListener(ContextCompat.getColor(context, firstColor))
            (groupContainer.getChildAt(0) as RadioButton).isChecked = true
        }
    }

    private fun createItemLayoutParams(): RadioGroup.LayoutParams {
        val size = context.toPx(ITEM_VIEW_SIZE).toInt()
        val horizontalMargin = context.toPx(HORIZONTAL_MARGIN).toInt()

        val params = RadioGroup.LayoutParams(size, size)
        params.setMargins(horizontalMargin, VERTICAL_MARGIN, horizontalMargin, VERTICAL_MARGIN)
        return params
    }

    private fun createItemView(color: Int): View =
            RadioButton(context).apply {
                setButtonDrawable(android.R.color.transparent)
                background = getItemBackground(color)
                setOnClickListener { colorSelectedClickListener(ContextCompat.getColor(context, color)) }
            }

    private fun getItemBackground(colorView: Int): Drawable {
        val default = getDefaultItemColor(colorView)
        val selected = getSelectedItemColor(colorView)

        return createStateSelector(default, selected!!)
    }

    private fun getDefaultItemColor(colorView: Int): Drawable {
        val array = IntArray(2)
        array[0] = ContextCompat.getColor(context, colorView)
        array[1] = ContextCompat.getColor(context, colorView)

        return GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, array).apply {
            shape = GradientDrawable.OVAL
        }
    }

    private fun getSelectedItemColor(colorView: Int): Drawable? =
            ContextCompat
                    .getDrawable(context, R.drawable.ic_selected)
                    ?.mutate()
                    ?.apply {
                        val color = ContextCompat.getColor(context, colorView)
                        DrawableCompat.setTint(this, color)
                    }

    private fun createStateSelector(default: Drawable, checked: Drawable): StateListDrawable =
            StateListDrawable().apply {
                setExitFadeDuration(ANIMATION_DURATION)
                addState(intArrayOf(android.R.attr.state_pressed), getRippleDrawableClick(default))
                addState(intArrayOf(android.R.attr.state_checked), checked)
                addState(intArrayOf(), default)
            }

    private fun getRippleDrawableClick(backgroundDrawable: Drawable?): RippleDrawable = RippleDrawable(getPressedState(), backgroundDrawable, null)

    private fun getPressedState(): ColorStateList = ColorStateList(arrayOf(intArrayOf(android.R.attr.state_pressed)), intArrayOf(Color.GRAY))

    companion object {
        const val ANIMATION_DURATION = 200
        const val VERTICAL_MARGIN = 0
        const val HORIZONTAL_MARGIN = 2f
        const val ITEM_VIEW_SIZE = 25f
    }
}