package com.karumi.ui.presenter

import android.util.Log
import co.metalab.asyncawait.async
import com.karumi.common.weak
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroByName
import com.karumi.ui.LifecycleSubscriber
import org.funktionale.either.Either.Left
import org.funktionale.either.Either.Right

class SuperHeroDetailPresenter(
        view: View,
        private val getSuperHeroByName: GetSuperHeroByName) :
        LifecycleSubscriber {

    companion object {
        private val TAG = "SuperHeroDetail"
    }

    private val view: View? by weak(view)

    private lateinit var name: String

    fun preparePresenter(name: String?) {
        if (name != null) {
            this.name = name
        } else {
            view?.close()
        }
    }

    override fun update() {
        view?.showLoading()
        refreshSuperHeroes()
    }

    private fun refreshSuperHeroes() = async {
        val result = await { getSuperHeroByName(name) }
        view?.hideLoading()
        when (result) {
            is Right -> view?.showSuperHero(result.r)
            is Left -> Log.d(SuperHeroDetailPresenter.TAG, "There was an error")
        }
    }

    interface View {
        fun close()
        fun showLoading()
        fun hideLoading()
        fun showSuperHero(superHero: SuperHero)
    }

}