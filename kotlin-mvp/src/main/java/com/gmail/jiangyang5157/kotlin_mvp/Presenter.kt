package com.gmail.jiangyang5157.kotlin_mvp

import java.lang.ref.SoftReference

/**
 * Created by Yang Jiang on September 09, 2017
 */
open class Presenter<V : MvpView> : MvpPresenter<V> {

    private var mViewRef: SoftReference<V>? = null

    val view: V?
        get() = mViewRef?.get()

    override fun attachView(view: V) {
        mViewRef?.clear()
        mViewRef = SoftReference(view)
    }

    override fun detachView() {
        mViewRef?.clear()
        mViewRef = null
    }
}