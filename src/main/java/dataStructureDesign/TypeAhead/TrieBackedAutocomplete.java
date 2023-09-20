package dataStructureDesign.TypeAhead;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieBackedAutocomplete implements AutocompleteSystem{
  private final TrieNode root;
  private final int suggestionLimit;
  private TrieNode inputStreamNode;
  private StringBuilder inputStreamBuilder;

  public TrieBackedAutocomplete() {
    this.root = new TrieNode();
    this.suggestionLimit = DEFAULT_SUGGESTION_LIMIT;

    this.inputStreamNode = this.root;
    this.inputStreamBuilder = new StringBuilder();
  }

  public TrieBackedAutocomplete(String[] searchTerms, int[] frequencies) {
    this(searchTerms, frequencies, DEFAULT_SUGGESTION_LIMIT);
  }

  public TrieBackedAutocomplete(String[] searchTerms, int[] frequencies, int suggestionLimit) {
    this.root = new TrieNode();
    this.suggestionLimit = suggestionLimit;

    this.inputStreamNode = this.root;
    this.inputStreamBuilder = new StringBuilder();

    for(int idx = 0; idx < searchTerms.length; idx++) {
      String searchTerm = searchTerms[idx];
      int frequency = frequencies[idx];

      addSearchTerm(searchTerm, frequency);
    }
  }

  @Override
  public boolean addSearchTerm(String searchTerm, int frequency) {
    TrieNode currentNode = root;
    List<TrieNode> visitedNodes = new ArrayList<>();

    for(int idx = 0; idx < searchTerm.length(); idx++) {
      char currentChar = searchTerm.charAt(idx);
      currentNode = currentNode.getChildren().computeIfAbsent(currentChar, n -> new TrieNode());
      visitedNodes.add(currentNode);
    }

    currentNode.incrementCount(frequency);
    currentNode.setEndOfTerm(true);
    currentNode.setTerm(searchTerm);
    enrichVisitedNodes(visitedNodes, currentNode, suggestionLimit);

    return false;
  }

  private void enrichVisitedNodes(List<TrieNode> visitedNodes, TrieNode currentNode, int suggestionLimit) {
    for(TrieNode visitedNode : visitedNodes) {
      visitedNode.rebalanceTopSearchTerms(currentNode, suggestionLimit);
    }
  }

  @Override
  public List<String> getAutocompleteSuggestions(String prefix) {
    return null;
  }

  @Override
  public List<String> inputCharacter(char ch) {
    return null;
  }

  private static class TrieNode implements Comparable<TrieNode> {
    private Map<Character, TrieNode> children;
    private List<TrieNode> topSearchTerms;
    private int count;
    private String term;
    private boolean endOfTerm;

    public TrieNode() {
      this.children = new HashMap<>();
      this.topSearchTerms = new ArrayList<>();
      this.count = 0;
      this.endOfTerm = false;
    }

    public Map<Character, TrieNode> getChildren() {
      return this.children;
    }

    public boolean isEndOfTerm() {
      return this.endOfTerm;
    }

    public void setEndOfTerm(boolean isEndOfWord) {
      this.endOfTerm = isEndOfWord;
    }

    public void incrementCount(int count) {
      this.count += count;
    }

    public String getTerm() {
      return this.term;
    }

    public void setTerm(String term) {
      this.term = term;
    }

    public void rebalanceTopSearchTerms(TrieNode currentNode, int suggestionLimit) {
      if(!this.topSearchTerms.contains(currentNode)) {
        this.topSearchTerms.add(currentNode);
      }

      Collections.sort(this.topSearchTerms);
      if(this.topSearchTerms.size() > suggestionLimit) {
        this.topSearchTerms.remove(this.topSearchTerms.size() - 1);
      }
    }

    @Override
    public int compareTo(TrieNode otherNode) {
      if(this.count == otherNode.count) {
        return this.getTerm().compareTo(otherNode.getTerm());
      }
      return otherNode.count - this.count;
    }
  }


}
