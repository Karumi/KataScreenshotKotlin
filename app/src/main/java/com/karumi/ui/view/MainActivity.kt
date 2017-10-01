package com.karumi.ui.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.karumi.R
import com.karumi.ui.LifecycleSubscriber
import com.karumi.ui.domain.model.SuperHero
import com.karumi.ui.domain.usecase.GetSuperHeroes
import com.karumi.ui.presenter.SuperHeroesPresenter
import com.karumi.ui.view.adapter.SuperHeroesAdapter
import kotlinx.android.synthetic.main.main_activity.progress_bar
import kotlinx.android.synthetic.main.main_activity.recycler_view

class MainActivity : BaseActivity(), SuperHeroesPresenter.View {
    private val presenter: SuperHeroesPresenter by injector.instance()
    private lateinit var adapter: SuperHeroesAdapter

    override fun getLayoutId(): Int = R.layout.main_activity
    override fun obtainPresenter(): LifecycleSubscriber = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeAdapter()
        initializeRecyclerView()
    }

    private fun initializeAdapter() {
        adapter = SuperHeroesAdapter(presenter)
    }

    private fun initializeRecyclerView() {
        recycler_view.setLayoutManager(LinearLayoutManager(this))
        recycler_view.setHasFixedSize(true)
        recycler_view.setAdapter(adapter)
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun showSuperHeroes(superHeroes: List<SuperHero>) {
        adapter.addAll(superHeroes)
        adapter.notifyDataSetChanged()
    }

    override val activityModules = Module(allowSilentOverride = true) {
        bind<SuperHeroesPresenter>() with provider {
            SuperHeroesPresenter(this@MainActivity,
                instance())
        }

        bind<GetSuperHeroes>() with provider { GetSuperHeroes() }
    }
}

