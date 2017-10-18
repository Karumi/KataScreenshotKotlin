package com.karumi.ui.presenter

import android.util.Log
import co.metalab.asyncawait.async
import com.karumi.common.weak
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroes
import com.karumi.ui.LifecycleSubscriber
import org.funktionale.either.Either.Left
import org.funktionale.either.Either.Right

class SuperHeroesPresenter(
        view: View,
        private val getSuperHeroes: GetSuperHeroes) : LifecycleSubscriber {

    companion object {
        private val TAG: String = "SuperHeroesPresenter"
    }

    private val view: View? by weak(view)

    override fun update() {
        view?.showLoading()
        refreshSuperHeroes()
    }

    private fun refreshSuperHeroes() = async {
        val result = await { getSuperHeroes() }
        view?.hideLoading()
        when {
            result is Right && result.r.isEmpty() -> view?.showEmptyCase()
            result is Right && result.r.isNotEmpty() -> view?.showSuperHeroes(result.r)
            result is Left -> Log.d(TAG, "There was an error")
        }
    }

    fun onSuperHeroClicked(superHero: SuperHero) = view?.openDetail(superHero.name)

    interface View {
        fun hideLoading()
        fun showSuperHeroes(superHeroes: List<SuperHero>)
        fun showLoading()
        fun showEmptyCase()
        fun openDetail(name: String)

    }
}