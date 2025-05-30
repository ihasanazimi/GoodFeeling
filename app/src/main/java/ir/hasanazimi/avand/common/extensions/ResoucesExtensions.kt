package ir.hasanazimi.avand.common.extensions

import android.R
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

// Extension function to determine if a color is light or dark
fun Int.isColorLight(): Boolean {
    val darkness = 1 - (0.299 * Color.red(this) +
            0.587 * Color.green(this) +
            0.114 * Color.blue(this)) / 255
    return darkness < 0.5
}


fun Context.drawable(@DrawableRes drawableRes: Int) = ResourcesCompat.getDrawable(resources, drawableRes, theme)


fun getColoredDrawable(context:Context,drawableResID: Int, colorResID: Int, mode: PorterDuff.Mode): Drawable? {
    val drawable = ContextCompat.getDrawable(context, drawableResID)?.mutate()
    val colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, colorResID), mode)
    drawable?.colorFilter = colorFilter
    return drawable
}

fun getColoredDrawable(context: Context,drawable: Drawable, colorResID: Int, mode: PorterDuff.Mode): Drawable? {
    val colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, colorResID), mode)
    drawable.colorFilter = colorFilter
    return drawable
}



fun parseHexColorResource(context: Context, hexColor: String): Int {
    // Parse hex color to get the color integer
    val colorString = if (hexColor.startsWith("#")) hexColor.substring(1) else hexColor
    // Convert the color string to a color integer
    val colorInt = try { Color.parseColor("#$colorString")
    } catch (e: IllegalArgumentException) {
        // Handle invalid color format
        ContextCompat.getColor(context, R.color.transparent)
    }
    // Return a color resource using the color integer
    return context.resources.getColor(colorInt, context.theme)
}
