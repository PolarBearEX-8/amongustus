package client.setting;

import java.util.ArrayList;
import java.util.List;

public enum BridgeOptionType {
    ALMOST_CERTAIN("almost certain", "!", 320),
    PROBABLE("probable", "??", 40),
    CERTAIN("certain", "!!", 960),
    MAYBE("maybe", "-", 10),
    VERY_LIKELY("very likely", "?!", 160),
    NONE("none", "-", 0),
    LIKELY("likely", "?", 80);

   public final int count;
   public final String labelText;
   public final String labelText2;

   public String getLabelText2() {
      return this.labelText2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public static List resolveList(int count2) {
      int count3 = count2;
      ArrayList list = new ArrayList();

      while (count3 > MAYBE.getCount()) {
         BridgeOptionType bridgeOptionType = resolveBridgeOptionType(count3);
         list.add(bridgeOptionType);
         count3 -= bridgeOptionType.getCount();
      }

      return list;
   }

   public static BridgeOptionType resolveBridgeOptionType(int count2) {
      BridgeOptionType bridgeOptionType = NONE;

      for (BridgeOptionType bridgeOptionType2 : values()) {
         if (bridgeOptionType2.count > bridgeOptionType.count && bridgeOptionType2.count <= count2) {
            bridgeOptionType = bridgeOptionType2;
         }
      }

      return bridgeOptionType;
   }

   public boolean matchesBridgeOptionType(BridgeOptionType bridgeOptionType) {
      return this.getCount() >= bridgeOptionType.getCount();
   }

   public static int computeCount(BridgeOptionType[] bridgeOptionTypeArray) {
      int count2 = 0;

      for (BridgeOptionType bridgeOptionType : bridgeOptionTypeArray) {
         count2 += bridgeOptionType.getCount();
      }

      return count2;
   }

   public int getCount() {
      return this.count;
   }

   private BridgeOptionType(String text, String text2, int count2) {
      this.labelText2 = text;
      this.labelText = text2;
      this.count = count2;
   }


}
