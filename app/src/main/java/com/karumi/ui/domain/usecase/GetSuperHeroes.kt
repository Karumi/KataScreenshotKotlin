package com.karumi.ui.domain.usecase

import com.karumi.ui.domain.Result
import com.karumi.ui.domain.model.SuperHero

class GetSuperHeroes {

    val mockSuperHero = SuperHero(name = "Super Peter",
        description = "He is a superHero, write code faster than flash",
        isAvenger = true,
        photo = "https://avatars1.githubusercontent.com/u/4030704")

    fun get(): Result<List<SuperHero>> {
        return Result.success(arrayListOf(mockSuperHero))
    }
}