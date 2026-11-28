package net.augustus.modules.modes.anticheat;

import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.ModuleMode;

public enum FlagCertainty {
   CERTAIN("certain", "!!", 960),
   VERY_LIKELY("very likely", "?!", 160),
   LIKELY("likely", "?", 80),
   PROBABLE("probable", "??", 40),
   NONE("none", "-", 0),
   ALMOST_CERTAIN("almost certain", "!", 320),
   MAYBE("maybe", "-", 10);

   private final String label;
   private final String symbol;
   private final int weight;

   public String getLabel() {
      return this.label;
   }

   public static int sumWeights(FlagCertainty[] flagCertaintys) {
      int n = 0;

      for (FlagCertainty flagCertainty : flagCertaintys) {
         n += flagCertainty.getWeight();
      }

      return n;
   }

   private FlagCertainty(String str, String str2, int n) {
      this.label = str;
      this.symbol = str2;
      this.weight = n;
   }

   public static FlagCertainty fromWeight(int n) {
      FlagCertainty flagCertainty = NONE;

      for (FlagCertainty flagCertainty2 : values()) {
         if (flagCertainty2.weight > flagCertainty.weight && flagCertainty2.weight <= n) {
            flagCertainty = flagCertainty2;
         }
      }

      return flagCertainty;
   }

   public static List decomposeWeight(int n) {
      ArrayList list = new ArrayList();

      while (n > MAYBE.getWeight()) {
         FlagCertainty flagCertainty = fromWeight(n);
         list.add(flagCertainty);
         n -= flagCertainty.getWeight();
      }

      return list;
   }

   public boolean isAtLeast(FlagCertainty flagCertainty) {
      return this.getWeight() >= flagCertainty.getWeight();
   }

   public int getWeight() {
      return this.weight;
   }

   public String getSymbol() {
      return this.symbol;
   }
}
