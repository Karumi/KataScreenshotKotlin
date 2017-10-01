package com.karumi.ui.domain

sealed class Error {
    class UnknownError : Error()
}