package net.minecraft.item;

import client.setting.MovementWeightProperty;
import client.util.AuxiliaryRuntimeExceptionAdapter;
import client.util.ContextStringEntry;
import java.io.IOException;
import java.io.InputStream;

public class ItemEntry133Entry {
   public final int count2;
   public final IronTalisman ironTalisman;
   public final DiamondNugget diamondNugget;
   public final MovementWeightProperty movementWeightProperty;
   public final int count;
   public final DynamicItemEntry dynamicItemEntry;
   public final long[][] timestampArray = new long[5][5];

   private void updateState() {
      int count3 = 0;
      int count4 = 0;
      long value = 0x531186234f50dcb0L;
      long value2 = -0x61d03421e5d93eaL;

      while (true) {
         while (true) {
            int count5 = (int)(value % 6L);
            if (count5 != 0) {
               if (count5 == 4) {
                  count4 = 0;
                  value = (value ^ 0x58617a95a13d8ff6L) - 0x3a7101cf1e0547b5L;
                  value2 = (value2 ^ -0x6007c0d4cc9f426dL) - 0x778b8f0c7e839d14L;
                  continue;
               }

               if (count5 == 5) {
                  this.timestampArray[count3][count4] = 0L;
                  value = (value ^ -0x2961fb6b80ec76d8L) + 0x3d81fa242a3c7f1fL;
                  value2 = (value2 ^ -0x500f643d3ad180b5L) + 0xdb940aa08701b4bL;
                  continue;
               }

               if (count5 == 1) {
                  break;
               }

               if (count5 != 3) {
                  count3 = 0;
                  value = (value ^ -0x1e3f568f773ae108L) - 0x771f037d54e421c3L;
                  value2 = (value2 ^ 0x37012be4582a3c3aL) + 0x476de9cbb616febeL;
                  continue;
               }
            } else {
               count4++;
               value = (value ^ -0x489b4f9ccdb384cL) + 0x455bf6d6bbbd4723L;
               value2 = (value2 ^ -0x247f2f7adb979b0L) + 0x442d39f9faca1f37L;
            }

            if (count4 >= 5) {
               long value3 = (value ^ -0x1f36a7485d58b471L) + 0x50e50575b5bac3dcL;
               long value4 = (value2 ^ -0x3d9c7b3e7fce06c8L) + 0xe784e3e57073361L;
               count3++;
               value = (value3 ^ 0x76eddb130a6b22a3L) - 0x13bb1b650ee230c4L;
               value2 = (value4 ^ -0x5afa97435b7b098bL) - 0x15b440862b00ef84L;
               break;
            }

            value = (value ^ 0x63f43ff4fc72f83aL) - 0x3e464b616a3b85d8L;
            value2 = (value2 ^ 0x397800e994d0bb30L) + 0x532a14befc9dfce9L;
         }

         if (count3 >= 5) {
            return;
         }

         value = (value ^ -0x1ef5f07e7c4217b9L) + 0x3fea90e9429703feL;
         value2 = (value2 ^ 0x4f4f12075847b596L) + 0x566d3eec9ef7bb89L;
      }
   }

   private void arraycopy(byte[] byteValueArray, byte[] byteValueArray2) {
      System.arraycopy(byteValueArray, 0, byteValueArray2, 0, byteValueArray.length);
   }

   public byte[] resolveByteArray(byte[] byteValueArray) {
      int count3 = 0;
      int count4 = 0;
      Object entry = null;
      Object entry2 = null;
      if (byteValueArray != null) {
         this.updateState();
         count3 = byteValueArray.length << 3;
         count4 = this.computeCount(count3);
         entry = this.resolveByteArray(count4);
         this.arraycopy(byteValueArray, (byte[])entry);
         this.applyByteArray((byte[])entry, count3);
         this.diamondNugget.applyByteArray((byte[])entry, count3);
         long[][] timestampArray2 = this.timestampArray;
         this.ironTalisman.applyByteArray((byte[])entry, timestampArray2);
         int count5 = this.movementWeightProperty.getCount();
         entry2 = this.resolveByteArray(count5);
         long[][] timestampArray3 = this.timestampArray;
         return this.dynamicItemEntry.resolveByteArray(timestampArray3, (byte[])entry2);
      } else {
         throw new AuxiliaryRuntimeExceptionAdapter("Data cannot be null");
      }
   }

