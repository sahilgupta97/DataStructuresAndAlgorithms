package dataStructureDesign.SegmentTree;

import java.util.Arrays;

public class SampleRun {

  public static void main(String[] args) {
    int[] arr = new int[] {0,5,2,6,1,2};
    MinimumSegmentTree minimumSegmentTree = new MinimumSegmentTree(arr);
    System.out.println("Array is : " + Arrays.toString(arr));
    System.out.println("Min from idx : (0, 5) is : " +  + minimumSegmentTree.query(0,5));
    System.out.println("Min from idx : (0, 2) is : " +  + minimumSegmentTree.query(0,2));
    System.out.println("Updating idx : 0 to value 10");
    minimumSegmentTree.update(0, 10);
    System.out.println("Min from idx : (0, 2) is : " +  + minimumSegmentTree.query(0,2));
    System.out.println("Min from idx : (3, 5) is : " +  + minimumSegmentTree.query(3,5));
    System.out.println("Min from idx : (4, 5) is : " +  + minimumSegmentTree.query(4,5));
  }

}
