package client.util;

public final class ControllerSupport {
   public final int count;

   public int getCount2() {
      return this.count & 0xFF;
   }

   public static ControllerSupport resolveControllerSupport(int count2, int count3, int count4) {
      return resolveControllerSupport(count2, count3, count4, 255);
   }

   public static ControllerSupport resolveControllerSupport(float posX, float posY, float posZ) {
      return resolveControllerSupport(posX, posY, posZ, 1.0F);
   }

   @Override
   public String toString() {
      return String.valueOf(this.count);
   }

   public static ControllerSupport resolveControllerSupport(int count2) {
      return new ControllerSupport(count2);
   }

   @Override
   public int hashCode() {
      return this.count;
   }

   public static ControllerSupport resolveControllerSupport(int count2, int count3, int count4, int count5) {
      return new ControllerSupport((count5 & 0xFF) << 24 | (count2 & 0xFF) << 16 | (count3 & 0xFF) << 8 | count4 & 0xFF);
   }

   public static ControllerSupport resolveControllerSupport(float posX, float posX2, float posY, float posZ) {
      return resolveControllerSupport(
         (int)((double)(posX * 255.0F) + 0.5), (int)((double)(posX2 * 255.0F) + 0.5), (int)((double)(posY * 255.0F) + 0.5), (int)((double)(posZ * 255.0F) + 0.5)
      );
   }

   public int getCountAsIndex() {
      return this.count >> 16 & 0xFF;
   }

   public static int floor(float posX, float posY, float posZ) {
      int count2 = 0;
      int count3 = 0;
      int count4 = 0;
      if (posY == 0.0F) {
         count2 = count3 = count4 = (int)(posZ * 255.0F + 0.5F);
      } else {
         float factor = (posX - (float)Math.floor((double)posX)) * 6.0F;
         float factor2 = factor - (float)Math.floor((double)factor);
         float factor3 = posZ * (1.0F - posY);
         float factor4 = posZ * (1.0F - posY * factor2);
         float factor5 = posZ * (1.0F - posY * (1.0F - factor2));
         switch ((int)factor) {
            case 0:
               count2 = (int)(posZ * 255.0F + 0.5F);
               count3 = (int)(factor5 * 255.0F + 0.5F);
               count4 = (int)(factor3 * 255.0F + 0.5F);
               break;
            case 1:
               count2 = (int)(factor4 * 255.0F + 0.5F);
               count3 = (int)(posZ * 255.0F + 0.5F);
               count4 = (int)(factor3 * 255.0F + 0.5F);
               break;
            case 2:
               count2 = (int)(factor3 * 255.0F + 0.5F);
               count3 = (int)(posZ * 255.0F + 0.5F);
               count4 = (int)(factor5 * 255.0F + 0.5F);
               break;
            case 3:
               count2 = (int)(factor3 * 255.0F + 0.5F);
               count3 = (int)(factor4 * 255.0F + 0.5F);
               count4 = (int)(posZ * 255.0F + 0.5F);
               break;
            case 4:
               count2 = (int)(factor5 * 255.0F + 0.5F);
               count3 = (int)(factor3 * 255.0F + 0.5F);
               count4 = (int)(posZ * 255.0F + 0.5F);
               break;
            case 5:
               count2 = (int)(posZ * 255.0F + 0.5F);
               count3 = (int)(factor3 * 255.0F + 0.5F);
               count4 = (int)(factor4 * 255.0F + 0.5F);
         }
      }

      return 0xFF000000 | count2 << 16 | count3 << 8 | count4;
   }

   public static ControllerSupport resolveControllerSupportByFloat(float posX, float posY, float posZ) {
      return resolveControllerSupportByIndex(floor(posX, posY, posZ));
   }

   public int getCount4() {
      return this.count >> 8 & 0xFF;
   }

   public int getCount() {
      return this.count >> 24 & 0xFF;
   }

   public ControllerSupport resolveControllerSupport(double offset) {
      int countAsIndex = this.getCountAsIndex();
      int count2 = this.getCount4();
      int count3 = this.getCount2();
      int count4 = (int)(1.0 / (1.0 - 1.0 / offset));
      if (countAsIndex == 0 && count2 == 0 && count3 == 0) {
         return resolveControllerSupport(count4, count4, count4, this.getCount());
      } else {
         if (countAsIndex > 0 && countAsIndex < count4) {
            countAsIndex = count4;
         }

         if (count2 > 0 && count2 < count4) {
            count2 = count4;
         }

         if (count3 > 0 && count3 < count4) {
            count3 = count4;
         }

         return resolveControllerSupport(
            Math.min((int)((double)countAsIndex / (1.0 / offset)), 255),
            Math.min((int)((double)count2 / (1.0 / offset)), 255),
            Math.min((int)((double)count3 / (1.0 / offset)), 255),
            this.getCount()
         );
      }
   }

   public static ControllerSupport resolveControllerSupportByIndex(int count2) {
      return new ControllerSupport(0xFF000000 | count2);
   }

   public ControllerSupport resolveControllerSupportByDouble(double offset) {
      return resolveControllerSupport(
         Math.max((int)((double)this.getCountAsIndex() * (1.0 / offset)), 0),
         Math.max((int)((double)this.getCount4() * (1.0 / offset)), 0),
         Math.max((int)((double)this.getCount2() * (1.0 / offset)), 0),
         this.getCount()
      );
   }

   public int getCount3() {
      return this.count;
   }

   private ControllerSupport(int count2) {
      this.count = count2;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else {
         return other != null && this.getClass() == other.getClass() ? this.count == ((ControllerSupport)other).count : false;
      }
   }
}
