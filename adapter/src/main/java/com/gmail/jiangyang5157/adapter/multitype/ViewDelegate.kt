package com.gmail.jiangyang5157.adapter.multitype

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * If you are using a custom View instead of XML resource, use ViewDelegate.
 *
 * Example:

```
data class Foo(
    val value: String
)

class FooDelegate : ViewDelegate<Foo, FooView>() {

    override fun onCreateView(context: Context): FooView {
        return FooView(context).apply { layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT) }
    }

    override fun onBindView(view: FooView, item: Foo) {
        view.setText(item.value)
    }
}
```
 */
abstract class ViewDelegate<T, V : View> : ItemViewDelegate<T, ViewDelegate.Holder<V>>() {

    abstract fun onCreateView(context: Context): V

    abstract fun onBindView(view: V, item: T)

    // Override this function if you need a parent ViewGroup
    open fun onCreateView(context: Context, parent: ViewGroup): V {
        return onCreateView(context)
    }

    // Override this function if you need a ViewHolder
    open fun onBindView(holder: Holder<V>, view: V, item: T) {
        onBindView(view, item)
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): Holder<V> {
        return Holder(onCreateView(context, parent))
    }

    override fun onBindViewHolder(holder: Holder<V>, item: T) =
        onBindView(holder, holder.view, item)

    class Holder<V : View>(val view: V) : RecyclerView.ViewHolder(view)

    protected val View.layoutPosition
        get() = recyclerLayoutParams.viewLayoutPosition

    protected val View.adapterPosition
        get() = recyclerLayoutParams.viewAdapterPosition

    private val View.recyclerLayoutParams
        get() = layoutParams as RecyclerView.LayoutParams
}
