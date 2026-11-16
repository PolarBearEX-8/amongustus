package client.util;

import java.util.Objects;

public class MapChunkBlockData {
   public final long timestamp;
   public final int count;
   public Object dataRef;
   public MapChunkBlockData extendedLongEntry;

   public final Object getEntry() {
      return this.dataRef;
   }

   public final long getTimestamp() {
      return this.timestamp;
   }

   public MapChunkBlockData(int count2, long value, Object entry, MapChunkBlockData mapChunkBlockData) {
      this.dataRef = entry;
      this.extendedLongEntry = mapChunkBlockData;
      this.timestamp = value;
      this.count = count2;
   }

   @Override
   public final boolean equals(Object other) {
      if (!(other instanceof MapChunkBlockData)) {
         return false;
      } else {
         MapChunkBlockData mapChunkBlockData = (MapChunkBlockData)other;
         Long timestamp2 = this.getTimestamp();
         Long timestamp3 = mapChunkBlockData.getTimestamp();
         if (Objects.equals(timestamp2, timestamp3)) {
            Object entry = this.getEntry();
            Object entry2 = mapChunkBlockData.getEntry();
            return Objects.equals(entry, entry2);
         } else {
            return false;
         }
      }
   }

   @Override
   public final String toString() {
      return this.getTimestamp() + "=" + this.getEntry();
   }

   @Override
   public final int hashCode() {
      return InternalFloatEntry.computeCount(this.timestamp);
   }
}
