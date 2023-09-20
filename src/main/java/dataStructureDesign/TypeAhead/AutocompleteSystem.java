package dataStructureDesign.TypeAhead;

import java.util.List;

public interface AutocompleteSystem {

  int DEFAULT_SUGGESTION_LIMIT = 5;
  char END_SYMBOL = '#';

  boolean addSearchTerm(String searchTerm, int frequency);

  List<String> getAutocompleteSuggestions(String prefix);

  /**
   * This could be optional method as we could choose to call getAutocompleteSuggestions.
   * The character '#' is reserved as a special character to indicate end of char stream. Hence, at this point
   * the system will return empty list and add the inputted sentence inside itself.
   *
   * @param ch input char typed by the user
   * @return list of popular search terms
   */
  List<String> inputCharacter(char ch);

}
