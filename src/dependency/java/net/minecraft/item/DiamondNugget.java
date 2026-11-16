package net.minecraft.item;

import client.setting.MovementWeightProperty;
import client.util.ContextStringEntry;

public class DiamondNugget {
   public final MovementWeightProperty movementWeightProperty;

   private int computeCount(int count) {
      return this.length(count) + 2;
   }

   private int computeCountByIndex(int count) {
      int count2 = 0;
      count2 = this.computeCount(count);
      return count2 % ContextStringEntry.computeCount(this.movementWeightProperty) == 0
         ? 0
         : ContextStringEntry.computeCount(this.movementWeightProperty) - count2 % ContextStringEntry.computeCount(this.movementWeightProperty);
   }

   private void applyByteArrayByByteArray(byte[] byteValueArray, int count) {
      int count2 = count / 8;
   }

   private int length(int count) {
      return count + "01".length();
   }

   public void applyByteArray(byte[] byteValueArray, int count) {
      int count2 = 0;
      int count3 = 0;
      count2 = this.length(count);
      count3 = this.computeCountByIndex(count);
      this.applyByteArrayByByteArray(byteValueArray, count2);
      int count4 = count2 + 1 + count3;
      this.applyByteArrayByByteArray(byteValueArray, count4);
   }

   public DiamondNugget(MovementWeightProperty movementWeightProperty2) {
      this.movementWeightProperty = movementWeightProperty2;
   }
}
