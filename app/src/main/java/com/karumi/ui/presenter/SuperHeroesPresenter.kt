package com.karumi.ui.presenter

import co.metalab.asyncawait.async
import com.karumi.ui.LifecycleSubscriber
import com.karumi.ui.domain.Result.Left
import com.karumi.ui.domain.Result.Right
import com.karumi.ui.domain.model.SuperHero
import com.karumi.ui.domain.usecase.GetSuperHeroes
import java.lang.ref.WeakReference

class SuperHeroesPresenter(view: View, getSuperHeroes: GetSuperHeroes) : LifecycleSubscriber {
    private val viewWeak: WeakReference<View> = WeakReference(view)
    private val getSuperHeroes: GetSuperHeroes = getSuperHeroes

    private fun view() = viewWeak.get()

    override fun initialize() {
    }

    override fun update() {
        refreshSuperHeroes()
    }

    private fun refreshSuperHeroes() {
        async {
            val result = await { getSuperHeroes.get() }
            view()?.hideLoading()
            when (result) {
                is Right -> view()?.showSuperHeroes(result.value)
                is Left -> TODO("Show error")
            }
        }
    }

    interface View {
        fun hideLoading()
        fun showSuperHeroes(superHeroes: List<SuperHero>)
    }

    fun onSuperHeroClicked(superHero: SuperHero) {
    }
}