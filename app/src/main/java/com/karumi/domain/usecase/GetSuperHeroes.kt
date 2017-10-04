package com.karumi.domain.usecase

import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import org.funktionale.either.Either

class GetSuperHeroes(val superHeroesRepository: SuperHeroRepository) {

    operator fun invoke(): Either<Error, List<SuperHero>> =
        Either.right(superHeroesRepository.getAllSuperHeroes())
}