package com.gmail.jiangyang5157.kotlin_kit.model

/**
 * Created by Yang Jiang on April 18, 2018
 */
interface BaseView<in T : BasePresenter> {

    fun setPresenter(presenter: T)

}