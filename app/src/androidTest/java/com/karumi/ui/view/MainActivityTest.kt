package com.karumi.ui.view

import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import com.karumi.mockito.MockitoExtensions.on
import org.junit.Test
import org.mockito.Mock

class MainActivityTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Mock private lateinit var repository: SuperHeroRepository

    @Test
    fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        givenThereAreNoSuperHeroes()

        val activity = startActivity()

        compareScreenshot(activity)
    }

    private fun givenThereAreSomeSuperHeroes(
            numberOfSuperHeroes: Int = 1,
            avengers: Boolean = false): List<SuperHero> {
        val superHeroes = IntRange(0, numberOfSuperHeroes - 1).map { id ->
            val superHeroName = "SuperHero - " + id
            val superHeroDescription = "Description Super Hero - " + id
            SuperHero(superHeroName, null, avengers,
                    superHeroDescription)
        }

        on(repository.getAllSuperHeroes()).thenReturn(superHeroes)
        return superHeroes
    }

    private fun givenThereAreNoSuperHeroes() {
        on(repository.getAllSuperHeroes()).thenReturn(emptyList())
    }

    override val testDependencies = Module(allowSilentOverride = true) {
        bind<SuperHeroRepository>() with instance(repository)
    }
}