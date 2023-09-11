package dataStructureDesign.RateLimiter;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Uses concurrent hashmap and reentrant lock (avoiding synchronized due to its inability to work on real
 * scale multithreaded applications), taking lock on user level.
 */
public class RateLimiter {

  // Limiter table having <K, V> as <UserId, RateCounter>
  private final Map<String, RateCounter> limiterTable;

  // time limits in milli seconds
  private final int RATE_LIMIT;
  private final int TIME_LIMIT_MILLIS;

  private static final int DEFAULT_RATE_LIMIT = 3;
  private static final int DEFAULT_TIME_LIMIT_MILLIS = 1000;

  public RateLimiter() {
    this.limiterTable = new ConcurrentHashMap<>();
    this.RATE_LIMIT = DEFAULT_RATE_LIMIT;
    this.TIME_LIMIT_MILLIS = DEFAULT_TIME_LIMIT_MILLIS;
  }

  public RateLimiter(int rateLimit, int timeLimitMillis) {
    this.limiterTable = new ConcurrentHashMap<>();
    this.RATE_LIMIT = rateLimit;
    this.TIME_LIMIT_MILLIS = timeLimitMillis;
  }

  public Response isAllowed(Request request) {
    stampRequestWithCurrentTimeStamp(request);
    String userId = request.getUserId();

    RateCounter rateCounter = limiterTable
        .computeIfAbsent(userId, s -> new RateCounter(RATE_LIMIT, TIME_LIMIT_MILLIS));
    boolean isHitSuccess = rateCounter.hit(request.getRequestTimestamp());

    if (isHitSuccess) {
      return new Response(200, "Request served successfully");
    }

    return new Response(429, "Too Many Requests");
  }

  private void stampRequestWithCurrentTimeStamp(Request request) {
    request.setRequestTimestamp(Instant.now());
  }

  private static class RateCounter {

    // Sliding window with counters to improve memory usage
    private final Deque<RequestInfo> deque;
    private int totalHits;
    private final ReentrantLock windowLock;

    private final int RATE_LIMIT;
    private final long TIME_LIMIT_MILLIS;

    public RateCounter(int rateLimit, long timeLimitMillis) {
      this.deque = new ArrayDeque<>();
      this.totalHits = 0;
      this.windowLock = new ReentrantLock();

      this.RATE_LIMIT = rateLimit;
      this.TIME_LIMIT_MILLIS = timeLimitMillis;
    }

    public boolean hit(Instant timestamp) {
      windowLock.lock();
      try {
        removeStaleEntries(timestamp);

        if (totalHits < RATE_LIMIT) {
          addEntry(timestamp);
          return true;
        }

        return false;
      } finally {
        windowLock.unlock();
      }
    }

    private void removeStaleEntries(Instant timestamp) {
      while (!deque.isEmpty()) {
        RequestInfo oldestEntry = deque.peekLast();
        Duration durationGap = Duration.between(oldestEntry.getTimestamp(), timestamp);
        if (durationGap.toMillis() >= TIME_LIMIT_MILLIS) {
          deque.pollLast();
          totalHits -= oldestEntry.counter;
        } else {
          break;
        }
      }
    }

    private void addEntry(Instant timestamp) {
      RequestInfo requestInfo;
      if (deque.isEmpty() || (!deque.peekFirst().getTimestamp().equals(timestamp))) {
        requestInfo = new RequestInfo(1, timestamp);
      } else {
        requestInfo = deque.pollFirst();
        requestInfo.setCounter(requestInfo.getCounter() + 1);
      }
      deque.offerFirst(requestInfo);
      totalHits += 1;
    }

    private static class RequestInfo {

      private int counter;
      private Instant timestamp;

      public RequestInfo(int counter, Instant timestamp) {
        this.counter = counter;
        this.timestamp = timestamp;
      }

      public int getCounter() {
        return counter;
      }

      public void setCounter(int counter) {
        this.counter = counter;
      }

      public Instant getTimestamp() {
        return timestamp;
      }

      public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
      }
    }
  }
}
