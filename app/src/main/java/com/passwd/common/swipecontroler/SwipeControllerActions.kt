package com.passwd.common.swipecontroler

abstract class SwipeControllerActions {
    open fun onLeftClicked(position: Int) {}

    open fun onRightClicked(position: Int) {}
}