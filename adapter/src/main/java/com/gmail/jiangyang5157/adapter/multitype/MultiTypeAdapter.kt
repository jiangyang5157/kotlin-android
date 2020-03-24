package com.gmail.jiangyang5157.adapter.multitype

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.jiangyang5157.adapter.SimpleRecycleViewAdapter
import kotlin.reflect.KClass

open class MultiTypeAdapter @JvmOverloads constructor(
    items: List<Any> = emptyList(),
    open val initialCapacity: Int = 0,
    open var types: Types = MutableTypes(initialCapacity)
) : SimpleRecycleViewAdapter(items) {

    /**
     * Registers a type class and its item view delegate. If you have registered the class,
     * it will override the original delegate(s).
     *
     * The method is non-thread-safe so that you should not use it in concurrent operation.
     * The method should not be called after [RecyclerView.setAdapter], or you have to call the setAdapter again.
     *
     * @param clazz the class of a item
     * @param delegate the item view delegate
     * @param T the item data type
     * */
    fun <T> register(clazz: Class<T>, delegate: ItemViewDelegate<T, *>): MultiTypeAdapter {
        unregisterAllTypesIfNeeded(clazz)
        return register(
            Type(
                clazz,
                delegate
            )
        )
    }

    fun <T> register(clazz: Class<T>, binder: ItemViewBinder<T, *>): MultiTypeAdapter {
        return register(clazz, binder as ItemViewDelegate<T, *>)
    }

    fun <T : Any> register(clazz: KClass<T>, delegate: ItemViewDelegate<T, *>): MultiTypeAdapter {
        return register(clazz.java, delegate)
    }

    fun <T : Any> register(clazz: KClass<T>, binder: ItemViewBinder<T, *>): MultiTypeAdapter {
        return register(clazz, binder as ItemViewDelegate<T, *>)
    }

    internal fun <T> register(type: Type<T>): MultiTypeAdapter {
        types.register(type)
        type.delegate._adapter = this
        return this
    }

    override fun getItemViewType(position: Int): Int {
        return indexInTypesOf(items[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        indexViewType: Int
    ): RecyclerView.ViewHolder {
        return types.getType<Any>(indexViewType).delegate.onCreateViewHolder(parent.context, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder(holder, position, emptyList())
    }

    /**
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * given item in the items data set.
     * @param item The item within the MultiTypeAdapter's items data set.
     * @param payloads A non-null list of merged payloads. Can be empty list if requires full update. If the payloads list is not empty, the ViewHolder is currently bound to old data and
     */
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        val item = items[position]
        getOutDelegateByViewHolder(holder).onBindViewHolder(holder, item, payloads)
    }

    override fun getItemCount(): Int = items.size

    /**
     * @return the stable ID of the item
     * @see RecyclerView.Adapter.setHasStableIds
     */
    override fun getItemId(position: Int): Long {
        val item = items[position]
        val itemViewType = getItemViewType(position)
        return types.getType<Any>(itemViewType).delegate.getItemId(item)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        getOutDelegateByViewHolder(holder).onViewRecycled(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        getOutDelegateByViewHolder(holder).onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        getOutDelegateByViewHolder(holder).onViewDetachedFromWindow(holder)
    }

    /**
     * @return True if the View should be recycled, false otherwise. Note that if this method
     * returns `true`, RecyclerView *will ignore* the transient state of
     * the View and recycle it regardless. If this method returns `false`,
     * RecyclerView will check the View's transient state again before giving a final decision.
     * Default implementation returns false.
     */
    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return getOutDelegateByViewHolder(holder).onFailedToRecycleView(holder)
    }

    private fun getOutDelegateByViewHolder(holder: RecyclerView.ViewHolder): ItemViewDelegate<Any, RecyclerView.ViewHolder> {
        @Suppress("UNCHECKED_CAST")
        return types.getType<Any>(holder.itemViewType).delegate as ItemViewDelegate<Any, RecyclerView.ViewHolder>
    }

    @Throws(IllegalStateException::class)
    internal fun indexInTypesOf(item: Any): Int {
        val index = types.firstIndexOf(item.javaClass)
        if (index != -1) {
            return index
        }
        throw IllegalStateException("Have you registered the ${item.javaClass.name} type and its delegate or binder?")
    }

    private fun unregisterAllTypesIfNeeded(clazz: Class<*>) {
        if (types.unregister(clazz)) {
            Log.w(TAG, "The type ${clazz.simpleName} you originally registered is now overwritten.")
        }
    }

    companion object {
        private const val TAG = "MultiTypeAdapter"
    }
}
