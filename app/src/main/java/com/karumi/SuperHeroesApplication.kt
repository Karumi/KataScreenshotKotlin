package com.karumi

import android.app.Application
import android.content.Context
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.conf.ConfigurableKodein

class SuperHeroesApplication : Application(), KodeinAware {
    override val kodein = ConfigurableKodein(mutable = true)
    var overrideModule: Module? = null

    override fun onCreate() {
        super.onCreate()
        resetInjection()
    }

    fun appDependencies(app: SuperHeroesApplication): Module {
        return Module(allowSilentOverride = true) {
        }
    }

    fun addModule(activityModules: Module) {
        kodein.addImport(activityModules)
        if (overrideModule != null) {
            kodein.addImport(overrideModule!!, true)
        }
    }

    fun resetInjection() {
        kodein.clear()
        kodein.addImport(appDependencies(this), true)
    }
}

fun Context.asApp() = this.applicationContext as SuperHeroesApplication