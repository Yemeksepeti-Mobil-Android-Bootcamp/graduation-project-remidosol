package com.remidosol.yemeksepeti_graduation_remidosol.utils.transformers

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class CubeInDepthPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.cameraDistance = 20000F

        if (position < -1) {
            view.alpha = 0F
        } else if (position <= 0) {
            view.alpha = 1F
            view.pivotX = view.width.toFloat()
            view.rotationY = 90 * abs(position)
        } else if (position <= 1) {
            view.alpha = 1F
            view.pivotX = 0F
            view.rotationY = -90 * abs(position)
        } else {
            view.alpha = 0F
        }

        if (abs(position) <= 0.5) {
            view.scaleY = Math.max(.4f, 1 - abs(position))
        } else if (abs(position) <= 1) {
            view.scaleY = Math.max(.4f, 1 - abs(position))

        }
    }
}