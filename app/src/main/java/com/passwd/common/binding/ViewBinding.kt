package com.passwd.common.binding

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import androidx.databinding.BindingAdapter

class ViewBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("fadeInAnimation")
        fun setupAnimationFadeIn(view: View, duration: Long) {
            val fadeIn: Animation = AlphaAnimation(0f, 1f)
            fadeIn.interpolator = DecelerateInterpolator()
            fadeIn.duration = duration
            view.startAnimation(fadeIn)
        }
    }
}