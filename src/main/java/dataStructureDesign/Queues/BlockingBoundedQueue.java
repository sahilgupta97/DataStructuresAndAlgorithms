package dataStructureDesign.Queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This queue does wait as it has defined bounds for adding the items in it.
 */
public class BlockingBoundedQueue<T> {

  private final Queue<T> queue;
  private final int capacity;
  private final ReentrantLock offerLock;
  private final ReentrantLock pollLock;
  private final Condition notFullCondition;
  private final Condition notEmptyCondition;

  public BlockingBoundedQueue(int capacity) {
    this.queue = new LinkedList<>();
    this.capacity = capacity;
    this.offerLock = new ReentrantLock();
    this.pollLock = new ReentrantLock();
    this.notEmptyCondition = this.pollLock.newCondition();
    this.notFullCondition = this.offerLock.newCondition();
  }

  public void offer(T item) throws InterruptedException {
    offerLock.lock();

    try {
      while (queue.size() == capacity) {
        // wait till queue becomes (not full) => till there is some space
        notFullCondition.await();
      }
      queue.offer(item);

      // signal all the threads that now the queue is not empty i.e. chance create for poll to take place
      notEmptyCondition.signalAll();
    } finally {
      offerLock.unlock();
    }
  }

  public T poll() throws InterruptedException {
    pollLock.lock();

    try {
      while (queue.isEmpty()) {
        // wait till queue becomes (not empty) => it at least has one item
        notEmptyCondition.await();
      }
      return queue.poll();
    } finally {
      // signal all threads that queue is now not full i.e. space created for offering to it
      notFullCondition.signalAll();
      pollLock.unlock();
    }
  }

  public int size() {
    fullyLock();
    try {
      return queue.size();
    } finally {
      fullyUnlock();
    }
  }

  void fullyLock() {
    offerLock.lock();
    pollLock.lock();
  }

  void fullyUnlock() {
    offerLock.unlock();
    pollLock.unlock();
  }
}
