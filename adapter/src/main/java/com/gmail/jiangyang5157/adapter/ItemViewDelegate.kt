package com.gmail.jiangyang5157.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewDelegate<T, VH : RecyclerView.ViewHolder> {

    @Suppress("PropertyName")
    internal var _adapter: MultiTypeAdapter? = null

    val adapter: MultiTypeAdapter
        get() {
            if (_adapter == null) {
                throw IllegalStateException(
                    "This $this has not been attached to MultiTypeAdapter yet. " +
                        "You should not call the method before registering the delegate."
                )
            }
            return _adapter!!
        }

    abstract fun onCreateViewHolder(context: Context, parent: ViewGroup): VH

    abstract fun onBindViewHolder(holder: VH, item: T)

    /**
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * given item in the items data set.
     * @param item The item within the MultiTypeAdapter's items data set.
     * @param payloads A non-null list of merged payloads. Can be empty list if requires full update. If the payloads list is not empty, the ViewHolder is currently bound to old data and
     */
    open fun onBindViewHolder(holder: VH, item: T, payloads: List<Any>) {
        onBindViewHolder(holder, item)
    }

    fun getPosition(holder: RecyclerView.ViewHolder): Int {
        return holder.adapterPosition
    }

    /**
     * @return the stable ID of the item
     * @see RecyclerView.Adapter.setHasStableIds
     */
    open fun getItemId(item: T): Long = RecyclerView.NO_ID

    open fun onViewRecycled(holder: VH) {}

    open fun onViewAttachedToWindow(holder: VH) {}

    open fun onViewDetachedFromWindow(holder: VH) {}

    /**
     * @return True if the View should be recycled, false otherwise. Note that if this method
     * returns `true`, RecyclerView *will ignore* the transient state of
     * the View and recycle it regardless. If this method returns `false`,
     * RecyclerView will check the View's transient state again before giving a final decision.
     * Default implementation returns false.
     */
    open fun onFailedToRecycleView(holder: VH): Boolean {
        return false
    }
}
