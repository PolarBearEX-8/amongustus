package client.util;

import net.minecraft.util.MathHelper;

public class BaseFloatEntry {
   public final int count;
   public final int count5;
   public final int count3;
   public final int count2;
   public int count4 = -1;
   public float factor;
   public float factor3;
   public float factor2;
   public BaseFloatEntry baseFloatEntry;
   public boolean enabled;

   public static int computeCount(int count6, int count7, int count8) {
      return count7 & 0xFF | (count6 & 32767) << 8 | (count8 & 32767) << 24 | (count6 < 0 ? Integer.MIN_VALUE : 0) | (count8 < 0 ? 32768 : 0);
   }

   public float computeFactorByBaseFloatEntry(BaseFloatEntry baseFloatEntry2) {
      float factor4 = (float)(baseFloatEntry2.count - this.count);
      float factor5 = (float)(baseFloatEntry2.count5 - this.count5);
      float factor6 = (float)(baseFloatEntry2.count3 - this.count3);
      return MathHelper.sin(factor4 * factor4 + factor5 * factor5 + factor6 * factor6);
   }

   public boolean isActive() {
      return this.count4 >= 0;
   }

   public float computeFactor(BaseFloatEntry baseFloatEntry2) {
      float factor4 = (float)(baseFloatEntry2.count - this.count);
      float factor5 = (float)(baseFloatEntry2.count5 - this.count5);
      float factor6 = (float)(baseFloatEntry2.count3 - this.count3);
      return factor4 * factor4 + factor5 * factor5 + factor6 * factor6;
   }

   public BaseFloatEntry(int count6, int count7, int count8) {
      this.count = count6;
      this.count5 = count7;
      this.count3 = count8;
      this.count2 = computeCount(count6, count7, count8);
   }

   @Override
   public boolean equals(Object other) {
      if (!(other instanceof BaseFloatEntry)) {
         return false;
      } else {
         BaseFloatEntry baseFloatEntry2 = (BaseFloatEntry)other;
         return this.count2 == baseFloatEntry2.count2 && this.count == baseFloatEntry2.count && this.count5 == baseFloatEntry2.count5 && this.count3 == baseFloatEntry2.count3;
      }
   }

   @Override
   public String toString() {
      return this.count + ", " + this.count5 + ", " + this.count3;
   }

   @Override
   public int hashCode() {
      return this.count2;
   }
}
