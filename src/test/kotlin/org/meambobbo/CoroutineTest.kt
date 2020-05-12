package org.meambobbo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test

class CoroutineTest {
    @Test
    fun run() {
        GlobalScope.launch {
            delay(1000)
            println("test")
        }
        println("about to ...")
        Thread.sleep(3000L)
    }
}