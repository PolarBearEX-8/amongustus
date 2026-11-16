package client.util;

public class InternalFloatEntry {
   public final float factor = 0.75F;
   public transient MapChunkBlockData[] extendedLongEntryArray = new MapChunkBlockData[4096];
   public transient int count2;
   public int count4;
   public int count = 3072;
   public transient volatile int count3;

   public void applyTarget(long value, Object entry) {
      int count5 = computeCountByTime(value);
      int count6 = computeCount(count5, this.count4);

      for (MapChunkBlockData mapChunkBlockData = this.extendedLongEntryArray[count6]; mapChunkBlockData != null; mapChunkBlockData = mapChunkBlockData.extendedLongEntry) {
         if (mapChunkBlockData.timestamp == value) {
            mapChunkBlockData.dataRef = entry;
            return;
         }
      }

      this.count3++;
      this.applyTarget(count5, value, entry, count6);
   }

   private void applyTarget(int count5, long value, Object entry, int count6) {
      MapChunkBlockData mapChunkBlockData = this.extendedLongEntryArray[count6];
      this.extendedLongEntryArray[count6] = new MapChunkBlockData(count5, value, entry, mapChunkBlockData);
      if (this.count2++ >= this.count) {
         this.applyIndex(2 * this.extendedLongEntryArray.length);
      }
   }

   public long[] getTimeArray() {
      long[] valueArray = new long[this.count2];
      int count5 = 0;
      MapChunkBlockData[] extendedLongEntryArray2 = this.extendedLongEntryArray;
      int length = extendedLongEntryArray2.length;

      for (int index = 0; index < length; index++) {
         for (MapChunkBlockData mapChunkBlockData = extendedLongEntryArray2[index]; mapChunkBlockData != null; mapChunkBlockData = mapChunkBlockData.extendedLongEntry) {
            valueArray[count5++] = mapChunkBlockData.timestamp;
         }
      }

      return valueArray;
   }

   public double getValue() {
      int count5 = 0;

      for (int index = 0; index < this.extendedLongEntryArray.length; index++) {
         if (this.extendedLongEntryArray[index] != null) {
            count5++;
         }
      }

      return (double)count5 / (double)this.count2;
   }

   public final MapChunkBlockData resolveMapChunkBlockData(long value) {
      int count5 = computeCountByTime(value);

      for (MapChunkBlockData mapChunkBlockData = this.extendedLongEntryArray[computeCount(count5, this.count4)]; mapChunkBlockData != null; mapChunkBlockData = mapChunkBlockData.extendedLongEntry) {
         if (mapChunkBlockData.timestamp == value) {
            return mapChunkBlockData;
         }
      }

      return null;
   }

   public Object resolveTarget(long value) {
      int count5 = computeCountByTime(value);

      for (MapChunkBlockData mapChunkBlockData = this.extendedLongEntryArray[computeCount(count5, this.count4)]; mapChunkBlockData != null; mapChunkBlockData = mapChunkBlockData.extendedLongEntry) {
         if (mapChunkBlockData.timestamp == value) {
            return mapChunkBlockData.dataRef;
         }
      }

      return null;
   }

   private static int computeCount(int count5) {
      int count6 = count5 ^ count5 >>> 20 ^ count5 >>> 12;
      return count6 ^ count6 >>> 7 ^ count6 >>> 4;
   }

   public boolean checkTime(long value) {
      return this.resolveMapChunkBlockData(value) != null;
   }

   private static int computeCountByTime(long value) {
      return (int)(value ^ value >>> 27);
   }

   private static int computeCount(int count5, int count6) {
      return count5 & count6;
   }

   private void applyIndex(int count5) {
      if (this.extendedLongEntryArray.length == 1073741824) {
         this.count = Integer.MAX_VALUE;
      } else {
         MapChunkBlockData[] mapChunkBlockDataArray = new MapChunkBlockData[count5];
         this.applyMapChunkBlockDataArray(mapChunkBlockDataArray);
         this.extendedLongEntryArray = mapChunkBlockDataArray;
         this.count4 = this.extendedLongEntryArray.length - 1;
         float factor2 = (float)count5;
         this.getClass();
         this.count = (int)(factor2 * 0.75F);
      }
   }

   private void applyMapChunkBlockDataArray(MapChunkBlockData[] mapChunkBlockDataArray) {
      MapChunkBlockData[] extendedLongEntryArray2 = this.extendedLongEntryArray;
      int length = mapChunkBlockDataArray.length;

      for (int index = 0; index < extendedLongEntryArray2.length; index++) {
         MapChunkBlockData mapChunkBlockData = extendedLongEntryArray2[index];
         if (mapChunkBlockData != null) {
            extendedLongEntryArray2[index] = null;

            while (true) {
               MapChunkBlockData extendedLongEntry = mapChunkBlockData.extendedLongEntry;
               int count5 = computeCount(mapChunkBlockData.count, length - 1);
               mapChunkBlockData.extendedLongEntry = mapChunkBlockDataArray[count5];
               mapChunkBlockDataArray[count5] = mapChunkBlockData;
               mapChunkBlockData = extendedLongEntry;
               if (extendedLongEntry == null) {
                  break;
               }
            }
         }
      }
   }

   public Object resolveTargetByTime(long value) {
      MapChunkBlockData mapChunkBlockData = this.resolveMapChunkBlockDataByTime(value);
      return mapChunkBlockData == null ? null : mapChunkBlockData.dataRef;
   }

   public int getCount2() {
      return this.count2;
   }

   public static int computeCount(long value) {
      return computeCountByTime(value);
   }

   public final MapChunkBlockData resolveMapChunkBlockDataByTime(long value) {
      int count5 = computeCount(computeCountByTime(value), this.count4);
      MapChunkBlockData mapChunkBlockData = this.extendedLongEntryArray[count5];
      MapChunkBlockData mapChunkBlockData2 = mapChunkBlockData;

      while (mapChunkBlockData2 != null) {
         MapChunkBlockData extendedLongEntry = mapChunkBlockData2.extendedLongEntry;
         if (mapChunkBlockData2.timestamp == value) {
            this.count3++;
            this.count2--;
            if (mapChunkBlockData == mapChunkBlockData2) {
               this.extendedLongEntryArray[count5] = extendedLongEntry;
            } else {
               mapChunkBlockData.extendedLongEntry = extendedLongEntry;
            }

            return mapChunkBlockData2;
         }

         mapChunkBlockData = mapChunkBlockData2;
         mapChunkBlockData2 = extendedLongEntry;
      }

      return mapChunkBlockData2;
   }

   public InternalFloatEntry() {
      this.count4 = this.extendedLongEntryArray.length - 1;
   }
}
