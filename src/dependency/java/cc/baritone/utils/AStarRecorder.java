package cc.baritone.utils;

import java.util.Arrays;

public final class AStarRecorder implements AStarSamplerService {
   public static final int count = 1024;
   public AStarSampler[] aStarSamplerArray;
   public int count2 = 0;

   public AStarRecorder(int count3) {
      this.aStarSamplerArray = new AStarSampler[count3];
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public final void applyAStarSamplerByAStarSampler(AStarSampler aStarSampler) {
      if (this.count2 >= this.aStarSamplerArray.length - 1) {
         this.aStarSamplerArray = Arrays.copyOf(this.aStarSamplerArray, this.aStarSamplerArray.length << 1);
      }

      this.count2++;
      aStarSampler.count4 = this.count2;
      this.aStarSamplerArray[this.count2] = aStarSampler;
      this.applyAStarSampler(aStarSampler);
   }

   public AStarRecorder() {
      this(1024);
   }

   @Override
   public final AStarSampler getAStarSampler() {
      if (this.count2 == 0) {
         throw new IllegalStateException();
      } else {
         AStarSampler aStarSampler = this.aStarSamplerArray[1];
         AStarSampler aStarSampler2 = this.aStarSamplerArray[this.count2];
         this.aStarSamplerArray[1] = aStarSampler2;
         aStarSampler2.count4 = 1;
         this.aStarSamplerArray[this.count2] = null;
         this.count2--;
         aStarSampler.count4 = -1;
         if (this.count2 < 2) {
            return aStarSampler;
         } else {
            int count3 = 1;
            int count4 = 2;
            double value = aStarSampler2.value3;

            do {
               AStarSampler aStarSampler3 = this.aStarSamplerArray[count4];
               double value2 = aStarSampler3.value3;
               if (count4 < this.count2) {
                  AStarSampler aStarSampler4 = this.aStarSamplerArray[count4 + 1];
                  double value3 = aStarSampler4.value3;
                  if (value2 > value3) {
                     count4++;
                     value2 = value3;
                     aStarSampler3 = aStarSampler4;
                  }
               }

               if (value <= value2) {
                  break;
               }

               this.aStarSamplerArray[count3] = aStarSampler3;
               this.aStarSamplerArray[count4] = aStarSampler2;
               aStarSampler2.count4 = count4;
               aStarSampler3.count4 = count3;
               count3 = count4;
            } while ((count4 <<= 1) <= this.count2);

            return aStarSampler;
         }
      }
   }

   @Override
   public final void applyAStarSampler(AStarSampler aStarSampler) {
      int count3 = aStarSampler.count4;
      int count4 = count3 >>> 1;
      double value = aStarSampler.value3;

      for (AStarSampler aStarSampler2 = this.aStarSamplerArray[count4]; count3 > 1 && aStarSampler2.value3 > value; aStarSampler2 = this.aStarSamplerArray[count4]) {
         this.aStarSamplerArray[count3] = aStarSampler2;
         this.aStarSamplerArray[count4] = aStarSampler;
         aStarSampler.count4 = count4;
         aStarSampler2.count4 = count3;
         count3 = count4;
         count4 >>>= 1;
      }
   }

   @Override
   public final boolean isActive() {
      return this.count2 == 0;
   }
}
