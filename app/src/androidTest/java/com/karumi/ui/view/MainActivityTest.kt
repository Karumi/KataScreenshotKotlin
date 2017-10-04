package com.karumi.ui.view

import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.karumi.data.repository.SuperHeroRepository
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class MainActivityTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Mock private lateinit var repository: SuperHeroRepository

    @Test
    fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        givenEmptySuperHeroes()

        val activity = startActivity()

        record(activity)
    }

    private fun givenEmptySuperHeroes() {
        `when`(repository.getAllSuperHeroes()).thenReturn(emptyList())
    }

    override val testDependencies =
        Module(allowSilentOverride = true) {
            bind<SuperHeroRepository>() with instance(repository)
        }
}