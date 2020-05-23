package com.gustavobarbosab.swipecontroller

abstract class SwipeControllerActions {
    open fun onLeftClicked(position: Int) {}

    open fun onRightClicked(position: Int) {}
}