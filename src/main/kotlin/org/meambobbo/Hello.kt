//kotlin

//package
package org.meambobbo

//imports
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import mu.KotlinLogging
import java.util.concurrent.ConcurrentSkipListSet

import kotlin.random.Random

//logger
private val logger = KotlinLogging.logger {}
private val channel = Channel<String>(10_000)

//main function
fun main(args: Array<String>) {
    println("Hello, World")
    showArgs(args)

    val demoClass = MyDemoClass()
    demoClass.thingItDoes()

    val coFun = CoroutinesFun()
    val results = coFun.goNuts()
    logger.debug { "coroutines results.size: ${results.size}" }

    runBlocking {
//        delay(2000)
        while (!channel.isClosedForReceive) {
            val msg = channel.receive()
            logger.debug { msg }
        }
    }

    logger.info { "main exiting" }
}

fun showArgs(args: Array<String>) {
    println("---- args: ----")
    val list = args.toList()
    list.stream().forEach { println("  - $it") }
    println("---------------")
}

class MyDemoClass {
    fun thingItDoes() {
        logger.debug("i actually don't do anything")
    }
}

class CoroutinesFun {
    private val results = ConcurrentSkipListSet<Int>()

    fun goNuts(): Set<Int> {
        runBlocking {
            val jobs = mutableListOf<Job>()
            for (i in 1..1_000) {
                jobs.add(launchCoroutine(i))
            }
            jobs.forEach { it.join() }
            channel.close()
        }
        return results.toSet()
    }

    private fun launchCoroutine(i: Int): Job =
        GlobalScope.launch {
            val duration = Random(i).nextInt(1000, 4000)
            logger.debug { "$i - launched coroutine with duration $duration" }
            channel.send("$i:$duration")
            if (duration < 2000) {
                delay(duration.toLong())
                logger.debug { "$i - called after waiting $duration ms " }
                results.add(i)
            }
        }
}
