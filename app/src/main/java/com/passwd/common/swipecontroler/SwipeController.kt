package com.passwd.common.swipecontroler

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min

private sealed class ButtonState {
    object Gone : ButtonState()
    object LeftVisible : ButtonState()
    object RightVisible : ButtonState()
}

class SwipeController(
        private val properties: SwipeControllerProperties
) : ItemTouchHelper.Callback() {

    private var swipeBack = false
    private var buttonShowedState: ButtonState = ButtonState.Gone
    private var buttonInstance: RectF? = null
    private var currentItemViewHolder: RecyclerView.ViewHolder? = null

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, LEFT or RIGHT)

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = buttonShowedState !== ButtonState.Gone
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(c: Canvas,
                             recyclerView: RecyclerView,
                             viewHolder: RecyclerView.ViewHolder,
                             dX: Float, dY: Float,
                             actionState: Int,
                             isCurrentlyActive: Boolean) {
        var dXValue = dX

        if (actionState == ACTION_STATE_SWIPE)
            when (buttonShowedState) {
                ButtonState.Gone -> {
                    setTouchListener(c, recyclerView, viewHolder, dXValue, dY, actionState, isCurrentlyActive)
                    super.onChildDraw(c, recyclerView, viewHolder, dXValue, dY, actionState, isCurrentlyActive)
                }
                ButtonState.LeftVisible -> {
                    dXValue = max(dX, buttonWidth)
                    super.onChildDraw(c, recyclerView, viewHolder, dXValue, dY, actionState, isCurrentlyActive)
                }
                ButtonState.RightVisible -> {
                    dXValue = min(dX, -buttonWidth)
                    super.onChildDraw(c, recyclerView, viewHolder, dXValue, dY, actionState, isCurrentlyActive)
                }
            }

        currentItemViewHolder = viewHolder
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(c: Canvas,
                                 recyclerView: RecyclerView,
                                 viewHolder: RecyclerView.ViewHolder,
                                 dX: Float, dY: Float,
                                 actionState: Int,
                                 isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener { _, event ->
            swipeBack = event.action == MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_UP

            if (swipeBack) {
                if (dX < -buttonWidth)
                    buttonShowedState = ButtonState.RightVisible
                else if (dX > buttonWidth) buttonShowedState = ButtonState.LeftVisible

                if (buttonShowedState !== ButtonState.Gone) {
                    setTouchDownListener(c, recyclerView, viewHolder, dY, actionState, isCurrentlyActive)
                    setItemsClickable(recyclerView, false)
                }
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchDownListener(c: Canvas,
                                     recyclerView: RecyclerView,
                                     viewHolder: RecyclerView.ViewHolder,
                                     dY: Float,
                                     actionState: Int,
                                     isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                setTouchUpListener(c, recyclerView, viewHolder, dY, actionState, isCurrentlyActive)
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchUpListener(c: Canvas,
                                   recyclerView: RecyclerView,
                                   viewHolder: RecyclerView.ViewHolder,
                                   dY: Float,
                                   actionState: Int,
                                   isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                super@SwipeController.onChildDraw(c, recyclerView, viewHolder, 0f, dY, actionState, isCurrentlyActive)
                recyclerView.setOnTouchListener { _, _ -> false }
                setItemsClickable(recyclerView, true)
                swipeBack = false

                if (buttonInstance?.contains(event.x, event.y) == true) {
                    when (buttonShowedState) {
                        ButtonState.LeftVisible -> properties.leftButton.action.invoke(viewHolder.adapterPosition)
                        ButtonState.RightVisible -> properties.rightButton.action.invoke(viewHolder.adapterPosition)
                    }
                }
                buttonShowedState = ButtonState.Gone
                currentItemViewHolder = null
            }
            false
        }
    }

    private fun setItemsClickable(recyclerView: RecyclerView, isClickable: Boolean) {
        for (i in 0 until recyclerView.childCount) {
            recyclerView.getChildAt(i).isClickable = isClickable
        }
    }

    private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {
        val buttonWidthWithoutPadding = buttonWidth - DEFAULT_MARGIN
        val corners = 16f

        val itemView = viewHolder.itemView
        val p = Paint()

        val top = itemView.top.toFloat() + DEFAULT_MARGIN
        val bottom = itemView.bottom.toFloat() - DEFAULT_MARGIN

        val leftToLeftButton = itemView.left.toFloat() + DEFAULT_MARGIN
        val rightToLeftButton = itemView.left + buttonWidthWithoutPadding

        val rightToRightButton = itemView.right.toFloat() - DEFAULT_MARGIN
        val leftToRightButton = itemView.right - buttonWidthWithoutPadding

        val leftButton = RectF(
                leftToLeftButton,
                top,
                rightToLeftButton,
                bottom
        )
        val context = viewHolder.itemView.context

        p.color = ContextCompat.getColor(context, properties.leftButton.color)
        c.drawRoundRect(leftButton, corners, corners, p)
        drawText(context.getString(properties.leftButton.text), c, leftButton, p)

        val rightButton = RectF(
                leftToRightButton,
                top,
                rightToRightButton,
                bottom
        )

        p.color = ContextCompat.getColor(context, properties.rightButton.color)
        c.drawRoundRect(rightButton, corners, corners, p)
        drawText(context.getString(properties.rightButton.text), c, rightButton, p)

        buttonInstance = null
        when (buttonShowedState) {
            ButtonState.LeftVisible -> buttonInstance = leftButton
            ButtonState.RightVisible -> buttonInstance = rightButton
        }
    }

    private fun drawText(text: String, c: Canvas, button: RectF, p: Paint) {
        val textSize = 30f
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize

        val textWidth = p.measureText(text)
        c.drawText(text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2, p)
    }

    fun onDraw(c: Canvas) {
        currentItemViewHolder?.let { drawButtons(c, it) }
    }

    companion object {
        private const val buttonWidth = 260f
        const val DEFAULT_MARGIN = 20F
    }
}