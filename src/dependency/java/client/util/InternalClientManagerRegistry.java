package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.util.MathHelper;

public class InternalClientManagerRegistry extends InterpolationSorter {
   public float factor;

   @Override
   public float getFactor() {
      return this.factor;
   }

   @Override
   public double getValue() {
      return (double)this.factor;
   }

   @Override
   public LISTManager getLISTManager() {
      return new InternalClientManagerRegistry(this.factor);
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(96L);
      this.factor = dataInput.readFloat();
   }

   @Override
   public short getShortCode() {
      return (short)(MathHelper.floor_float(this.factor) & 65535);
   }

   @Override
   public long floor() {
      return (long)this.factor;
   }

   @Override
   public byte getByteCode() {
      return 5;
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      dataOutput.writeFloat(this.factor);
   }

   @Override
   public int getCount() {
      return MathHelper.floor_float(this.factor);
   }

   @Override
   public byte getByteFlag() {
      return (byte)(MathHelper.floor_float(this.factor) & 0xFF);
   }

   public InternalClientManagerRegistry() {
   }

   public InternalClientManagerRegistry(float factor2) {
      this.factor = factor2;
   }

   @Override
   public boolean equals(Object other) {
      if (super.equals(other)) {
         InternalClientManagerRegistry internalClientManagerRegistry = (InternalClientManagerRegistry)other;
         return this.factor == internalClientManagerRegistry.factor;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "" + this.factor + "f";
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ Float.floatToIntBits(this.factor);
   }
}
