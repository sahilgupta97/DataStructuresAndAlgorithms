package dataStructureDesign.Trie;

public class Trie {

  TrieNode root;

  public Trie() {
    this.root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode currentNode = this.root;
    for (int i = 0; i < word.length(); i++) {
      char currentChar = word.charAt(i);
      currentNode = currentNode.getChildren().computeIfAbsent(currentChar, n -> new TrieNode());
    }

    currentNode.setEndOfWord(true);
  }

  public void delete(String word) {
    delete(root, word, 0);
  }

  /**
   * Returns true if parent should delete the mapping
   */
  private boolean delete(TrieNode currentNode, String word, int index) {
    if (index == word.length()) {
      //when end of word is reached only delete if currrent.endOfWord is true.
      if (!currentNode.isEndOfWord()) {
        return false;
      }
      currentNode.setEndOfWord(false);
      //if currentNode has no other mapping then return true
      return currentNode.getChildren().size() == 0;
    }
    char currentChar = word.charAt(index);
    TrieNode node = currentNode.getChildren().get(currentChar);
    if (node == null) {
      return false;
    }
    boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

    //if true is returned then delete the mapping of character and trienode reference from map.
    if (shouldDeleteCurrentNode) {
      currentNode.getChildren().remove(currentChar);
      //return true if no mappings are left in the map.
      return currentNode.getChildren().size() == 0;
    }
    return false;
  }

  public boolean search(String word) {
    TrieNode currentNode = root;
    for (int i = 0; i < word.length(); i++) {
      char currentChar = word.charAt(i);
      TrieNode nextNode = currentNode.getChildren().getOrDefault(currentChar, null);
      if (nextNode == null) {
        return false;
      }
    }

    return currentNode.isEndOfWord();
  }
}
