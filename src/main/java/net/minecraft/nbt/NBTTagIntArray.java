package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class NBTTagIntArray extends NBTBase {
   private int[] intArray;

   public NBTTagIntArray() {
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.intArray);
   }

   public NBTTagIntArray(int[] ints) {
      this.intArray = ints;
   }

   @Override
   public boolean equals(Object obj) {
      return super.equals(obj) ? Arrays.equals(this.intArray, ((NBTTagIntArray)obj).intArray) : false;
   }

   @Override
   public void write(DataOutput dataOutput) throws IOException {
      dataOutput.writeInt(this.intArray.length);

      for (int i = 0; i < this.intArray.length; i++) {
         dataOutput.writeInt(this.intArray[i]);
      }
   }

   @Override
   public NBTBase copy() {
      int[] ints = new int[this.intArray.length];
      System.arraycopy(this.intArray, 0, ints, 0, this.intArray.length);
      return new NBTTagIntArray(ints);
   }

   @Override
   public byte getId() {
      return 11;
   }

   @Override
   public String toString() {
      String str = "[";

      for (int n : this.intArray) {
         str = str + n + ",";
      }

      return str + "]";
   }

   public int[] getIntArray() {
      return this.intArray;
   }

   @Override
   public void read(DataInput dataInput, int n, NBTSizeTracker nbtSizeTracker) throws IOException {
      nbtSizeTracker.read(192L);
      int n2 = dataInput.readInt();
      nbtSizeTracker.read((long)(32 * n2));
      this.intArray = new int[n2];

      for (int i = 0; i < n2; i++) {
         this.intArray[i] = dataInput.readInt();
      }
   }
}
