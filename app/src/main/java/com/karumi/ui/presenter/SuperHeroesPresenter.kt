package com.karumi.ui.presenter

import android.util.Log
import co.metalab.asyncawait.async
import com.karumi.ui.LifecycleSubscriber
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroes
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
        refreshSuperHeroes()
    }

    private fun refreshSuperHeroes() {
        async {
            val result = await { getSuperHeroes() }
            view()?.hideLoading()
            when (result) {
                is Right -> view()?.showSuperHeroes(result.r)
                is Left -> Log.d(TAG, "an error happens.")
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