package com.karumi.domain.usecase

import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import org.funktionale.either.Either

class GetSuperHeroByName(val superHeroesRepository: SuperHeroRepository) {

    operator fun invoke(name: String): Either<Error, SuperHero> =
        Either.right(superHeroesRepository.getByName(name))
}