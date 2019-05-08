package com.sanitcode.footballmatchdbtest.util

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextProviderTest: CoroutineContextProvider() {
    override val main : CoroutineContext = Unconfined
}