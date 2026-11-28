package net.augustus.utils.text;

import java.util.HashMap;
import java.util.Map;

public class StringTrieNode {
   public Map children = new HashMap();
   public String word;
   public boolean endOfWord = false;

   public StringTrieNode() {
      this.word = null;
   }
}
