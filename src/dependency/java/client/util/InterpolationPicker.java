package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class InterpolationPicker extends LISTManager {
   @Override
   public LISTManager getLISTManager() {
      return new InterpolationPicker();
   }

   @Override
   public void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException {
      bytesWhereMaxAllowedManager.applyTime(64L);
   }

   @Override
   public byte getByteCode() {
      return 0;
   }

   @Override
   public void writeInt(DataOutput dataOutput) throws IOException {
   }

   @Override
   public String toString() {
      return "END";
   }
}
