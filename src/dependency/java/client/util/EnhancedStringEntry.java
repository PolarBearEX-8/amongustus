package client.util;

import java.util.List;

public class EnhancedStringEntry {
   public final String labelText;
   public final List list;

   public static String resolveText(EnhancedStringEntry enhancedStringEntry) {
      return enhancedStringEntry.labelText;
   }

   public List getList() {
      return this.list;
   }

   public EnhancedStringEntry(String text, List list2) {
      this.labelText = text;
      this.list = list2;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof EnhancedStringEntry)) {
         return false;
      } else {
         EnhancedStringEntry enhancedStringEntry = (EnhancedStringEntry)other;
         return !this.labelText.equals(enhancedStringEntry.labelText) ? false : this.list.equals(enhancedStringEntry.list);
      }
   }

   @Override
   public int hashCode() {
      int hash = this.labelText.hashCode();
      return 31 * hash + this.list.hashCode();
   }
}
