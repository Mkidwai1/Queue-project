package dsassignment3;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercises WorkAheadQueue methods.
 *
 * @author xiaotuwang. Revised for fall 2021, nblong.
 * @param <T>
 */
public class WorkAheadQueueTest<T> {

    // WorkAheadQueue being tested
    private WorkAheadQueue<Integer> instance;
    // TestList holds correct values of queue for comparison to actual queue
    private ArrayList<Integer> testList;
    // Pointer to dequeued node during tests
    Integer dequeuePtr = null;

    /**
     * Test of dequeue(n) method of class WorkAheadQueue.
     */
    @Test
    public void testDequeue_int() {
        System.out.println("dequeue(n)");

        // Try dequeueing from an empty queue - should throw EmptyCollection
        buildQueue(0);
        assertTrue(instance.isEmpty());
        try {
            dequeuePtr = instance.dequeue(0);
            fail("*** Error in testDequeue_int - shouldn't get here 1");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyCollectionException);
        }

        /* Try dequeueing from a 2-element queue (i.e., with indices 0 & 1) but 
         * with invalid arguments -2 and 2.  Each should throw InvalidArgument.
         */
        for (int rem = -2; rem < 3; rem += 4) {
            buildQueue(2);
            try {
                dequeuePtr = instance.dequeue(rem);
                fail("*** Error in testDequeue_int - shouldn't get here 2");
            } catch (Exception e) {
                assertTrue(e instanceof InvalidArgumentException);
            }
        }

