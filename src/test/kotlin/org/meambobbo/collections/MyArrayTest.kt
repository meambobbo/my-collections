package org.meambobbo.collections

import mu.KotlinLogging
import java.time.Duration
import java.time.temporal.ChronoField
import java.time.temporal.Temporal
import java.time.temporal.TemporalField
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.test.Test

private val logger = KotlinLogging.logger {}

/**
 * Tests for the MyArray class
 */
class MyArrayTest {

    /**
     * custom type used to test ability to fulfill generic type
     */
    data class MyTestType(val str: String = "Default", val int: Int = 1)

    /**
     * ensure MyArray can be instantiated using a custom type
     */
    @Test
    fun testInit() {
        logger.info { "testInit started" }
        val arr: MyArray<MyTestType> = MyArray(10)
        assert(arr.size() == 10)
        assert(arr.get(0) == null)
        assert(arr.get(9) == null)
        var found = false
        try {
            arr.get(10)
        } catch (e: ArrayIndexOutOfBoundsException) {
            found = true
        }
        assert(found)
        logger.info { "testInit finished" }
    }

    /**
     * Test the get() and set() methods
     */
    @Test
    fun testGetAndSet() {
        logger.info { "testGetAndSet started" }

        val arr: MyArray<MyTestType?> = MyArray(10)
        arr.set(0, MyTestType("Great", 1))
            .set(1, MyTestType("Job", 2))
            .set(9, MyTestType("Thing", 3))

        assert(MyTestType("Great", 1) == (arr.get(0)))
        assert(MyTestType("Job", 2) == (arr.get(1)))
        assert(MyTestType("Thing", 3) == (arr.get(9)))
        logger.info { "testGetAndSet finished" }
    }

    /**
     * Test Adding Single and Array Elements - check that size is correct
     */
    @Test
    fun testAdd() {
        logger.info { "testAdd started" }
        val arr: MyArray<MyTestType> = MyArray(3)
        for (i in 0..2)
            arr.set(i, MyTestType("Whatever", i))
        arr.add(MyTestType("AddOne", 3))
        assert(MyTestType("AddOne", 3) == arr.get(3))
        assert(arr.size() == 4)

        var x = 4
        val newArr: Array<MyTestType?> = arrayOf(
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++),
            MyTestType("AddMany", x++)
        )
        arr.add(newArr)
        assert(MyTestType("AddMany", 11) == arr.get(11))
        assert(MyTestType("AddMany", 12) == arr.get(12))
        assert(MyTestType("AddMany", 26) == arr.get(26))
        assert(arr.size() == 27)

        logger.info { "testAdd finished" }
        logger.debug { "arr: $arr" }
    }

    @Test
    fun testPerformance() {
        logger.info { "testPerformance started" }
        val arr = MyArray<Int>(10)

        var start = Date().toInstant()
        for (i in 0..2_500_000) {
            arr.add(i)
        }
        var end = Date().toInstant()
        var duration = Duration.between(start, end)
        logger.info { "inserted 2.5M items in ${duration.toMillis()} ms" }

        start = Date().toInstant()
        var x = 0
        for (i in 0..1_000) {
            x = Random(Date().toInstant().get(ChronoField.MILLI_OF_SECOND)).nextInt(0, 2_499_999)
            arr.rem(i = x)
        }
        end = Date().toInstant()
        duration = Duration.between(start, end)
        logger.info { "removed 1k random items in ${duration.toMillis()} ms" }



        logger.info { "testPerformance finished" }
    }

}
