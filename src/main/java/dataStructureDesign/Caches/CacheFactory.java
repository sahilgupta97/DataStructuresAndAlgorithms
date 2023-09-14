package dataStructureDesign.Caches;

public class CacheFactory {

  public static <K, V> Cache<K, V> createCache(CacheEvictionPolicy cacheEvictionPolicy, int capacity) {
    Cache<K, V> cache = null;

    if (cacheEvictionPolicy.equals(CacheEvictionPolicy.LRU)) {
      cache = new LRUCache<>(capacity);
    } else if (cacheEvictionPolicy.equals(CacheEvictionPolicy.LFU)) {
      cache = new LFUCache<>(capacity);
    }

    return cache;
  }

}
