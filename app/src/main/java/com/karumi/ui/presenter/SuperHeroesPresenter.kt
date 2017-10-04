package com.karumi.ui.presenter

import android.util.Log
import co.metalab.asyncawait.async
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroes
import com.karumi.ui.LifecycleSubscriber
import org.funktionale.either.Either.Left
import org.funktionale.either.Either.Right
import java.lang.ref.WeakReference

class SuperHeroesPresenter(view: View, getSuperHeroes: GetSuperHeroes) : LifecycleSubscriber {
    companion object {
        private val TAG: String = "SuperHeroesPresenter"
    }

    private val viewWeak: WeakReference<View> = WeakReference(view)
    private val getSuperHeroes: GetSuperHeroes = getSuperHeroes

    private fun view() = viewWeak.get()

    override fun initialize() {
    }

    override fun update() {
        view()?.showLoading()
        refreshSuperHeroes()
    }

    private fun refreshSuperHeroes() {
        async {
            val result = await { getSuperHeroes() }
            view()?.hideLoading()
            when (result) {
                is Right -> showSuperHeroes(result.r)
                is Left -> Log.d(TAG, "an error happens.")
            }
        }
    }

    private fun showSuperHeroes(superHeroes: List<SuperHero>) {
        if (superHeroes.isEmpty()) {
            view()?.showEmptyCase()
        } else {
            view()?.showSuperHeroes(superHeroes)
        }
    }

    interface View {
        fun hideLoading()
        fun showSuperHeroes(superHeroes: List<SuperHero>)
        fun showLoading()
        fun showEmptyCase()
    }

    fun onSuperHeroClicked(superHero: SuperHero) {
    }
}