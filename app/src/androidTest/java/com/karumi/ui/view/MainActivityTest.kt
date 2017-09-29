package com.karumi.ui.view

import com.github.salomonbrys.kodein.Kodein.Module
import org.junit.Test

class MainActivityTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        val activity = startActivity()

        record(activity)
    }

    override val testDependencies =
        Module(allowSilentOverride = true) {
        }
}