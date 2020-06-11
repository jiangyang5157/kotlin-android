package com.gmail.jiangyang5157.adapter.multitype

import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class SimpleRecycleViewAdapter constructor(
  open var items: List<Any> = emptyList()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun addItem(item: Any, position: Int = this.items.size) {
        if (this.items !is MutableList) {
            this.items = this.items.toMutableList()
        }
        if (this.items.size >= position) {
            (this.items as MutableList<Any>).add(position, item)
            notifyItemInserted(position)
        }
    }

    fun addItems(items: List<Any>, position: Int = this.items.size) {
        if (this.items !is MutableList) {
            this.items = this.items.toMutableList()
        }
        if (this.items.size >= position) {
            (this.items as MutableList<Any>).addAll(position, items)
            notifyItemRangeInserted(position, items.size)
        }
    }

    fun removeAllItems() {
        this.items = emptyList()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int): Any? {
        if (this.items !is MutableList) {
            this.items = this.items.toMutableList()
        }
        return if (this.items.size > position) {
            val item = (this.items as MutableList<Any>).removeAt(position)
            notifyItemRemoved(position)
            item
        } else {
            null
        }
    }

    fun removeItems(positionStart: Int, itemCount: Int) {
        if (this.items !is MutableList) {
            this.items = this.items.toMutableList()
        }
        if (this.items.size > positionStart) {
            val removedCount = if (positionStart + itemCount <= this.items.size) {
                positionStart + itemCount
            } else {
                this.items.size
            }
            for (i in 0 until removedCount) {
                val item = (this.items as MutableList<Any>).removeAt(positionStart + i)
            }
            notifyItemRangeRemoved(positionStart, itemCount)
        }
    }

    fun updateItem(item: Any, position: Int) {
        if (this.items !is MutableList) {
            this.items = this.items.toMutableList()
        }
        if (this.items.size > position) {
            (this.items as MutableList<Any>)[position] = item
            notifyItemChanged(position)
        }
    }

    fun updateItems(items: List<Any>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (this.items !is MutableList) {
            this.items = this.items.toMutableList()
        }
        if (this.items.size > fromPosition &&
            this.items.size > toPosition
        ) {
            val item = (this.items as MutableList<Any>).removeAt(fromPosition)
            (this.items as MutableList<Any>).add(toPosition, item)
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    fun swapItems(positionA: Int, positionB: Int) {
        if (this.items.size > positionA && this.items.size > positionB) {
            Collections.swap(this.items, positionA, positionB)
            notifyDataSetChanged()
        }
    }
}
