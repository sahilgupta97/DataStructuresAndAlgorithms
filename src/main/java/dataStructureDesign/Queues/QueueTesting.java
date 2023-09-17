package dataStructureDesign.Queues;

public class QueueTesting {

  public static void main(String[] args) throws InterruptedException {
    simulateBlockingBounded();

//    simulateBlockingUnbounded();
  }

  private static void simulateBlockingBounded() throws InterruptedException {
    BlockingBoundedQueue<Integer> blockingBoundedQueue = new BlockingBoundedQueue<>(6);

    Producer producerTask = new Producer(blockingBoundedQueue);
    Consumer consumerTask = new Consumer(blockingBoundedQueue);

    Thread producerThread = new Thread(producerTask);
    Thread consumerThread = new Thread(consumerTask);

    producerThread.start();

//    Thread.sleep(5000);
    consumerThread.start();

    System.out.println();
  }

  private static void simulateBlockingUnbounded() {
    BlockingUnboundedQueue<Integer> blockingUnboundedQueue = new BlockingUnboundedQueue<>();

    ProducerBlockingUnbounded producerTask = new ProducerBlockingUnbounded(blockingUnboundedQueue);
    ConsumerBlockingUnbounded consumerTask = new ConsumerBlockingUnbounded(blockingUnboundedQueue);

    Thread producerThread = new Thread(producerTask);
    Thread consumerThread = new Thread(consumerTask);

    producerThread.start();

    consumerThread.start();
  }

  static class Producer implements Runnable {
    private final BlockingBoundedQueue<Integer> queue;

    Producer(BlockingBoundedQueue<Integer> queue) {
      this.queue = queue;
    }


    @Override
    public void run() {
      while(true) {
        int num = (int)(Math.random() * 1000 + 23);
        try {
//          Thread.sleep(1000);
          queue.offer(num);
        } catch (InterruptedException e) {
          System.out.println("Error while producing : " + num + "with error : " + e.getMessage());
          e.printStackTrace();
        }
//        System.out.println("Produced : " + num);
      }
    }
  }

  static class Consumer implements Runnable {

    private final BlockingBoundedQueue<Integer> queue;

    Consumer(BlockingBoundedQueue<Integer> queue) {
      this.queue = queue;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Integer item = queue.poll();
//          Thread.sleep(1000);
//          System.out.println("Consumed : " + item);
        } catch (InterruptedException e) {
          System.out.println("Error while consuming with error : " + e.getMessage());
          e.printStackTrace();
        }
      }
    }
  }

  static class ProducerBlockingUnbounded implements Runnable {
    private final BlockingUnboundedQueue<Integer> queue;

    ProducerBlockingUnbounded(BlockingUnboundedQueue<Integer> queue) {
      this.queue = queue;
    }


    @Override
    public void run() {
      while(true) {
        int num = (int)(Math.random() * 1000 + 23);
//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
        queue.offer(num);
        //        System.out.println("Produced : " + num);
      }
    }
  }

  static class ConsumerBlockingUnbounded implements Runnable {

    private final BlockingUnboundedQueue<Integer> queue;

    ConsumerBlockingUnbounded(BlockingUnboundedQueue<Integer> queue) {
      this.queue = queue;
    }

    @Override
    public void run() {
      while (true) {
        try {
          Integer item = queue.poll();
//          Thread.sleep(10000);
//          System.out.println("Consumed : " + item);
        } catch (InterruptedException e) {
          System.out.println("Error while consuming with error : " + e.getMessage());
          e.printStackTrace();
        }
      }
    }
  }

}
