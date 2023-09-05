package dataStructureDesign.HashMap;

import java.util.LinkedList;

public class DeprecatedOldHashMap<K, V> {

  private static class HashMapNode<K, V> {

    K key;
    V value;

    HashMapNode(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  private int size;
  private LinkedList<HashMapNode<K, V>>[] buckets;
  private static final int INITIAL_CAPACITY = 4;

  public DeprecatedOldHashMap() {
    initBuckets(INITIAL_CAPACITY);
    size = 0;
  }

  private void initBuckets(int capacity) {
    buckets = new LinkedList[capacity];
    for (int bucketIndex = 0; bucketIndex < capacity; bucketIndex++) {
      buckets[bucketIndex] = new LinkedList<>();
    }
  }

  private int hashFunction(K key) {
    int hashCode = key.hashCode();
    return Math.abs(hashCode) % buckets.length;
  }

  public void put(K key, V value) {
    int bucketIndex = hashFunction(key);
    int directIndex = getIndexWithinBucket(key, bucketIndex);

    if (directIndex != -1) {
      HashMapNode<K, V> node = buckets[bucketIndex].get(directIndex);
      node.value = value;
    } else {
      HashMapNode<K, V> node = new HashMapNode<>(key, value);
      buckets[bucketIndex].add(node);
      size++;
    }

    double loadFactor = size * 1.0 / buckets.length;
    if (loadFactor > 0.75) {
      rehash();
    }
  }

  private int getIndexWithinBucket(K key, int bucketIndex) {
    int directIndex = 0;
    for (HashMapNode<K, V> node : buckets[bucketIndex]) {
      if (node.key.equals(key)) {
        return directIndex;
      }
      directIndex++;
    }
    return -1;
  }

  private void rehash() {
    LinkedList<HashMapNode<K, V>>[] oldBucket = buckets;
    initBuckets(oldBucket.length * 2);
    size = 0;

    for (LinkedList<HashMapNode<K, V>> bucket : oldBucket) {
      for (HashMapNode<K, V> node : bucket) {
        put(node.key, node.value);
      }
    }
  }

  public boolean containsKey(K key) {
    int bucketIndex = hashFunction(key);
    int directIndex = getIndexWithinBucket(key, bucketIndex);

    return directIndex != -1;
  }

  public V get(K key) {
    int bucketIndex = hashFunction(key);
    int directIndex = getIndexWithinBucket(key, bucketIndex);

    if (directIndex != -1) {
      HashMapNode<K, V> node = buckets[bucketIndex].get(directIndex);
      return node.value;
    }

    return null;
  }

  public V remove(K key) {
    int bucketIndex = hashFunction(key);
    int directIndex = getIndexWithinBucket(key, bucketIndex);

    if (directIndex != -1) {
      HashMapNode<K,V> node = buckets[bucketIndex].remove(directIndex);
      size--;
      return node.value;
    }

    return null;
  }

  public int size() {
    return size;
  }
}
