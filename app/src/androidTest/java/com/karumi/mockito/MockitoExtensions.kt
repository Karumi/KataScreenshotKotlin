package com.karumi.mockito

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

object MockitoExtensions {
    @JvmStatic
    fun <T> on(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)
}