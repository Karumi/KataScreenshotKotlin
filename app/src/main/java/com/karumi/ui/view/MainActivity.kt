package com.karumi.ui.view

import android.os.Bundle
import android.view.View
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.karumi.R
import com.karumi.ui.presenter.SuperHeroesPresenter
import kotlinx.android.synthetic.main.main_activity.progress_bar

class MainActivity : BaseActivity(), SuperHeroesPresenter.View {

    val presenter: SuperHeroesPresenter by injector.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        presenter.create()
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun activityModules(): Module = Module(allowSilentOverride = true) {
        bind<SuperHeroesPresenter>() with provider { SuperHeroesPresenter(this@MainActivity) }
    }
}