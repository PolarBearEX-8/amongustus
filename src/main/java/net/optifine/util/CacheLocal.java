package net.optifine.util;

public class CacheLocal {
   private int maxX = 18;
   private int maxY = 128;
   private int maxZ = 18;
   private int offsetX = 0;
   private int offsetY = 0;
   private int offsetZ = 0;
   private int[][][] cache = (int[][][])null;
   private int[] lastZs = null;
   private int lastDz = 0;

   public void resetCache() {
      for (int i = 0; i < this.maxX; i++) {
         int[][] intsArr = this.cache[i];

         for (int j = 0; j < this.maxY; j++) {
            int[] ints = intsArr[j];

            for (int k = 0; k < this.maxZ; k++) {
               ints[k] = -1;
            }
         }
      }
   }

   public void setLast(int n) {
      try {
         this.lastZs[this.lastDz] = n;
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public int get(int n, int n2, int n3) {
      try {
         this.lastZs = this.cache[n - this.offsetX][n2 - this.offsetY];
         this.lastDz = n3 - this.offsetZ;
         return this.lastZs[this.lastDz];
      } catch (ArrayIndexOutOfBoundsException e) {
         e.printStackTrace();
         return -1;
      }
   }

   public CacheLocal(int n, int n2, int n3) {
      this.maxX = n;
      this.maxY = n2;
      this.maxZ = n3;
      this.cache = new int[n][n2][n3];
      this.resetCache();
   }

   public void setOffset(int n, int n2, int n3) {
      this.offsetX = n;
      this.offsetY = n2;
      this.offsetZ = n3;
      this.resetCache();
   }
}
