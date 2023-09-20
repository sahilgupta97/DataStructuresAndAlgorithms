package dataStructureDesign.TypeAhead;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrieBackedAutocomplete implements AutocompleteSystem {

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

    for (int idx = 0; idx < searchTerms.length; idx++) {
      String searchTerm = searchTerms[idx];
      int frequency = frequencies[idx];

      addSearchTerm(searchTerm, frequency);
    }
  }

  @Override
  public boolean addSearchTerm(String searchTerm, int frequency) {
    TrieNode currentNode = root;
    List<TrieNode> visitedNodes = new ArrayList<>();

    for (int idx = 0; idx < searchTerm.length(); idx++) {
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
    for (TrieNode visitedNode : visitedNodes) {
      visitedNode.rebalanceTopSearchTerms(currentNode, suggestionLimit);
    }
  }

  @Override
  public List<String> getAutocompleteSuggestions(String prefix) {
    TrieNode currentNode = root;

    for (int idx = 0; idx < prefix.length(); idx++) {
      char currentChar = prefix.charAt(idx);
      TrieNode node = currentNode.getChildren().getOrDefault(currentChar, null);
      if (node == null) {
        return new ArrayList<>();
      }
      currentNode = node;
    }

    List<TrieNode> topNodes = currentNode.getTopSearchTerms();
    List<String> topSearchTerms = topNodes.stream().map(TrieNode::getTerm).collect(Collectors.toList());

    return topSearchTerms;
  }

  @Override
  public List<String> inputCharacter(char ch) {
    List<String> topSuggestions = new ArrayList<>();

    if (ch == END_SYMBOL) {
      String searchTerm = inputStreamBuilder.toString();
      addSearchTerm(searchTerm, 1);
      inputStreamBuilder = new StringBuilder();
      inputStreamNode = root;
      return getAutocompleteSuggestions(searchTerm);
    }

    if (inputStreamNode != null) {
      inputStreamNode = inputStreamNode.children.getOrDefault(ch, null);
    }

    if (inputStreamNode == null) {
      return topSuggestions;
    }

    List<TrieNode> topSuggestionNodes = inputStreamNode.getTopSearchTerms();
    topSuggestions = topSuggestionNodes.stream().map(TrieNode::getTerm).collect(Collectors.toList());

    return topSuggestions;
  }

  private static class TrieNode implements Comparable<TrieNode> {

    private final Map<Character, TrieNode> children;
    private final List<TrieNode> topSearchTerms;
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

    public List<TrieNode> getTopSearchTerms() {
      return this.topSearchTerms;
    }

    public void rebalanceTopSearchTerms(TrieNode currentNode, int suggestionLimit) {
      if (!this.topSearchTerms.contains(currentNode)) {
        this.topSearchTerms.add(currentNode);
      }

      Collections.sort(this.topSearchTerms);
      if (this.topSearchTerms.size() > suggestionLimit) {
        this.topSearchTerms.remove(this.topSearchTerms.size() - 1);
      }
    }

    @Override
    public int compareTo(TrieNode otherNode) {
      if (this.count == otherNode.count) {
        return this.getTerm().compareTo(otherNode.getTerm());
      }
      return otherNode.count - this.count;
    }
  }


}
