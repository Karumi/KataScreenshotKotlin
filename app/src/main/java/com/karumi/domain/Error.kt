package com.karumi.domain

sealed class Error {
    class UnknownError : Error()
}