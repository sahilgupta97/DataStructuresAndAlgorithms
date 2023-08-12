package dataStructureDesign.Heaps.oldImplementations;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class SmartHeap {

    List<Integer> heap;
    BiFunction<Integer, Integer, Boolean> comparisonFunction;
    Integer length;

    public SmartHeap(BiFunction<Integer, Integer, Boolean> comparisonFunction, List<Integer> array) {
        this.comparisonFunction = comparisonFunction;
        this.heap = buildHeap(array);
        this.length = heap.size();
    }

    public List<Integer> buildHeap(List<Integer> array) {
        Integer firstParentIdx = (heap.size() - 2 ) / 2;
        for (Integer currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
            heapifyDown(currentIdx, array.size() - 1, array);
        }
        return array;
    }

    private void heapifyDown(Integer currentIdx, Integer endIdx, List<Integer> heap) {
        Integer leftChildIdx = getLeftChildIndex(currentIdx);
        while (leftChildIdx <= endIdx) {
            Integer rightChildIdx = getRightChildIndex(currentIdx);
            rightChildIdx = rightChildIdx <= endIdx ? rightChildIdx : -1;

            Integer childIdxToSwap = leftChildIdx;

            if(rightChildIdx != -1){
                if(comparisonFunction.apply(heap.get(rightChildIdx), heap.get(leftChildIdx))){
                    childIdxToSwap = rightChildIdx;
                }
            }

            if(comparisonFunction.apply(heap.get(childIdxToSwap), heap.get(currentIdx))) {
                swap(childIdxToSwap, currentIdx, heap);
                currentIdx = childIdxToSwap;
                leftChildIdx = getLeftChildIndex(currentIdx);
            }else{
                return;
            }
        }
    }

    private void heapifyUp(Integer currentIdx, List<Integer> heap){
        while(currentIdx > 0){
            Integer parentIdx = getParentIndex(currentIdx);
            if(comparisonFunction.apply(heap.get(currentIdx), heap.get(parentIdx))){
                swap(currentIdx, parentIdx, heap);
                currentIdx = parentIdx;
            }else{
                return;
            }
        }
    }

    public Integer peek(){
        return heap.get(0);
    }

    public Integer remove(){
        swap(0, heap.size() - 1, heap);
        Integer valueToRemove = heap.get(heap.size() - 1);
        heap.remove(heap.size() - 1);
        length--;
        heapifyDown(0, heap.size() - 1, heap);
        return valueToRemove;
    }

    public void insert(Integer value){
        heap.add(value);
        length++;
        heapifyUp(heap.size() - 1, heap);
    }

    public static Boolean MAX_HEAP_FUNC(Integer a, Integer b){
        return a > b;
    }

    public static Boolean MIN_HEAP_FUNC(Integer a, Integer b){
        return a < b;
    }

    public Integer getLeftChildIndex(Integer parentIndex) {
        return (2 * parentIndex) + 1;
    }

    public Integer getRightChildIndex(Integer parentIndex) {
        return (2 * parentIndex) + 2;
    }

    public Integer getParentIndex(Integer childIndex) {
        return (childIndex - 1) / 2;
    }

    public void swap(Integer indexOne, Integer indexTwo, List<Integer> heap) {
        Integer temp = heap.get(indexOne);
        heap.set(indexOne, heap.get(indexTwo));
        heap.set(indexTwo, temp);
    }
}
