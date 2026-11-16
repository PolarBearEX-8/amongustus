package client.util;

import client.setting.Setting;

public abstract class StandardByteArrayEntry {
   public boolean enabled;
   public byte[] byteFlagArray;
   public int count3;
   public long timestamp;
   public int count2;
   public int count4;
   public int count5;
   public int count;
   private static Setting[] settingArray;

   public final int getCount4() {
      return this.count4;
   }

   public StandardByteArrayEntry resolveStandardByteArrayEntryByByteArray(byte[] byteValueArray, int count6, int count7) {
      int count8 = 0;
      applyIndex(count6, count7, byteValueArray.length);
      if (count7 > this.getCount3()) {
         throw new RuntimeException();
      } else {
         count8 = count6 + count7;

         for (int index = count6; index < count8; index++) {
            byteValueArray[index] = this.getByteCode();
         }

         return this;
      }
   }

   public static AngleTracker resolveAngleTracker(int count6) {

      for (long index = 0x1ac4c8169674568dL; (int)(index % 3L) == 1; index = (index ^ 0x4e1970f11f6bc817L) - 0x212deff4892064e2L) {
         if (count6 < 0) {
            throw new RuntimeException();
         }
      }

      return new AngleTracker(count6, count6);
   }

   public final StandardByteArrayEntry resolveStandardByteArrayEntryByByteArray(byte[] byteValueArray) {
      int length = byteValueArray.length;
      int count7 = length;
      byte byteValue = 0;
      return this.resolveStandardByteArrayEntry(byteValueArray, byteValue, count7);
   }

   public final byte[] getByteArray() {
      if (this.byteFlagArray != null) {
         if (!this.enabled) {
            return this.byteFlagArray;
         } else {
            throw new RuntimeException();
         }
      } else {
         throw new RuntimeException();
      }
   }

   public static StandardByteArrayEntry resolveStandardByteArrayEntry(byte[] byteValueArray) {
      int length = byteValueArray.length;
      byte byteValue = 0;
      return resolveStandardByteArrayEntry2(byteValueArray, byteValue, length);
   }

   public final int getCountAsIndex() {
      int count6 = 0;
      count6 = this.count;
      if (count6 < this.count4) {
         this.count = count6 + 1;
         return count6;
      } else {
         throw new RuntimeException();
      }
   }

   public abstract byte getByteCode();

   public final StandardByteArrayEntry resolveStandardByteArrayEntry(int count6) {
      if (count6 <= this.count5 && count6 >= 0) {
         this.count4 = count6;
         if (this.count > count6) {
            this.count = count6;
         }

         if (this.count3 > count6) {
            this.count3 = -1;
         }

         return this;
      } else {
         throw new RuntimeException();
      }
   }

   public abstract StandardByteArrayEntry resolveStandardByteArrayEntry(byte byteValue);

   public final int getCount() {
      int count6 = 0;
      count6 = this.count;
      if (count6 < this.count4) {
         this.count = count6 + 1;
         return count6;
      } else {
         throw new RuntimeException();
      }
   }

   public final StandardByteArrayEntry resolveStandardByteArrayEntryByIndex(int count6) {
      if (count6 <= this.count4 && count6 >= 0) {
         if (this.count3 > count6) {
            this.count3 = -1;
         }

         this.count = count6;
         return this;
      } else {
         throw new RuntimeException();
      }
   }

   public final int getCount5() {
      return this.count5;
   }

   public StandardByteArrayEntry resolveStandardByteArrayEntry2(byte[] byteValueArray) {
      int length = byteValueArray.length;
      byte byteValue = 0;
      return this.resolveStandardByteArrayEntryByByteArray(byteValueArray, byteValue, length);
   }

   public StandardByteArrayEntry resolveStandardByteArrayEntry(byte[] byteValueArray, int count6, int count7) {
      byte byteValue = 0;
      short shortValue = 29962;
      int count8 = -110238142;
      int count9 = 0;
      applyIndex(count6, count7, byteValueArray.length);
      if (count7 > this.getCount3()) {
         throw new RuntimeException();
      } else {
         count9 = count6 + count7;

         for (int index = count6; index < count9; index++) {
            byte byteValue2 = byteValueArray[index];
            this.resolveStandardByteArrayEntry(byteValue2);
         }

         return this;
      }
   }

   public final StandardByteArrayEntry getStandardByteArrayEntry() {
      this.count3 = this.count;
      return this;
   }

   public final int getCount3() {
      int count6 = 0;
      count6 = this.count4 - this.count;
      return count6 <= 0 ? 0 : count6;
   }

   public static StandardByteArrayEntry resolveStandardByteArrayEntry2(byte[] byteValueArray, int count6, int count7) {
      try {
         return new AngleTracker(byteValueArray, count6, count7);
      } catch (IllegalArgumentException illegalArgumentException) {
         throw new RuntimeException();
      }
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   public static void applyIndex(int count6, int count7, int count8) {
      if ((count6 | count7 | count6 + count7 | count8 - (count6 + count7)) < 0) {
         throw new RuntimeException();
      }
   }

   public final int getCount2() {
      return this.count;
   }

   public StandardByteArrayEntry(int count6, int count7, int count8, int count9, byte[] byteValueArray, int count10) {
      this(count6, count7, count8, count9);
      this.byteFlagArray = byteValueArray;
      this.count2 = count10;
   }

   public StandardByteArrayEntry(int count6, int count7, int count8, int count9) {
      this.count3 = -1;
      this.count = 0;
      if (count9 < 0) {
         throw new RuntimeException();
      } else {
         this.count5 = count9;
         this.resolveStandardByteArrayEntry(count8);
         this.resolveStandardByteArrayEntryByIndex(count7);
         if (count6 >= 0) {
            if (count6 > count7) {
               throw new RuntimeException();
            }

            this.count3 = count6;
         }
      }
   }
}
