package org.ups.greensky.common

interface WrappedItem<CLAZZ> {
    fun unwrap() : CLAZZ
}