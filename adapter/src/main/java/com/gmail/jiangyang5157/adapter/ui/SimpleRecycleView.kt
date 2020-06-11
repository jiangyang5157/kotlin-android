package com.gmail.jiangyang5157.adapter.ui

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.recyclerview.widget.RecyclerView
import com.gmail.jiangyang5157.adapter.multitype.SimpleRecycleViewAdapter

/**
 * see [init]
 */
abstract class SimpleRecycleView : RecyclerView {

    abstract val recycleViewAdapter: SimpleRecycleViewAdapter

    constructor(context: Context) :
        super(context)

    constructor(context: Context, attrs: AttributeSet?) :
        super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

    open fun init() {
        adapter = recycleViewAdapter
    }

    fun getItems(): List<Any> {
        return recycleViewAdapter.items
    }

    fun addItem(item: Any, position: Int = recycleViewAdapter.items.size): SimpleRecycleView {
        recycleViewAdapter.addItem(item, position)
        return this
    }

    fun addItems(
      items: List<Any>,
      position: Int = recycleViewAdapter.items.size
    ): SimpleRecycleView {
        recycleViewAdapter.addItems(items, position)
        return this
    }

    fun removeAllItems(): SimpleRecycleView {
        recycleViewAdapter.removeAllItems()
        return this
    }

    fun removeItem(position: Int): Any? {
        return recycleViewAdapter.removeItem(position)
    }

    fun removeItems(positionStart: Int, itemCount: Int): SimpleRecycleView {
        recycleViewAdapter.removeItems(positionStart, itemCount)
        return this
    }

    fun updateItem(item: Any, position: Int): SimpleRecycleView {
        recycleViewAdapter.updateItem(item, position)
        return this
    }

    fun updateItems(items: List<Any>): SimpleRecycleView {
        recycleViewAdapter.updateItems(items)
        return this
    }

    fun moveItem(fromPosition: Int, toPosition: Int): SimpleRecycleView {
        recycleViewAdapter.moveItem(fromPosition, toPosition)
        return this
    }

    fun swapItems(positionA: Int, positionB: Int): SimpleRecycleView {
        recycleViewAdapter.swapItems(positionA, positionB)
        return this
    }
}
