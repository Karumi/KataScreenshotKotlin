package com.karumi.ui.presenter

import android.util.Log
import co.metalab.asyncawait.async
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroByName
import com.karumi.ui.LifecycleSubscriber
import org.funktionale.either.Either.Left
import org.funktionale.either.Either.Right
import java.lang.ref.WeakReference

class SuperHeroDetailPresenter(view: View, getSuperHeroByName: GetSuperHeroByName) :
    LifecycleSubscriber {
    companion object {
        private val TAG = "SuperHeroDetail"
    }

    private val getSuperHeroByName: GetSuperHeroByName = getSuperHeroByName
    private val viewWeak: WeakReference<View> = WeakReference(view)
    private fun view() = viewWeak.get()

    private lateinit var name: String

    fun preparePresenter(name: String?) {
        if (name != null) {
            this.name = name
        } else {
            view()?.close()
        }
    }

    override fun initialize() {
    }

    override fun update() {
        view()?.showLoading()
        refreshSuperHeroes()
    }

    private fun refreshSuperHeroes() {
        async {
            val result = await { getSuperHeroByName(name) }
            view()?.hideLoading()
            when {
                result is Right -> view()?.showSuperHero(result.r)
                result is Left -> Log.d(SuperHeroDetailPresenter.TAG, "an error happens.")
            }
        }
    }

    interface View {
        fun close()
        fun showLoading()
        fun hideLoading()
        fun showSuperHero(superHero: SuperHero)
    }

}