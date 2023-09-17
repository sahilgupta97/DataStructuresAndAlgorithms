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
  private final ReentrantLock operatingLock;
  private final Condition notFullCondition;
  private final Condition notEmptyCondition;

  public BlockingBoundedQueue(int capacity) {
    this.queue = new LinkedList<>();
    this.capacity = capacity;
    this.operatingLock = new ReentrantLock();
    this.notEmptyCondition = this.operatingLock.newCondition();
    this.notFullCondition = this.operatingLock.newCondition();
  }

  public void offer(T item) throws InterruptedException {
    operatingLock.lock();

    try {
      while (queue.size() == capacity) {
        // wait till queue becomes (not full) => till there is some space
        notFullCondition.await();
      }
//      System.out.println("Offering : " + item.toString());
      queue.offer(item);

      // signal all the threads that now the queue is not empty i.e. chance created for poll to take place
      notEmptyCondition.signalAll();
    } finally {
      operatingLock.unlock();
    }
  }

  public T poll() throws InterruptedException {
    operatingLock.lock();
    try {
      while (queue.isEmpty()) {
        // wait till queue becomes (not empty) => it at least has one item
        notEmptyCondition.await();
      }

//      System.out.println("Polling : " + queue.peek());
      T item = queue.poll();

      // signal all threads that queue is now not full i.e. space created for offering to it
      notFullCondition.signalAll();

      return item;
    } finally {
      operatingLock.unlock();
    }
  }

  public int size() {
    operatingLock.lock();
    try {
      return queue.size();
    } finally {
      operatingLock.unlock();
    }
  }
}

//package dataStructureDesign.Queues;
//
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;

/*
TWO WAY LOCKING APPROACH

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
//      System.out.println("Offering : " + item.toString());
      queue.offer(item);
    } finally {
      offerLock.unlock();
    }

    // signal all the threads that now the queue is not empty i.e. chance created for poll to take place
    signalNotEmpty();
  }

  public T poll() throws InterruptedException {
    pollLock.lock();
    T polledItem = null;
    try {
      while (queue.size() == 0) {
        // wait till queue becomes (not empty) => it at least has one item
        notEmptyCondition.await();
      }
//      System.out.println("Polling");
      polledItem = queue.poll();
    } finally {
      pollLock.unlock();
    }
    System.out.println("Polled : " + polledItem.toString());

    // signal all threads that queue is now not full i.e. space created for offering to it
    signalNotFull();

    return polledItem;
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
      notEmptyCondition.signal();
    } finally {
      pollLock.unlock();
    }
  }

  private void signalNotFull() {
    offerLock.lock();
    try {
      notFullCondition.signal();
    } finally {
      offerLock.unlock();
    }
  }
}
*/