        /*
        Test dequeueing indices 0, 1, & 2 from a queue of 4, 3, 2, and 1 
        elements.  All should pass.
         */
        int last;  // last is the highest index from which you can dequeue 
                   // without error during this test.
        int max;   // max is the size of the queue.
        for (max = 4; max > 0; max--) {
            if (max >= 3) {
                last = 3;
            } else {
                last = max;
            }
            int rem;  // rem is the index being removed.
            /* 
            Try to remove each of the elements 0 to 2 (or the size of the
            queue, which ever is smaller.
            */
            for (rem = 0; rem < last; rem++) {
                buildQueue(max);  // Build the queue.
                //  Try dequeueing element at index rem - should pass.
                try {
                    dequeuePtr = instance.dequeue(rem);  // Dequeue at index rem
                } catch (Exception e) {
                    System.out.println("*** " + e.toString());
                    fail("*** Error in testDequeue_int - shouldn't get here 4");
                }
                // Should have removed value of (rem + 1)
                assertEquals(dequeuePtr.intValue(), rem + 1);
                testList.remove(rem);  // Remove corresponding testList element
                // Should be max-1 elements left
                assertEquals(instance.size(), testList.size(), max - 1);
                testFrontThrees();  // Verify frontThrees arrays are correct
            }
        }
    }

    /**
     * Test of first(x) method, of class WorkAheadQueue.
     */
    @Test
    public void testFirst_int() throws Exception {
        System.out.println("first(n)");
        buildQueue(0);
        
        /*
        Test empty queue - should throw EmptyCollectionException
         */
        assertTrue(instance.isEmpty());
        try {
            Object x = instance.first(0);
            fail("*** Error in testFirst_int - shouldn't get here 1");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyCollectionException);
        }

        /*
        Test non-empty queue but with invalid arguments - should throw 
        InvalidArgumentException.
         */
        instance.enqueue(1);
        assertEquals(instance.size(), 1);
        try {
            Object x = instance.first(-1);  // Negative index
            fail("*** Error in testFirst_int - shouldn't get here 2");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidArgumentException);
        }
        try {
            Integer x = instance.first(2);  // Index larger than size()-1
            fail("*** Error in testFirst_int - shouldn't get here 3");
        } catch (Exception e) {
            assertTrue(e instanceof InvalidArgumentException);
        }

        /*
        Test non-empty queue with more than 3 elements
         */
            buildQueue(5); 
            for (int i = 0; i < 3; i++) {
            try {
                assertEquals(instance.first(i).intValue(), i + 1);
                //  Verify call to first(n) doesn't change any size
                assertEquals(instance.size(), 5);
                assertEquals(instance.frontThreeElements.size(), 3);
                assertEquals(instance.frontThreeNodes.size(), 3);
            } catch (Exception e) {
                fail("*** Error in testFirst_int - shouldn't get here 4");
            }
        }
    }

    /**
     * Test of firstThreeNodes method of class WorkAheadQueue.
     */
    @Test
    public void testFirstThreeNodes() {
        System.out.println("firstThreeNodes");
        buildQueue(0);
        /*
        Test contents of firstThreeNodes array when queue is built from 0 to 
        5 elements.
         */
        int max;
        for (int i = 0; i < 5; i++) {
            instance.enqueue(i + 1);
            // Set maximum size of firstThreeNodes array
            if (instance.size() < 4) 
            {
                max = instance.size();
            } else {
                max = 3;
            }
            assertEquals(instance.frontThreeNodes.size(), max);
            for (int j = 0; j < max; j++) {
                assertEquals(instance.frontThreeNodes.get(j).getElement().
                             intValue(), j + 1);
            }
        }
    }

    /**
     * Test of firstThreeElements method, of class WorkAheadQueue.
     */
    @Test
    public void testFirstThreeElements() {
        System.out.println("firstThreeElements");
        buildQueue(0);
        /*
        Test contents of firstThreeElements array as queue is built from 0 to 
        5 elements.
         */
        int max;  // Maximum size of the firstThrees arrays
        for (int i = 0; i < 5; i++) {
            instance.enqueue(i + 1);
            if (instance.size() < 4) // Set maximum size of firstThrees arrays
            {
                max = instance.size();
            } else {
                max = 3;
            }
            assertEquals(instance.frontThreeElements.size(), max);
            for (int j = 0; j < max; j++) {
                assertEquals(instance.frontThreeElements.get(j).intValue(), 
                             j + 1);
            }
        }
    }

    /**
     * Test of enqueue method, of class WorkAheadQueue.
     */
    @Test
    public void testEnqueue() {
        System.out.println("enqueue");
        buildQueue(0);
        
        /*
        Test queue size and contents of firstThrees arrays as queue is built
        from 0 to 5 elements.
         */
        for (int i = 0; i < 5; i++) {
            instance.enqueue(i + 1);
            assertEquals(instance.size(), i + 1);
            // Enqueueing the first three elements should update the 
            // firstThrees arrays
            if (i < 3) {  
                assertEquals(instance.frontThreeElements.size(), i + 1);
                assertEquals(instance.frontThreeElements.get(i).intValue(), 
                             i + 1);
                assertEquals(instance.frontThreeNodes.size(), i + 1);
                assertEquals(instance.frontThreeNodes.get(i).getElement().
                             intValue(), i + 1);
            } else {  // Enqueueing the fourth+ elements should not alter the 
                      // firstThrees arrays
                assertEquals(instance.frontThreeElements.size(), 3);
                assertEquals(instance.frontThreeNodes.size(), 3);
                for (int j = 0; j < 3; j++) {
                    assertEquals(instance.frontThreeElements.get(j).intValue(), 
                                 j + 1);
                    assertEquals(instance.frontThreeNodes.get(j).getElement().
                                 intValue(), j + 1);
                }
            }
        }

        /*
        Test non-empty queue has elements in the correct order by dequeueing 
        them one at a time
         */
        int max = instance.size();
        for (int i = 0; i < max; i++) {
            try {
                assertEquals(instance.dequeue().intValue(), i + 1);
                assertEquals(instance.size(), max - 1 - i);
            } catch (EmptyCollectionException ece) {
                fail("*** Error in testEnqueue - shouldn't get here");
            }
        }
        assertEquals(instance.size(), 0);
    }

    /**
     * Test of dequeue method, of class WorkAheadQueue.
     */
    @Test
    public void testDequeue() {
        System.out.println("dequeue");
        buildQueue(0); // Build empty queue
        /*
        Test empty queue - should throw EmptyCollectionException
         */
        try {
            Integer x = instance.dequeue();
            fail("*** Error in testDequeue - shouldn't get here 1");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyCollectionException);
        }

        /*
        Test queue size and contents of firstThrees arrays when queue has up to
        5 elements
         */
        buildQueue(5);
        int max = instance.size(); // Number of elements in initial queue
        int compVal;               // Max size of firstThrees arrays
        for (int i = 0; i < 5; i++) { // Dequeue each element
            try {
                // Verify value dequeued
                assertEquals(instance.dequeue().intValue(), i + 1); 
                // Verify queue size after dequeue
                assertEquals(instance.size(), max - i - 1); 
                if (instance.size() < 4) // Set max size of firstThrees arrays
                {
                    compVal = instance.size();
                } else {
                    compVal = 3;
                }
                // Verify firstThrees arrays sizes
                assertEquals(instance.frontThreeElements.size(), compVal); 
                assertEquals(instance.frontThreeNodes.size(), compVal);
                // For each element in the firstThrees arrays ...
                for (int j = 0; j < compVal; j++) {  
                    assertEquals(instance.frontThreeElements.get(j).intValue(), 
                                 i + j + 2);
                    assertEquals(instance.frontThreeNodes.get(j).getElement().
                                 intValue(), i + j + 2);
                }
            } catch (EmptyCollectionException ece) {
                fail("*** Error in testDequeue - should get here");
            }
        }
    }

    /**
     * Test of first method, of class WorkAheadQueue.
     */
    @Test
    public void testFirst() {
        System.out.println("first");
        instance = new WorkAheadQueue<Integer>();
        assertEquals(instance.size(), 0);

        /*
        Test empty queue - should throw EmptyCollectionException
         */
        assertTrue(instance.isEmpty());
        try {
            Object result = instance.first();
            fail("*** Error in testFirst - shouldn't get here");
        } catch (Exception e) {
            assertTrue(e instanceof EmptyCollectionException);
        }

        /*
        Test non-empty queue.  Should not throw exception.
         */
        buildQueue(3);
        assertEquals(instance.size(), 3);

        int max = instance.size();
        for (int i = 0; i < max; i++) {
            try {
                assertEquals(instance.first(), instance.dequeue(), i + 1);
                assertEquals(instance.size(), 2 - i);
                assertEquals(instance.frontThreeElements.size(), 2 - i);
                assertEquals(instance.frontThreeNodes.size(), 2 - i);
            } catch (EmptyCollectionException ece) {
                fail("*** Error in testFirst - shouldn't get here.");
            }
        }
        assertEquals(instance.size(), 0);
        assertEquals(instance.frontThreeElements.size(), 0);
        assertEquals(instance.frontThreeNodes.size(), 0);
    }

    /**
     * Test of isEmpty method, of class WorkAheadQueue.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        /*
        Test empty queue
         */
        instance = new WorkAheadQueue<>();
        assertTrue(instance.isEmpty());
        /*
        Test when queue is not empty
         */
        instance.enqueue(1);
        assertFalse(instance.isEmpty());
    }

    /**
     * Test of size method, of class WorkAheadQueue.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        /*
        Test queue of size 0-4
         */
        instance = new WorkAheadQueue<>();
        for (int n = 0; n < 5; n++) {
            assertEquals(instance.size(), n);
            instance.enqueue(n);
        }
    }
    
    /**
     * Test of toString
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        instance = new WorkAheadQueue<>();
        assertTrue(instance.isEmpty());

        // Test empty queue
        assertTrue(instance.toString().isEmpty());

        // Non-empty queue
        instance.enqueue(1);
        assertEquals("1", instance.toString());
        
        // Multi-element queue
        instance.enqueue(2);
        assertEquals("1, 2", instance.toString());
    }

    /**
     * Builds: A n-element WorkAheadQueue queue of Integers with values {1, 2,
     * 3, ..., n-1} and testList, an n-element ArrayList of Integers with the 
     * same values as the queue.  testList is used to verify the contents of the
     * queue.
     */
    private void buildQueue(int n) {
        instance = new WorkAheadQueue<Integer>();  // new WorkAheadQueue
        testList = new ArrayList<>();              // new ArrayList
        for (int i = 0; i < n; i++) {
            instance.enqueue(i + 1);  // Set up queue
            testList.add(i + 1);      // Set up ArrayList of comparison values
        }
        assertEquals(instance.size(), n);
        if (instance.size() > 3) {
            assertEquals(instance.frontThreeElements.size(), 3);
            assertEquals(instance.frontThreeNodes.size(), 3);
        } else {
            assertEquals(instance.frontThreeElements.size(), n);
            assertEquals(instance.frontThreeNodes.size(), n);
        }
    }

    /**
     * Verifies that the contents of the frontThreeElements and frontThreeNodes
     * ArrayLists, i.e., the "frontThrees" are correct for whatever the contents
     * of the queue are.
     */
    private void testFrontThrees() {
        for (int i = 0; (i < instance.size() && i < 2); i++) {
            assertEquals(instance.frontThreeElements.get(i).intValue(),
                    testList.get(i).intValue());
            assertEquals(instance.frontThreeNodes.get(i).getElement().
                    intValue(), testList.get(i).intValue());
        }

    }
}
