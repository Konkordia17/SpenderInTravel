package com.example.core_api.mediator

import javax.inject.Provider

interface MediatorsProvider {

    fun mediatorsMap(): Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
}
