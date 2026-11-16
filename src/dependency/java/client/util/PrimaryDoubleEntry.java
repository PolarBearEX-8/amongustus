package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.util.MathHelper;

public class PrimaryDoubleEntry extends InterpolationSorter {
   public double value;

   @Override
   public float getFactor() {
      return (float)this.value;
   }

   @Override
   public LISTManager getLISTManager() {
      return new PrimaryDoubleEntry(this.value);
   }

   @Override
   public double getValue() {
      return this.value;
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(128L);
      this.value = dataInput.readDouble();
   }

   @Override
   public short getShortCode() {
      return (short)(MathHelper.ceiling_double_int(this.value) & 65535);
   }

   @Override
   public byte getByteCode() {
      return 6;
   }

   @Override
   public long floor() {
      return (long)Math.floor(this.value);
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      dataOutput.writeDouble(this.value);
   }

   @Override
   public int getCount() {
      return MathHelper.ceiling_double_int(this.value);
   }

   @Override
   public byte getByteFlag() {
      return (byte)(MathHelper.ceiling_double_int(this.value) & 0xFF);
   }

   public PrimaryDoubleEntry(double offset) {
      this.value = offset;
   }

   public PrimaryDoubleEntry() {
   }

   @Override
   public boolean equals(Object other) {
      if (super.equals(other)) {
         PrimaryDoubleEntry primaryDoubleEntry = (PrimaryDoubleEntry)other;
         return this.value == primaryDoubleEntry.value;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "" + this.value + "d";
   }

   @Override
   public int hashCode() {
      long value2 = Double.doubleToLongBits(this.value);
      return super.hashCode() ^ (int)(value2 ^ value2 >>> 32);
   }
}
