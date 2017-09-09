package com.gmail.jiangyang5157.kotlin_mvp

/**
 * Created by Yang Jiang on September 09, 2017
 */
interface MvpPresenter<in V : MvpView> {

    fun attachView(view: V)

    fun detachView()
}
