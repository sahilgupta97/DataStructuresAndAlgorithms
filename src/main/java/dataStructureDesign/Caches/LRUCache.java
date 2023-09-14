package dataStructureDesign.Caches;


import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {

  private final Map<K, DoublyLinkedListNode> cacheTable;
  private final DoublyLinkedList lruList;
  private final int capacity;
  private int size;

  public LRUCache(int capacity) {
    this.cacheTable = new HashMap<>();
    this.lruList = new DoublyLinkedList();
    this.capacity = capacity;
  }

  public V get(K key) {
    DoublyLinkedListNode valueNode = cacheTable.get(key);
    if (valueNode == null) {
      return null;
    }
    lruList.setHeadTo(valueNode);
    return valueNode.getValue();
  }

  public void set(K key, V value) {
    DoublyLinkedListNode valueNode = cacheTable.get(key);
    if (valueNode == null) {
      if (size == capacity) {
        evictLeastRecent();
      } else {
        size++;
      }
      valueNode = new DoublyLinkedListNode(key, value);
      cacheTable.put(key, valueNode);
    } else {
      valueNode.setValue(value);
    }

    lruList.setHeadTo(valueNode);
  }

  private void evictLeastRecent() {
    DoublyLinkedListNode nodeToRemove = lruList.removeTail();
    if (nodeToRemove != null) {
      cacheTable.remove(nodeToRemove.getKey());
    }
  }

  private class DoublyLinkedList {

    private final DoublyLinkedListNode dummyHead;
    private final DoublyLinkedListNode dummyTail;

    DoublyLinkedList() {
      this.dummyHead = new DoublyLinkedListNode();
      this.dummyTail = new DoublyLinkedListNode();
      this.dummyHead.next = this.dummyTail;
      this.dummyTail.prev = this.dummyHead;
    }

    public void setHeadTo(DoublyLinkedListNode valueNode) {
      valueNode.selfDetach();

      DoublyLinkedListNode oldHead = dummyHead.getNext();
      dummyHead.setNext(valueNode);

      valueNode.setNext(oldHead);
      valueNode.setPrev(dummyHead);

      oldHead.setPrev(valueNode);
    }

    public DoublyLinkedListNode removeTail() {
      if (dummyTail.getPrev() == dummyHead) {
        return null;
      }

      DoublyLinkedListNode nodeToRemove = dummyTail.getPrev();
      nodeToRemove.selfDetach();
      return nodeToRemove;
    }
  }

  private class DoublyLinkedListNode {

    private K key;
    private V value;
    private DoublyLinkedListNode next;
    private DoublyLinkedListNode prev;

    public DoublyLinkedListNode() {
    }

    public DoublyLinkedListNode(K key, V value) {
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

    public DoublyLinkedListNode getNext() {
      return next;
    }

    public void setNext(DoublyLinkedListNode next) {
      this.next = next;
    }

    public DoublyLinkedListNode getPrev() {
      return prev;
    }

    public void setPrev(DoublyLinkedListNode prev) {
      this.prev = prev;
    }

    public void selfDetach() {
      if (this.getNext() != null) {
        this.getNext().setPrev(this.getPrev());
      }

      if (this.getPrev() != null) {
        this.getPrev().setNext(this.getNext());
      }

      this.setNext(null);
      this.setPrev(null);
    }
  }
}
