package com.gmail.jiangyang5157.core.presentation.widget

import android.content.Context
import androidx.annotation.NonNull

interface WidgetBuilder<T : Widget> {

    fun build(@NonNull context: Context): T
}
