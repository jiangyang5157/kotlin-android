package com.gmail.jiangyang5157.kotlin_android_core.utils

import android.os.Build
import android.app.ActivityManager
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue.*

/**
 * Created by Yang Jiang on July 01, 2017
 */
object DeviceUtils {

    fun sdkValidate(sdkVersion: Int): Boolean = Build.VERSION.SDK_INT >= sdkVersion

    fun glesValidate(context: Context, version: Int): Boolean
            = (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).deviceConfigurationInfo.reqGlEsVersion >= version

    /**
     * Return integer pixels. An offset conversion involves simply truncating the base value to an integer.
     */
    fun getDimensionPixelOffset(resources: Resources, resId: Int): Int = resources.getDimensionPixelOffset(resId)

    fun dp2Px(dp: Float, resources: Resources): Int = applyDimension(COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

}
