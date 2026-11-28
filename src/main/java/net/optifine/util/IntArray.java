package net.optifine.util;

public class IntArray {
   private int[] array = null;
   private int position = 0;
   private int limit = 0;

   public void put(int n, int n2) {
      this.array[n] = n2;
      if (this.limit < n) {
         this.limit = n;
      }
   }

   public void put(int[] ints) {
      int length = ints.length;

      for (int i = 0; i < length; i++) {
         this.array[this.position] = ints[i];
         this.position++;
      }

      if (this.limit < this.position) {
         this.limit = this.position;
      }
   }

   public IntArray(int n) {
      this.array = new int[n];
   }

   public int getLimit() {
      return this.limit;
   }

   public int getPosition() {
      return this.position;
   }

   public int get(int n) {
      return this.array[n];
   }

   public void put2(int n) {
      this.array[this.position] = n;
      this.position++;
      if (this.limit < this.position) {
         this.limit = this.position;
      }
   }

   public void clear() {
      this.position = 0;
      this.limit = 0;
   }

   public int[] getArray() {
      return this.array;
   }

   public void setPosition(int n) {
      this.position = n;
   }
}
