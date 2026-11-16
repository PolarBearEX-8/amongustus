package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ExtendedClientManagerHandler extends InterpolationSorter {
   public long timestamp;

   @Override
   public float getFactor() {
      return (float)this.timestamp;
   }

   @Override
   public LISTManager getLISTManager() {
      return new ExtendedClientManagerHandler(this.timestamp);
   }

   @Override
   public double getValue() {
      return (double)this.timestamp;
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(128L);
      this.timestamp = dataInput.readLong();
   }

   @Override
   public short getShortCode() {
      return (short)((int)(this.timestamp & 65535L));
   }

   @Override
   public byte getByteCode() {
      return 4;
   }

   @Override
   public long floor() {
      return this.timestamp;
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      dataOutput.writeLong(this.timestamp);
   }

   @Override
   public int getCount() {
      return (int)(this.timestamp & -1L);
   }

   @Override
   public byte getByteFlag() {
      return (byte)((int)(this.timestamp & 255L));
   }

   public ExtendedClientManagerHandler() {
   }

   public ExtendedClientManagerHandler(long value) {
      this.timestamp = value;
   }

   @Override
   public boolean equals(Object other) {
      if (super.equals(other)) {
         ExtendedClientManagerHandler extendedClientManagerHandler = (ExtendedClientManagerHandler)other;
         return this.timestamp == extendedClientManagerHandler.timestamp;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "" + this.timestamp + "L";
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ (int)(this.timestamp ^ this.timestamp >>> 32);
   }
}
