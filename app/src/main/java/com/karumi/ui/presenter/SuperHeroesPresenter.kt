package com.karumi.ui.presenter

import com.karumi.ui.LifecycleSubscriber
import java.lang.ref.WeakReference

class SuperHeroesPresenter(view: View) : LifecycleSubscriber {
    private val viewWeak: WeakReference<View> = WeakReference(view)

    private fun view() = viewWeak.get()

    override fun initialize() {
        view()?.hideLoading()
    }

    override fun update() {

    }

    interface View {
        fun hideLoading()
    }
}