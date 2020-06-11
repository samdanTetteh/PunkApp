package com.ijikod.punkapp

import org.mockito.Mockito


class MokitoUtils {
    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}
