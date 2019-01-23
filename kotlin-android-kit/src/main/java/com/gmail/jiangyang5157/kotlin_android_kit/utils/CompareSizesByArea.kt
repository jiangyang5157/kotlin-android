package com.gmail.jiangyang5157.kotlin_android_kit.utils

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Size
import java.lang.Long.signum

/**
 * Created by Yang Jiang on April 30, 2018
 */
class CompareSizesByArea : Comparator<Size> {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun compare(lhs: Size, rhs: Size) =
            signum(lhs.width.toLong() * lhs.height - rhs.width.toLong() * rhs.height)

}