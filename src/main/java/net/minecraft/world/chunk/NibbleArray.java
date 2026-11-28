package net.minecraft.world.chunk;

public class NibbleArray {
   private final byte[] data;

   public void set(int n, int n2, int n3, int n4) {
      this.setIndex(this.getCoordinateIndex(n, n2, n3), n4);
   }

   public int getFromIndex(int n) {
      int nibbleIndex = this.getNibbleIndex(n);
      return this.isLowerNibble(n) ? this.data[nibbleIndex] & 15 : this.data[nibbleIndex] >> 4 & 15;
   }

   public void setIndex(int n, int n2) {
      int nibbleIndex = this.getNibbleIndex(n);
      if (this.isLowerNibble(n)) {
         this.data[nibbleIndex] = (byte)(this.data[nibbleIndex] & 240 | n2 & 15);
      } else {
         this.data[nibbleIndex] = (byte)(this.data[nibbleIndex] & 15 | (n2 & 15) << 4);
      }
   }

   private int getNibbleIndex(int n) {
      return n >> 1;
   }

   public byte[] getData() {
      return this.data;
   }

   private boolean isLowerNibble(int n) {
      return (n & 1) == 0;
   }

   public int get(int n, int n2, int n3) {
      return this.getFromIndex(this.getCoordinateIndex(n, n2, n3));
   }

   public NibbleArray() {
      this.data = new byte[2048];
   }

   private int getCoordinateIndex(int n, int n2, int n3) {
      return n2 << 8 | n3 << 4 | n;
   }

   public NibbleArray(byte[] bytes) {
      this.data = bytes;
      if (bytes.length != 2048) {
         throw new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + bytes.length);
      }
   }
}
