package com.karumi.ui.presenter

import com.karumi.ui.LifecycleReceiver

class SuperHeroesPresenter(val view: View) : LifecycleReceiver {
    override fun initialize() {
        view.hideLoading()
    }

    override fun update() {

    }

    interface View {
        fun hideLoading()
    }
}