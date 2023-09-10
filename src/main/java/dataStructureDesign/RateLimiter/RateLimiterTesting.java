package dataStructureDesign.RateLimiter;

import java.time.Instant;

public class RateLimiterTesting {
  public static void main(String[] args) throws InterruptedException {
    RateLimiter rateLimiter = new RateLimiter(3, 1);

    String[] users = {"user1"}; //, "user2", "user3"
    int numThreads = 5;

    for (int i = 0; i < numThreads; i++) {
      new Thread(() -> {
        for (int j = 0; j < 30; j++) {
          for (String user : users) {
            Request request = new Request(user, Instant.now());
//            System.out.println("****Hitting " + user + " with Thread " + Thread.currentThread().getId() + " at time : " + request.getRequestTimestamp());
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            if (rateLimiter.isAllowed(request).getStatusCode() == 200) {
              System.out.println(request.getRequestTimestamp() + " Thread " + Thread.currentThread().getId()
                  + ": Processing Request : " + request);
            } else {
              System.out.println(request.getRequestTimestamp() + " Thread " + Thread.currentThread().getId()
                  + ": Rate Limit Exceeded for Request: " + request);
            }
          }
        }
      }).start();
    }
  }
}
