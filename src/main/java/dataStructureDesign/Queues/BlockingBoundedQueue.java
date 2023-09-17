package dataStructureDesign.Queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingBoundedQueue<T> {

  private final Queue<T> queue;
  private final int capacity;
  private final ReentrantLock operatingLock;
  private final Condition emptyCondition;
  private final Condition fullCondition;

  public BlockingBoundedQueue(int capacity) {
    this.queue = new LinkedList<>();
    this.capacity = capacity;
    this.operatingLock = new ReentrantLock();
    this.emptyCondition = this.operatingLock.newCondition();
    this.fullCondition = this.operatingLock.newCondition();
  }

  public void offer(T item) throws InterruptedException {
    operatingLock.lock();

    try {
      while (queue.size() == capacity) {
        fullCondition.await();
      }
      queue.offer(item);
      fullCondition.signalAll();
    } finally {
      operatingLock.unlock();
    }
  }

  public T poll() throws InterruptedException {
    operatingLock.lock();

    try {
      while (queue.isEmpty()) {
        emptyCondition.await();
      }
      return queue.poll();
    } finally {
      emptyCondition.signalAll();
      operatingLock.unlock();
    }
  }
}
