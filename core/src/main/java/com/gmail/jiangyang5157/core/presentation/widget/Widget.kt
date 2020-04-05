package com.gmail.jiangyang5157.core.presentation.widget

import android.content.Context
import android.view.View
import androidx.annotation.NonNull

interface Widget {

    fun view(): View

    interface Builder<T : Widget> {

        fun build(@NonNull context: Context): T
    }
}

