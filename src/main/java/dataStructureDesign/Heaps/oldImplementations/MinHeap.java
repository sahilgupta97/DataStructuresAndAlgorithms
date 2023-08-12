package dataStructureDesign.Heaps.oldImplementations;

public class MinHeap extends Heap {
    public MinHeap(int capacity) {
        heap = new int[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public MinHeap() {
        heap = new int[capacity];
        size = 0;
    }

    @Override
    public void heapifyUp() {
        int index = size - 1;
        while(hasParent(index) && parent(index) > heap[index]){
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    @Override
    public void heapifyDown() {
        int index = 0;
        while(hasLeftChild(index)){
            int smallerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index) && rightChild(index) < leftChild(index)){
                smallerChildIndex = getRightChildIndex(index);
            }

            if(heap[index] < heap[smallerChildIndex]){
                break;
            }else{
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }
}
