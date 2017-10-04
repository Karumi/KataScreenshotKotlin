package com.karumi.domain.usecase

import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import org.funktionale.either.Either

class GetSuperHeroes(superHeroesRepository: SuperHeroRepository) {

    private val mockSuperHero = SuperHero(name = "Super Peter",
        description = "He is a superHero, write code faster than flash",
        isAvenger = true,
        photo = "https://avatars1.githubusercontent.com/u/4030704")

    operator fun invoke(): Either<Error, List<SuperHero>> =
        Either.right(arrayListOf(mockSuperHero))
}