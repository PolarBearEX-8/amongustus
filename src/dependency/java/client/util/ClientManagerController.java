package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ClientManagerController extends InterpolationSorter {
   public short shortCode;

   @Override
   public float getFactor() {
      return (float)this.shortCode;
   }

   @Override
   public double getValue() {
      return (double)this.shortCode;
   }

   @Override
   public LISTManager getLISTManager() {
      return new ClientManagerController(this.shortCode);
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(80L);
      this.shortCode = dataInput.readShort();
   }

   @Override
   public short getShortCode() {
      return this.shortCode;
   }

   @Override
   public long floor() {
      return (long)this.shortCode;
   }

   @Override
   public byte getByteCode() {
      return 2;
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
      dataOutput.writeShort(this.shortCode);
   }

   @Override
   public int getCount() {
      return this.shortCode;
   }

   @Override
   public byte getByteFlag() {
      return (byte)(this.shortCode & 255);
   }

   public ClientManagerController(short shortValue) {
      this.shortCode = shortValue;
   }

   public ClientManagerController() {
   }

   @Override
   public boolean equals(Object other) {
      if (super.equals(other)) {
         ClientManagerController clientManagerController = (ClientManagerController)other;
         return this.shortCode == clientManagerController.shortCode;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "" + this.shortCode + "s";
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.shortCode;
   }
}
