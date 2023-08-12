package dataStructureDesign.Heaps.oldImplementations;

public class MaxHeap extends Heap{
    public MaxHeap(int capacity) {
        heap = new int[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public MaxHeap() {
        heap = new int[capacity];
        size = 0;
    }

    @Override
    public void heapifyUp() {
        int index = size - 1;
        while(hasParent(index) && parent(index) < heap[index]){
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    @Override
    public void heapifyDown() {
        int index = 0;
        while(hasLeftChild(index)){
            int largerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index) && rightChild(index) > leftChild(index)){
                largerChildIndex = getRightChildIndex(index);
            }

            if(heap[index] > heap[largerChildIndex]){
                break;
            }else{
                swap(index, largerChildIndex);
            }

            index = largerChildIndex;
        }
    }
}
