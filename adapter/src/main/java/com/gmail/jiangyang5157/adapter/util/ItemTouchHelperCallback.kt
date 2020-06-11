package com.gmail.jiangyang5157.adapter.util

import android.graphics.Canvas
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class ItemTouchHelperCallback(
  private val adapter: ItemTouchHelperAdapter,
  private val alpha: Float = 1.0f,
  private val longPressDragEnabled: Boolean = false,
  private val itemViewSwipeEnabled: Boolean = false
) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return longPressDragEnabled
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return itemViewSwipeEnabled
    }

    override fun getMovementFlags(
      recyclerView: RecyclerView,
      viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (recyclerView.layoutManager is GridLayoutManager) {
            val dragFlags =
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            makeMovementFlags(dragFlags, swipeFlags)
        } else {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    override fun onMove(
      recyclerView: RecyclerView,
      source: RecyclerView.ViewHolder,
      target: RecyclerView.ViewHolder
    ): Boolean {
        if (source.itemViewType != target.itemViewType) {
            return false
        }
        adapter.onItemMove(source.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(
      viewHolder: RecyclerView.ViewHolder,
      i: Int
    ) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
      canvas: Canvas,
      recyclerView: RecyclerView,
      viewHolder: RecyclerView.ViewHolder,
      dX: Float,
      dY: Float,
      actionState: Int,
      isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            viewHolder.itemView.alpha = alpha - abs(dX) / viewHolder.itemView.width.toFloat()
            viewHolder.itemView.translationX = dX
        } else {
            super.onChildDraw(
                canvas,
                recyclerView,
                viewHolder,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }

    override fun onSelectedChanged(
      viewHolder: RecyclerView.ViewHolder?,
      actionState: Int
    ) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is ItemTouchHelperViewHolder) {
                viewHolder.onItemSelected()
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(
      recyclerView: RecyclerView,
      viewHolder: RecyclerView.ViewHolder
    ) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.alpha = alpha
        if (viewHolder is ItemTouchHelperViewHolder) {
            viewHolder.onItemClear()
        }
    }
}
