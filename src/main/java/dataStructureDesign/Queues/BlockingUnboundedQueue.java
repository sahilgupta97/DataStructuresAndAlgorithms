package dataStructureDesign.Queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This queue does not wait as it has no bounds for adding the items in it
 */
public class BlockingUnboundedQueue<T> {

  private final Queue<T> queue;
  private final ReentrantLock offerLock;
  private final ReentrantLock pollLock;
  private final Condition notEmptyCondition;

  public BlockingUnboundedQueue() {
    this.queue = new LinkedList<>();
    this.offerLock = new ReentrantLock();
    this.pollLock = new ReentrantLock();
    this.notEmptyCondition = this.pollLock.newCondition();
  }

  public boolean offer(T item) {
    offerLock.lock();

    try {
//      System.out.println("Offering : " + item.toString());
      queue.offer(item);
      signalNotEmpty();
    } finally {
      offerLock.unlock();
    }

    return true;
  }

  public T poll() throws InterruptedException {
    pollLock.lock();

    try {
      while (queue.isEmpty()) {
        notEmptyCondition.await();
      }
//      System.out.println("Polling");
      return queue.poll();
    } finally {
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

  private void fullyLock() {
    offerLock.lock();
    pollLock.lock();
  }

  private void fullyUnlock() {
    offerLock.unlock();
    pollLock.unlock();
  }

  private void signalNotEmpty() {
    pollLock.lock();
    try {
      notEmptyCondition.signalAll();
    } finally {
      pollLock.unlock();
    }
  }
}
