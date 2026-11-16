package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class ProviderIntArrayEntry extends LISTManager {
   public int[] countArray;

   public int[] getCountArray() {
      return this.countArray;
   }

   @Override
   public LISTManager getLISTManager() {
      int[] countArray2 = new int[this.countArray.length];
      System.arraycopy(this.countArray, 0, countArray2, 0, this.countArray.length);
      return new ProviderIntArrayEntry(countArray2);
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(192L);
      int count2 = dataInput.readInt();
      bytesWhereMaxAllowedManager.applyTime((long)(32 * count2));
      this.countArray = new int[count2];

      for (int index = 0; index < count2; index++) {
         this.countArray[index] = dataInput.readInt();
      }
   }

   @Override
   public byte getByteCode() {
      return 11;
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      dataOutput.writeInt(this.countArray.length);

      for (int index = 0; index < this.countArray.length; index++) {
         dataOutput.writeInt(this.countArray[index]);
      }
   }

   public ProviderIntArrayEntry(int[] countArray2) {
      this.countArray = countArray2;
   }

   public ProviderIntArrayEntry() {
   }

   @Override
   public boolean equals(Object other) {
      return super.equals(other) ? Arrays.equals(this.countArray, ((ProviderIntArrayEntry)other).countArray) : false;
   }

   @Override
   public String toString() {
      String text = "[";

      for (int count : this.countArray) {
         text = text + count + ",";
      }

      return text + "]";
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.countArray);
   }
}
