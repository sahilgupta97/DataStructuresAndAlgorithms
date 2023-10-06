package dataStructureDesign.HashMap;

import java.util.LinkedList;

public class HashMap<K, V> {

  private LinkedList<HashMapNode<K, V>>[] buckets;
  private int size;

  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  private static final double LOAD_FACTOR = 0.75;

  public HashMap() {
    initBuckets(DEFAULT_INITIAL_CAPACITY);
    this.size = 0;
  }

  private void initBuckets(int defaultInitialCapacity) {
    this.buckets = new LinkedList[defaultInitialCapacity];
    for (int bucketIdx = 0; bucketIdx < this.buckets.length; bucketIdx++) {
      this.buckets[bucketIdx] = new LinkedList<>();
    }
  }

  public boolean put(K key, V value) {
    int bucketIdx = hashFunction(key);
    HashMapNode<K, V> node = getNodeWithinBucket(bucketIdx, key);

    if (node == null) {
      HashMapNode<K, V> newNode = new HashMapNode<>(key, value);
      buckets[bucketIdx].offer(newNode);
      size++;
    } else {
      node.setValue(value);
    }

    double currentLoad = getCurrentLoad();
    if (currentLoad > LOAD_FACTOR) {
      resize();
    }

    return true;
  }

  private void resize() {
    LinkedList<HashMapNode<K, V>>[] oldBuckets = buckets;
    buckets = new LinkedList[oldBuckets.length * 2];
    for(int idx = 0; idx < buckets.length; idx++) {
      buckets[idx] = new LinkedList<>();
    }
    size = 0;

    for (LinkedList<HashMapNode<K, V>> bucket : oldBuckets) {
      for (HashMapNode<K, V> node : bucket) {
        put(node.getKey(), node.getValue());
      }
    }
  }

  private int hashFunction(K key) {
    int hashCode = key.hashCode();
    return Math.abs(hashCode) % buckets.length;
  }

  private double getCurrentLoad() {
    return size * 1.0 / buckets.length;
  }

  public V get(K key) {
    int bucketIdx = hashFunction(key);
    HashMapNode<K, V> node = getNodeWithinBucket(bucketIdx, key);

    if (node == null) {
      return null;
    }

    return node.getValue();
  }

  private HashMapNode<K, V> getNodeWithinBucket(int bucketIdx, K key) {
    if (bucketIdx >= buckets.length) {
      return null;
    }

    LinkedList<HashMapNode<K, V>> bucket = buckets[bucketIdx];
    for (HashMapNode<K, V> node : bucket) {
      if (node.key.equals(key)) {
        return node;
      }
    }

    return null;
  }

  public boolean containsKey(K key) {
    int bucketIdx = hashFunction(key);
    HashMapNode<K, V> node = getNodeWithinBucket(bucketIdx, key);

    return node != null;
  }

  public V remove(K key) {
    int bucketIdx = hashFunction(key);
    HashMapNode<K, V> node = getNodeWithinBucket(bucketIdx, key);

    if (node == null) {
      return null;
    }

    buckets[bucketIdx].remove(node);
    size--;
    return node.getValue();
  }

  public int size() {
    return size;
  }

  private static class HashMapNode<K, V> {

    K key;
    V value;

    HashMapNode(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public void setKey(K key) {
      this.key = key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }
  }

}
