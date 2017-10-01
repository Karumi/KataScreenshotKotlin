package com.karumi.ui.domain

sealed class Result<R> {
    class Left<R>(val error: Error) : Result<R>() {
        override fun toString(): String = "Error $error"
    }

    class Right<R>(val value: R) : Result<R>() {
        override fun toString(): String = "Value $value"
    }

    infix fun <Rp> bind(f: (R) -> (Result<Rp>)): Result<Rp> {
        return when (this) {
            is Result.Left<R> -> Left(this.error)
            is Result.Right<R> -> f(this.value)
        }
    }

    infix fun <Rp> seq(e: Result<Rp>): Result<Rp> = e

    companion object {
        fun <R> success(value: R) = Result.Right(value)
        fun <R> error(error: Error) = Result.Left<R>(error)
    }
}