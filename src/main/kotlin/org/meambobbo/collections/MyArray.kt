package org.meambobbo.collections

import mu.KotlinLogging
import kotlin.math.roundToInt

/**
 * My implementation of a managed/dynamic "Array" Datatype
 *
 * Backed by Kotlin Array which when run on the JVM uses the Java array implementation
 */

private val logger = KotlinLogging.logger {}

class MyArray<T>() {
    private lateinit var arr: Array<Any?>
    private var size = 0
    private val multiplier: Double = 2.0

    /**
     * standard primitive array constructor - size needs to be known in advance
     */
    constructor(sizeIn: Int) : this() {
        arr = Array(sizeIn) { null }
        size = sizeIn
    }

    /**
     * alternate constructor for an existing primitive Array
     */
    constructor (arrIn: Array<T?>) : this() {
        arr = Array(0, { null })
        this.add(arrIn)
    }

    /**
     * get by index
     */
    fun get(i: Int): T? {
        if (i >= size)
            throw ArrayIndexOutOfBoundsException()
        return this.arr[i] as T
    }

    /**
     * set at index
     */
    fun set(i: Int, item: T?): MyArray<T> {
        this.arr[i] = item
        return this
    }

    /**
     * get the array's element size (not the backing Array's size)
     */
    fun size(): Int {
        return this.size
    }

    /**
     * Add a single item - will increased the managed array size
     * If the backing Array is too small to handle the additional item, clone the items to a new Array 2x as large
     */
    fun add(item: T?): MyArray<T> {
        if (size == arr.size) {
            val newArrSize = (multiplier * arr.size).roundToInt() + 1
            logger.warn { "increasing array size from ${arr.size} elements to ${newArrSize}" }
            arr = Array(newArrSize) {
                if (it < arr.size) {
                    arr[it]
                } else {
                    null
                }
            }
        }
        arr[size] = item
        size++

        return this
    }

    /**
     * Add an Array of items - will increased the managed array size
     * If the backing Array is too small to handle the additional items, clone to a double-sized array as many times as needed
     */
    fun add(items: Array<T?>): MyArray<T> {

        for (item in items) {
            if (size == arr.size) {
                val newArrSize = (multiplier * arr.size).roundToInt() + 1
                logger.warn { "increasing array size from ${arr.size} elements to ${newArrSize}" }
                arr = Array(newArrSize) {
                    if (it < arr.size) {
                        arr[it]
                    } else {
                        null
                    }
                }
            }
            arr[size] = item
            size++
        }
        return this
    }

    /**
     * remove a single item by index, decrementing the size and shifting the items above the removed index
     */
    fun rem(i: Int): MyArray<T> {
        var x = i
        while (x < size - 1) {
            arr[x] = arr[x + 1]
            x++
        }
        arr[x] = null
        size--

        return this
    }

    /**
     * return the index of the first item that matches the param, -1 if not found
     */
    fun find(item: T): Int {
        for (i in 0..size - 1) {
            if (item == arr[i])
                return i
        }
        return -1
    }

    /**
     * remove all intances of item T
     */
    fun rem (item: T): MyArray<T> {
        var x = find(item)
        while (x != -1) {
            rem(x)
            x = find(item)
        }
        return this
    }

    /**
     * Convenient toString override to see the elements in the backing array
     */
    override fun toString(): String {
        var str = super.toString() + ": ["
        for (i in 0..size - 1) {
            str += "${arr[i]}, "
        }
        str = str.substring(0, str.length - 2) + "]"
        return str
    }
}