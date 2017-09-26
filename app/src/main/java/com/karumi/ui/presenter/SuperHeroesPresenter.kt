package com.karumi.ui.presenter


class SuperHeroesPresenter(val view: View) {

    fun create() {
        view.hideLoading()
    }

    interface View {
        fun hideLoading()
    }
}