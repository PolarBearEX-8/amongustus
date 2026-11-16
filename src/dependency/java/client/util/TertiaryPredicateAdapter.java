package client.util;

import client.gui.SupplementalGuiScreenAdapter;
import com.google.common.base.Predicate;
import java.net.IDN;

public class TertiaryPredicateAdapter implements Predicate {
   public final SupplementalGuiScreenAdapter supplementalGuiScreenAdapter;

   public boolean matchesText(String text) {
      if (text.length() == 0) {
         return true;
      } else {
         String[] textArray = text.split(":");
         if (textArray.length == 0) {
            return true;
         } else {
            try {
               IDN.toASCII(textArray[0]);
               return true;
            } catch (IllegalArgumentException illegalArgumentException) {
               return false;
            }
         }
      }
   }

   public TertiaryPredicateAdapter(SupplementalGuiScreenAdapter supplementalGuiScreenAdapter2) {
      this.supplementalGuiScreenAdapter = supplementalGuiScreenAdapter2;
   }

   public boolean apply(Object entry) {
      return this.matchesText((String)entry);
   }
}
