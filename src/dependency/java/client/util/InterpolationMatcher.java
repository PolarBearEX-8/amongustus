package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class InterpolationMatcher extends InterpolationSorter {
   public int count;

   @Override
   public float getFactor() {
      return (float)this.count;
   }

   @Override
   public double getValue() {
      return (double)this.count;
   }

   @Override
   public LISTManager getLISTManager() {
      return new InterpolationMatcher(this.count);
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count2, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(96L);
      this.count = dataInput.readInt();
   }

   @Override
   public short getShortCode() {
      return (short)(this.count & 65535);
   }

   @Override
   public byte getByteCode() {
      return 3;
   }

   @Override
   public long floor() {
      return (long)this.count;
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      dataOutput.writeInt(this.count);
   }

   @Override
   public byte getByteFlag() {
      return (byte)(this.count & 0xFF);
   }

   @Override
   public int getCount() {
      return this.count;
   }

   public InterpolationMatcher() {
   }

   public InterpolationMatcher(int count2) {
      this.count = count2;
   }

   @Override
   public boolean equals(Object other) {
      if (super.equals(other)) {
         InterpolationMatcher interpolationMatcher = (InterpolationMatcher)other;
         return this.count == interpolationMatcher.count;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "" + this.count;
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.count;
   }
}
