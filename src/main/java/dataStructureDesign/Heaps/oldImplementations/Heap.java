package dataStructureDesign.Heaps.oldImplementations;

import java.util.Arrays;
import java.util.NoSuchElementException;

public abstract class Heap {
    /**
     *      Array data structure to hold the elements. Perfect candidate as heap is a complete binary tree.
     *     Hence, we can map child and parent using indexing.
     */
    int[] heap;

    /**
     *      To keep track of number of elements currently present in the array, as we need to move it back
     *     when we poll from the heap.
     */
    int size;

    /**
     *      Initial capacity of the heap i.e. underlying array data structure.
     */
    int capacity = 10;

    /**
     * Abstract method that will be implemented based on Min/Max Heap.
     */
    public abstract void heapifyUp();
    public abstract void heapifyDown();

    /**
     * Adds the item in the heap.
     */
    public void add(int item){
        ensureCapacity();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    /**
     * Returns the minimum/maximum element of the heap
     */
    int peek(){
        if(isEmpty()){
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap[0];
    }

    /**
     * Extract the minimum/maximum element of the heap.
     */
    public int poll(){
        if(isEmpty()){
            throw new NoSuchElementException("Heap is empty.");
        }

        int element = heap[0];
        heap[0] = heap[size-1];
        size--;

        heapifyDown();

        return element;
    }

    /**
     * To double the capacity of the array if we add an element when the array is full.
     */
    public void ensureCapacity(){
        if(size == capacity){
            heap = Arrays.copyOf(heap, capacity*2);
            capacity *= 2;
        }
    }

    /**
     * Check whether heap is empty or not.
     */
    boolean isEmpty(){
        return size == 0;
    }

    /**
     * Simple swap method to swap the value in the array while heapifying.
     */
    public void swap(int indexOne, int indexTwo){
        int temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    public int getLeftChildIndex(int parentIndex){
        return (2 * parentIndex) + 1;
    }

    public int getRightChildIndex(int parentIndex){
        return (2 * parentIndex) + 2;
    }

    public int getParentIndex(int childIndex){
        return (childIndex-1) / 2;
    }

    public boolean hasLeftChild(int index){
        return getLeftChildIndex(index) < size;
    }

    public boolean hasRightChild(int index){
        return getRightChildIndex(index) < size;
    }

    public boolean hasParent(int index){
        return getParentIndex(index) >= 0;
    }

    public int leftChild(int index){
        return heap[getLeftChildIndex(index)];
    }

    public int rightChild(int index){
        return heap[getRightChildIndex(index)];
    }

    public int parent(int index){
        return heap[getParentIndex(index)];
    }
}
