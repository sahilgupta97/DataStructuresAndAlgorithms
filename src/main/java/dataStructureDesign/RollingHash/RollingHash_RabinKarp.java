package dataStructureDesign.RollingHash;

import java.util.HashSet;
import java.util.Set;

public class RollingHash_RabinKarp {
  /**
   * Suppose we want to find the length of longest repeating substring in a given string.
   * */
  public int longestRepeatingSubstring(String s) {
    // For this problem, Overall TIME : O(Nlogn) SPACE : O(N) but for only rabin karp it takes O(s + p) where s and p are lengths of string and pattern.
    int n = s.length();
    int minLength = 0, maxLength = n - 1;
    int result = 0;

    while(minLength <= maxLength) {
      int midLength = minLength + (maxLength - minLength) / 2;

      boolean found = findRepeatingSubstringOfLength(midLength, s);
      if(found) {
        result = midLength;
        minLength = midLength + 1;
      } else {
        maxLength = midLength - 1;
      }
    }

    return result;
  }

  /**
   * ROLLING HASH
   *    Multiplier also plays an important part in rollong hash which actually denoted the ordering of the characters in the string.
   *    Ideally we prefer picking the multiplier value to be equal or greater than the largest value in our string so that there are no hash collisions.
   *    In case of lowercase alphabets, we can use 26 as the multiplier.
   *
   *    abcde, suppose window size is 4
   *    abcd =   1*10^3 + 2*10^2 + 3*10^1 + 4*10^0
   *    bcde =            2*10^3 + 3*10^2 + 4*10^1 + 5*10^0
   *    same as -1*10^3 + 2*10^3 + 3*10^2 + 4*10^1 + 5*10^0
   *    i.e.     (prevHash - 1*10^3)*10 + newChar
   */
  public static final long MULTIPLIER = 26;
  public static final long MOD = 1000000007; // (greater the mod, lesser the chances of collision in rolling hash)
  private boolean findRepeatingSubstringOfLength(int l, String s) {
    // TIME : O(N)  SPACE : O(N)
    if(l >= s.length()) {
      // if no substring can be considered, we do need complete string because that will always be true.
      return false;
    }

    Set<Long> seenTable = new HashSet<>();

    // calculate first window hash
    long rollingHash = 0, rollingMultiplier = 1;
    for(int idx = l - 1; idx >= 0; idx--) {
      char ch = s.charAt(idx);
      int chValue = (ch - 'a') + 1;

      rollingHash = (rollingHash + (chValue * rollingMultiplier) % MOD) % MOD;
      if(idx != 0) {
        // this should stop at last value of the window.
        rollingMultiplier = (rollingMultiplier * MULTIPLIER) % MOD;
      }
    }
    seenTable.add(rollingHash);

    // start window sliding and see if a match
    for(int idx = l; idx < s.length(); idx++) {
      int discardIdx = idx - l;
      char discardCh = s.charAt(discardIdx);
      int discardChValue = (discardCh - 'a') + 1;
      rollingHash = (rollingHash - (rollingMultiplier * discardChValue) % MOD + MOD) % MOD;

      rollingHash = (rollingHash * MULTIPLIER) % MOD;

      char ch = s.charAt(idx);
      int chValue = (ch - 'a') + 1;
      rollingHash = (rollingHash + chValue) % MOD;

      if(seenTable.contains(rollingHash)) {
        return true;
      }
      seenTable.add(rollingHash);
    }

    return false;
  }

  /**
   private boolean findRepeatingSubstringOfLength(int l, String s) {
   // TIME : O(N ^ 2)  SPACE : O(N ^ 2)
   Set<String> seenTable = new HashSet<>();

   for(int startIdx = 0; startIdx <= s.length() - l; startIdx++) {
   String subStr = s.substring(startIdx, startIdx + l);
   if(seenTable.contains(subStr)) {
   return true;
   }

   seenTable.add(subStr);
   }

   return false;
   }
   */

  /**
   private boolean findRepeatingSubstringOfLength(int l, String s) {
   // TIME : O(N ^ 2)  SPACE : O(N)
   Set<Integer> seenTable = new HashSet<>();

   for(int startIdx = 0; startIdx <= s.length() - l; startIdx++) {
   String subStr = s.substring(startIdx, startIdx + l);
   Integer subStrHash = subStr.hashCode();
   if(seenTable.contains(subStrHash)) {
   return true;
   }

   seenTable.add(subStrHash);
   }

   return false;
   }
   */
}
