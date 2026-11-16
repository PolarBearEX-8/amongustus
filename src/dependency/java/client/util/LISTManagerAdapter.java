package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class LISTManagerAdapter extends LISTManager {
   public byte[] byteFlagArray;

   @Override
   public LISTManager getLISTManager() {
      byte[] byteValueArray = new byte[this.byteFlagArray.length];
      System.arraycopy(this.byteFlagArray, 0, byteValueArray, 0, this.byteFlagArray.length);
      return new LISTManagerAdapter(byteValueArray);
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(192L);
      int count2 = dataInput.readInt();
      bytesWhereMaxAllowedManager.applyTime((long)(8 * count2));
      this.byteFlagArray = new byte[count2];
      dataInput.readFully(this.byteFlagArray);
   }

   @Override
   public byte getByteCode() {
      return 7;
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      dataOutput.writeInt(this.byteFlagArray.length);
      dataOutput.write(this.byteFlagArray);
   }

   public byte[] getByteFlagArray() {
      return this.byteFlagArray;
   }

   public LISTManagerAdapter(byte[] byteValueArray) {
      this.byteFlagArray = byteValueArray;
   }

   public LISTManagerAdapter() {
   }

   @Override
   public boolean equals(Object other) {
      return super.equals(other) ? Arrays.equals(this.byteFlagArray, ((LISTManagerAdapter)other).byteFlagArray) : false;
   }

   @Override
   public String toString() {
      return "[" + this.byteFlagArray.length + " bytes]";
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.byteFlagArray);
   }
}
