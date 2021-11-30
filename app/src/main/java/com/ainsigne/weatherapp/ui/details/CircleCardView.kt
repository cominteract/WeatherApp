package com.ainsigne.weatherapp.ui.details

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import kotlin.math.min

/**
 * A custom [CardView] that does not allow the radius to exceed the minimum of width/2 or height/2
 * On some versions, this causes a strange behavior so we use this to stay safe.
 */
//source https://github.com/nikhilpanju/FabFilter/blob/master/app/src/main/java/com/nikhilpanju/fabfilter/views/CircleCardView.kt
class CircleCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    /**
     * set radius and update computation when necessary
     * @param radius as [Float] to be computed or updated
     */
    override fun setRadius(radius: Float) {
        super.setRadius(
            if (radius > height / 2 || radius > width / 2) min(height, width) / 2f
            else radius
        )
    }
}