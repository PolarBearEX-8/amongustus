package client.util;

import java.util.BitSet;

public class BitSetHolder {
   public static boolean enabled;
   public static BitSet bitSet2 = new BitSet();
   public static BitSet bitSet = new BitSet();

   public static void setByIndex(int byIndex) {
      if (byIndex >= 0) {
         bitSet.set(byIndex);
      }
   }

   public static void clear2() {
      bitSet.clear();
   }

   public static void set(int count) {
      if (count >= 0) {
         bitSet2.set(count);
      }
   }

   public static boolean get(int count) {
      return count < 0 ? false : bitSet.get(count);
   }

   public static void clear() {
      bitSet2.clear();
   }

   public static void updateState() {
      enabled = BuildManager.getGameSettings().state85;
   }

   public static boolean isActive() {
      return enabled && !PreEndHandManager.enabled12;
   }

   public static void or(BitSet bitSet3) {
      if (bitSet3 != null) {
         bitSet2.or(bitSet3);
      }
   }

   public static boolean getByIndex(int count) {
      return count < 0 ? false : bitSet2.get(count);
   }
}
