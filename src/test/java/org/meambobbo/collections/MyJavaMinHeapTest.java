package org.meambobbo.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

public class MyJavaMinHeapTest {

    @Test
    public void test() {
        MyJavaMinHeap<Integer> heap = new MyJavaMinHeap<>();
        heap.insert(20);
        System.out.println(heap.toString());
        heap.insert(1);
        System.out.println(heap.toString());
        heap.insert(1);
        System.out.println(heap.toString());
        heap.insert(-100);
        System.out.println(heap.toString());
        heap.insert(500);
        System.out.println(heap.toString());
        heap.insert(1);
        System.out.println(heap.toString());
        heap.insert(300);
        System.out.println(heap.toString());

        Assert.assertEquals(heap.pop(), Integer.valueOf(-100));
        System.out.println(heap.toString());
        Assert.assertEquals(heap.pop(), Integer.valueOf(1));
        System.out.println(heap.toString());
        Assert.assertEquals(heap.pop(), Integer.valueOf(1));
        System.out.println(heap.toString());
        Assert.assertEquals(heap.pop(), Integer.valueOf(1));
        System.out.println(heap.toString());
        Assert.assertEquals(heap.pop(), Integer.valueOf(20));
        System.out.println(heap.toString());
        Assert.assertEquals(heap.pop(), Integer.valueOf(300));
        System.out.println(heap.toString());
        Assert.assertEquals(heap.pop(), Integer.valueOf(500));
        System.out.println(heap.toString());

        System.out.println();
        System.out.println("Compare to Priority Queue");

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(20);
        System.out.println(priorityQueue.toString());
        priorityQueue.add(1);
        System.out.println(priorityQueue.toString());
        priorityQueue.add(1);
        System.out.println(priorityQueue.toString());
        priorityQueue.add(-100);
        System.out.println(priorityQueue.toString());
        priorityQueue.add(500);
        System.out.println(priorityQueue.toString());
        priorityQueue.add(1);
        System.out.println(priorityQueue.toString());
        priorityQueue.add(300);
        System.out.println(priorityQueue.toString());

        Assert.assertEquals(priorityQueue.poll(), Integer.valueOf(-100));
        System.out.println(priorityQueue.toString());
        Assert.assertEquals(priorityQueue.poll(), Integer.valueOf(1));
        System.out.println(priorityQueue.toString());
        Assert.assertEquals(priorityQueue.poll(), Integer.valueOf(1));
        System.out.println(priorityQueue.toString());
        Assert.assertEquals(priorityQueue.poll(), Integer.valueOf(1));
        System.out.println(priorityQueue.toString());
        Assert.assertEquals(priorityQueue.poll(), Integer.valueOf(20));
        System.out.println(priorityQueue.toString());
        Assert.assertEquals(priorityQueue.poll(), Integer.valueOf(300));
        System.out.println(priorityQueue.toString());
        Assert.assertEquals(priorityQueue.poll(), Integer.valueOf(500));
        System.out.println(priorityQueue.toString());
        
    }
}
