package com.passwd.common.swipecontroler

import android.graphics.Canvas
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeControllerBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("addSwipeController")
        fun setupRecyclerWithSwipe(recyclerView: RecyclerView,
                                   swipeController: SwipeController) {
            val itemTouchHelper = ItemTouchHelper(swipeController)
            itemTouchHelper.attachToRecyclerView(recyclerView)

            recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    swipeController.onDraw(c)
                }
            })
        }
    }
}