   public byte[] resolveByteArray(InputStream inputStream) throws IOException {
      short shortValue = 15193;
      char character = '\uc1de';
      short shortValue2 = 26387;
      Object entry = null;
      int count3 = 0;
      Object entry2 = null;
      Object entry3 = null;
      if (inputStream != null) {
         this.updateState();
         int count4 = ContextStringEntry.computeCount(this.movementWeightProperty);
         entry = this.resolveByteArray(count4);
         long[][] timestampArray2 = this.timestampArray;
         count3 = this.ironTalisman.computeCount(inputStream, timestampArray2, (byte[])entry);
         entry2 = this.resolveByteArray((byte[])entry, count3);
         this.applyByteArray((byte[])entry2, count3);
         this.diamondNugget.applyByteArray((byte[])entry2, count3);
         long[][] timestampArray3 = this.timestampArray;
         this.ironTalisman.applyByteArray((byte[])entry2, timestampArray3);
         int count5 = this.movementWeightProperty.getCount();
         entry3 = this.resolveByteArray(count5);
         long[][] timestampArray4 = this.timestampArray;
         return this.dynamicItemEntry.resolveByteArray(timestampArray4, (byte[])entry3);
      } else {
         throw new AuxiliaryRuntimeExceptionAdapter("File cannot be null");
      }
   }

   private int computeCount(int count3, int count4) {
      if (count3 != 0) {
         return count3 % count4 != 0 ? 1 + count3 / count4 : count3 / count4;
      } else {
         return 0;
      }
   }

   private void applyByteArray(byte[] byteValueArray, int count3) {
      int count4 = 0;
      int count5 = 0;
      int count6 = 0;

      for (int index = 0; index < "01".length(); index++) {
         if ("01".charAt(index) == '1') {
            count4 = count3 + index;
            count5 = count4 / 8;
            count6 = count4 % 8;
            byteValueArray[count5] = (byte)(byteValueArray[count5] + (1 << count6));
         }
      }
   }

   private byte[] resolveByteArray(int count3) {
      int count4 = 0;

      for (long index = 0xf33860aa8da7adbL; (int)(index % 3L) == 1; index = (index ^ 0x5a0423f607fae077L) - 0x2135024b1642f018L) {
         count4 = count3 / 8;
      }

      if (count3 % 8 != 0) {
         count4++;
      }

      return new byte[count4];
   }

   private byte[] resolveByteArray(byte[] byteValueArray, int count3) {
      int count4 = 0;
      count4 = this.computeCount(count3);
      return count4 <= ContextStringEntry.computeCount(this.movementWeightProperty) ? byteValueArray : this.resolveByteArray(byteValueArray, count3, count4);
   }

   public int computeCount(int count3) {
      int count4 = 0;
      count4 = count3 + 1 + "01".length() + 1;
      return count4 % this.count2 != 0 ? count4 + this.count2 - count4 % this.count2 : count4;
   }

   private byte[] resolveByteArray(byte[] byteValueArray, int count3, int count4) {
      int count5 = 0;
      byte[] entry = null;
      int count6 = 0;
      int count7 = ContextStringEntry.computeCount(this.movementWeightProperty);
      count5 = this.computeCount(count4, count7);
      entry = new byte[count5 * ContextStringEntry.computeCount(this.movementWeightProperty) / 8];
      byte byteValue = 8;
      count6 = this.computeCount(count3, byteValue);
      System.arraycopy(byteValueArray, 0, entry, 0, count6);
      return (byte[])entry;
   }

   public ItemEntry133Entry(MovementWeightProperty movementWeightProperty2) {
      this.movementWeightProperty = movementWeightProperty2;
      this.count2 = ContextStringEntry.computeCount(movementWeightProperty2);
      this.count = ContextStringEntry.computeCountByMovementWeightPr(movementWeightProperty2);
      this.diamondNugget = new DiamondNugget(movementWeightProperty2);
      this.ironTalisman = new IronTalisman(movementWeightProperty2);
      this.dynamicItemEntry = new DynamicItemEntry(movementWeightProperty2);
   }
}
