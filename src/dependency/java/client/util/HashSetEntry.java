package client.util;

import java.util.HashSet;
import java.util.List;

public class HashSetEntry {
   public final HashSet hashSet = new HashSet();
   public final String labelText;

   public String getLabelText() {
      return this.labelText;
   }

   public boolean matchesText(String text) {
      return this.hashSet.contains(text.toLowerCase());
   }

   public HashSetEntry(List list, String text) {
      for (Object text2Obj : list) {
         String text2 = (String)text2Obj;
         this.hashSet.add(text2.toLowerCase());
      }

      this.labelText = text;
   }
}
