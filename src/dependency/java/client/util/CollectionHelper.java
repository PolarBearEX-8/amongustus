package client.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CollectionHelper {
   public static Set set = new HashSet();
   public static Set set2 = new HashSet();

   public static boolean matchesTextByText(String text) {
      Set set3 = set;
      return matchesText(text, set3);
   }

   private static boolean matchesText(String text, Collection collection) {
      String text2 = text.toLowerCase();

      for (String text3 : (Iterable<String>)(collection)) {
         if (text2.startsWith(text3)) {
            return true;
         }
      }

      return false;
   }

   public static boolean matchesText(String text) {
      Set set3 = set2;
      if (!matchesText(text, set3)) {
         Set set4 = set;
         if (!matchesText(text, set4)) {
            return false;
         }
      }

      return true;
   }

   static {
      String[] textArray = new String[]{
         "https://cdn.discordapp.com/",
         "https://dl.cosmeticsmod.com/",
         "https://cl.cosmeticsmod.com/",
         "http://logo.cosmeticsmod.com/",
         "http://dl.cosmeticsmod.com/",
         "https://media.discordapp.net/"
      };
      set.add(textArray[2]);
      set.add(textArray[1]);
      set.add(textArray[4]);
      set.add(textArray[3]);
      set2.add(textArray[0]);
      set2.add(textArray[5]);
   }
}
