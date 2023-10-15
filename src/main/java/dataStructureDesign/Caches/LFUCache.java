package dataStructureDesign.Caches;

import java.util.HashMap;
import java.util.Map;

public class LFUCache<K, V> implements Cache<K, V> {

  int capacity;
  int size;
  int minimumFrequency;
  Map<K, DoublyLinkedListNode> cacheTable;
  Map<Integer, DoublyLinkedList> frequencyTable;


  public LFUCache(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.minimumFrequency = 0;
    this.cacheTable = new HashMap<>();
    this.frequencyTable = new HashMap<>();
  }

  @Override
  public V get(K key) {
    DoublyLinkedListNode node = cacheTable.get(key);
    if (node == null) {
      return null;
    }

    updateAccessedElement(node);
    return node.value;
  }

  @Override
  public void set(K key, V value) {
    DoublyLinkedListNode node = cacheTable.get(key);
    if (node == null) {
      node = new DoublyLinkedListNode(key, value);
      size++;
      if (size > capacity) {
        evictLeastFrequent();
      }

      minimumFrequency = 1;

      cacheTable.put(key, node);
      DoublyLinkedList currentFrequencyList = frequencyTable
          .computeIfAbsent(minimumFrequency, l -> new DoublyLinkedList());
      currentFrequencyList.offerFirst(node);
    } else {
      node.value = value;
      updateAccessedElement(node);
    }
  }

  private void evictLeastFrequent() {
    DoublyLinkedList leastFrequentList = frequencyTable.get(minimumFrequency);
    DoublyLinkedListNode leastRecentlyUsedNode = leastFrequentList.removeTail();

    cacheTable.remove(leastRecentlyUsedNode.key);
    size--;
  }

  private void updateAccessedElement(DoublyLinkedListNode node) {
    int currentFrequency = node.frequency;
    DoublyLinkedList currentFrequencyList = frequencyTable.get(node.frequency);
    int newFrequency = currentFrequency + 1;
    if (minimumFrequency == currentFrequency && currentFrequencyList.size == 1) {
      minimumFrequency++;
    }

    node.frequency = newFrequency;

    currentFrequencyList.removeNode(node);

    DoublyLinkedList newFrequencyList = frequencyTable
        .computeIfAbsent(newFrequency, l -> new DoublyLinkedList());
    newFrequencyList.offerFirst(node);
  }

  class DoublyLinkedList {

    int size;
    DoublyLinkedListNode dummyHead;
    DoublyLinkedListNode dummyTail;

    public DoublyLinkedList() {
      this.dummyHead = new DoublyLinkedListNode();
      this.dummyTail = new DoublyLinkedListNode();
      this.dummyHead.previous = this.dummyTail;
      this.dummyTail.next = this.dummyHead;
      this.size = 0;
    }

    public void offerFirst(DoublyLinkedListNode node) {
      node.selfDetach();

      DoublyLinkedListNode oldHead = dummyHead.previous;

      node.previous = oldHead;
      node.next = dummyHead;
      dummyHead.previous = node;

      oldHead.next = node;
      size++;
    }

    public DoublyLinkedListNode removeTail() {
      if (dummyTail.next == dummyHead) {
        return null;
      }

      DoublyLinkedListNode tailNode = dummyTail.next;
      removeNode(tailNode);
      return tailNode;
    }

    public void removeNode(DoublyLinkedListNode node) {
      node.selfDetach();
      size--;
    }
  }

  class DoublyLinkedListNode {

    K key;
    V value;
    int frequency;
    DoublyLinkedListNode next;
    DoublyLinkedListNode previous;

    public DoublyLinkedListNode() {

    }

    public DoublyLinkedListNode(K key, V value) {
      this.key = key;
      this.value = value;
      this.frequency = 1;
      this.next = null;
      this.previous = null;
    }

    public void selfDetach() {
      if (this.next != null) {
        this.next.previous = this.previous;
      }

      if (this.previous != null) {
        this.previous.next = this.next;
      }

      this.next = null;
      this.previous = null;
    }
  }
}