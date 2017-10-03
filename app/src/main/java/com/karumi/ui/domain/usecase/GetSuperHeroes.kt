package com.karumi.ui.domain.usecase

import com.karumi.ui.domain.model.SuperHero
import org.funktionale.either.Either

class GetSuperHeroes {

    private val mockSuperHero = SuperHero(name = "Super Peter",
        description = "He is a superHero, write code faster than flash",
        isAvenger = true,
        photo = "https://avatars1.githubusercontent.com/u/4030704")

    operator fun invoke(): Either<Error, List<SuperHero>> =
        Either.right(arrayListOf(mockSuperHero))
}