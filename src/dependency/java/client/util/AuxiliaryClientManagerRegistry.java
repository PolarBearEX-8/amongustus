package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AuxiliaryClientManagerRegistry extends InterpolationSorter {
   public byte byteFlag;

   @Override
   public float getFactor() {
      return (float)this.byteFlag;
   }

   @Override
   public double getValue() {
      return (double)this.byteFlag;
   }

   @Override
   public LISTManager getLISTManager() {
      return new AuxiliaryClientManagerRegistry(this.byteFlag);
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(72L);
      this.byteFlag = dataInput.readByte();
   }

   @Override
   public short getShortCode() {
      return (short)this.byteFlag;
   }

   @Override
   public byte getByteCode() {
      return 1;
   }

   @Override
   public long floor() {
      return (long)this.byteFlag;
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      dataOutput.writeByte(this.byteFlag);
   }

   @Override
   public int getCount() {
      return this.byteFlag;
   }

   @Override
   public byte getByteFlag() {
      return this.byteFlag;
   }

   public AuxiliaryClientManagerRegistry() {
   }

   public AuxiliaryClientManagerRegistry(byte byteValue) {
      this.byteFlag = byteValue;
   }

   @Override
   public boolean equals(Object other) {
      if (super.equals(other)) {
         AuxiliaryClientManagerRegistry auxiliaryClientManagerRegistry = (AuxiliaryClientManagerRegistry)other;
         return this.byteFlag == auxiliaryClientManagerRegistry.byteFlag;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "" + this.byteFlag + "b";
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.byteFlag;
   }
}
