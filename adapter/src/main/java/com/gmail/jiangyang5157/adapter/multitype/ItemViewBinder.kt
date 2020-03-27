package com.gmail.jiangyang5157.adapter.multitype

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * If you want a LayoutInflater parameter, use ItemViewBinder
 *
 * Example:

```
data class Foo(
    val value: String
)

class FooDelegate: ItemViewBinder<Foo, FooDelegate.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.foo_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Foo) {
        holder.fooViewText.text = item.value
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val fooViewText: TextView = itemView.findViewById(R.id.fv_value)
    }
}
```
 */
abstract class ItemViewBinder<T, VH : RecyclerView.ViewHolder> : ItemViewDelegate<T, VH>() {

    final override fun onCreateViewHolder(context: Context, parent: ViewGroup): VH {
        return onCreateViewHolder(LayoutInflater.from(context), parent)
    }

    abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH
}
