package dataStructureDesign.SegmentTree;

public class MinimumSegmentTree {

  private final int[] segmentArr;
  private final int size;

  public MinimumSegmentTree(int[] array) {
    this.size = array.length;
    this.segmentArr = new int[size * 4];
    buildSegmentTree(0, 0, size - 1, array);
  }

  private void buildSegmentTree(int index, int low, int high, int[] array) {
    if (low == high) {
      segmentArr[index] = array[low];
      return;
    }

    int mid = low + (high - low) / 2;
    buildSegmentTree(2 * index + 1, low, mid, array);
    buildSegmentTree(2 * index + 2, mid + 1, high, array);
    segmentArr[index] = Math.min(segmentArr[2 * index + 1], segmentArr[2 * index + 2]);
  }

  public int query(int startIdx, int endIdx) {
    return query(0, 0, size - 1, startIdx, endIdx);
  }

  public void update(int idx, int value) {
    update(0, 0, size - 1, idx, value);
  }

  private int query(int segmentIdx, int segmentLow, int segmentHigh, int queryLow, int queryHigh) {
    // no overlap
    if (queryHigh < segmentLow || segmentHigh < queryLow) {
      return Integer.MAX_VALUE;
    }

    // complete overlap
    if (segmentLow >= queryLow && segmentHigh <= queryHigh) {
      return segmentArr[segmentIdx];
    }

    // partial overlap
    int segmentMid = segmentLow + (segmentHigh - segmentLow) / 2;
    int leftSegmentTreeMin = query(2 * segmentIdx + 1, segmentLow, segmentMid, queryLow, queryHigh);
    int rightSegmentTreeMin = query(2 * segmentIdx + 2, segmentMid + 1, segmentHigh, queryLow, queryHigh);
    return Math.min(leftSegmentTreeMin, rightSegmentTreeMin);
  }

  private void update(int segmentIdx, int segmentLow, int segmentHigh, int idx, int value) {
    if (segmentLow == segmentHigh) {
      segmentArr[segmentIdx] = value;
      return;
    }

    int segmentMid = segmentLow + (segmentHigh - segmentLow) / 2;
    if (idx <= segmentMid) {
      update(2 * segmentIdx + 1, segmentLow, segmentMid, idx, value);
    } else {
      update(2 * segmentIdx + 2, segmentMid + 1, segmentHigh, idx, value);
    }

    segmentArr[segmentIdx] = Math.min(segmentArr[2 * segmentIdx + 1], segmentArr[2 * segmentIdx + 2]);
  }

}
