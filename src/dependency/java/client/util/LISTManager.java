package client.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class LISTManager {
   public static final String[] stringArray;

   public abstract LISTManager getLISTManager();

   public abstract void applyDataInput(DataInput dataInput, int count, BytesWhereMaxAllowedManager bytesWhereMaxAllowedManager) throws IOException;

   public String getLabelText() {
      return this.toString();
   }

   public boolean isEmpty() {
      return false;
   }

   public abstract byte getByteCode();

   public abstract void writeInt(DataOutput dataOutput) throws IOException;

   public static LISTManager resolveLISTManager(byte byteValue) {
      switch (byteValue) {
         case 0:
            return new InterpolationPicker();
         case 1:
            return new AuxiliaryClientManagerRegistry();
         case 2:
            return new ClientManagerController();
         case 3:
            return new InterpolationMatcher();
         case 4:
            return new ExtendedClientManagerHandler();
         case 5:
            return new InternalClientManagerRegistry();
         case 6:
            return new PrimaryDoubleEntry();
         case 7:
            return new LISTManagerAdapter();
         case 8:
            return new HandlerStringEntry();
         case 9:
            return new NBTTagList();
         case 10:
            return new NBTTagCompound();
         case 11:
            return new ProviderIntArrayEntry();
         default:
            return null;
      }
   }

   static {
      String[] textArray = new String[]{"LIST", "COMPOUND", "BYTE[]", "STRING", "INT", "DOUBLE", "FLOAT", "LONG", "END", "INT[]", "SHORT", "BYTE"};
      stringArray = new String[]{textArray[8], textArray[11], textArray[10], textArray[4], textArray[7], textArray[6], textArray[5], textArray[2], textArray[3], textArray[0], textArray[1], textArray[9]};
   }

   @Override
   public boolean equals(Object other) {
      if (!(other instanceof LISTManager)) {
         return false;
      } else {
         LISTManager lISTManager = (LISTManager)other;
         return this.getByteCode() == lISTManager.getByteCode();
      }
   }

   @Override
   public abstract String toString();

   @Override
   public int hashCode() {
      return this.getByteCode();
   }
}
