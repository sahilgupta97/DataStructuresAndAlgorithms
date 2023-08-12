package dataStructureDesign.Heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<E> {
    private List<E> heap;
    private Comparator<E> comparator;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    public Heap(List<E> array) {
        this.heap = buildHeap(array);
    }

    public Heap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    public Heap(List<E> array, Comparator<E> comparator) {
        this.comparator = comparator;
        this.heap = buildHeap(array);
    }

    private List<E> buildHeap(List<E> array) {
        int firstParentIdx = getParentIndex(array.size() - 1);
        for(int idx = firstParentIdx; idx >= 0; idx--) {
            siftDown(idx, array.size() - 1, array);
        }
        return array;
    }

    public void offer(E element) {
        this.heap.add(element);
        siftUp(this.heap.size() - 1, this.heap);
    }

    public E poll() {
        if(this.heap.isEmpty()) {
            return null;
        }

        swap(0, this.heap.size() - 1, this.heap);
        E removedElement = this.heap.remove(this.heap.size() - 1);
        siftDown(0, this.heap.size() - 1, this.heap);
        return removedElement;
    }

    private void swap(int idx1, int idx2, List<E> array) {
        E temporaryValue = array.get(idx1);
        array.set(idx1, array.get(idx2));
        array.set(idx2, temporaryValue);
    }

    public E peek() {
        if(this.heap.isEmpty()) {
            return null;
        }

        return this.heap.get(0);
    }

    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    public int size() {
        return this.heap.size();
    }

    private void siftDown(int startIdx, int endIdx, List<E> array) {
        int currentIdx = startIdx;
        int leftChildIdx = getLeftChildIndex(startIdx);
        while(leftChildIdx <= endIdx) {
            int rightChildIdx = getRightChildIndex(startIdx);
            rightChildIdx = (rightChildIdx <= endIdx) ? rightChildIdx : -1;

            int childIdxToSwap = leftChildIdx;
            if(rightChildIdx != -1 && hasMorePriority(rightChildIdx, leftChildIdx, array)) {
                childIdxToSwap = rightChildIdx;
            }

            if(hasMorePriority(childIdxToSwap, currentIdx, array)) {
                swap(childIdxToSwap, currentIdx, array);
                currentIdx = childIdxToSwap;
                leftChildIdx = getLeftChildIndex(currentIdx);
            } else {
                return;
            }
        }
    }

    private void siftUp(int startIdx, List<E> array) {
        int currentIndex = startIdx;
        while(currentIndex > 0) {
            int parentIdx = getParentIndex(currentIndex);
            if(hasMorePriority(currentIndex, parentIdx, array)) {
                swap(currentIndex, parentIdx, array);
                currentIndex = parentIdx;
            } else {
                return;
            }
        }
    }

    /**
     * Checks whether idx1 has more priority than idx2.
     */
    private boolean hasMorePriority(int idx1, int idx2, List<E> array) {
        if(comparator != null) {
            return hasMorePriorityUsingComparator(idx1, idx2, array);
        }
        return  hasMorePriorityUsingComparable(idx1, idx2, array);
    }

    private boolean hasMorePriorityUsingComparator(int idx1, int idx2, List<E> array) {
        E element1 = array.get(idx1);
        E element2 = array.get(idx2);

        int priority = comparator.compare(element1, element2);
        return priority < 0;
    }

    private boolean hasMorePriorityUsingComparable(int idx1, int idx2, List<E> array) {
        Comparable<E> element1 = (Comparable<E>) array.get(idx1);
        E element2 = array.get(idx2);

        int priority = element1.compareTo(element2);
        return priority < 0;
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private int getParentIndex(int index) {
        return  (index - 1) / 2;
    }
}
