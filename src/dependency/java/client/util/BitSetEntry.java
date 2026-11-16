package client.util;

import augustus.modules.render.XRay;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.Set;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class BitSetEntry {
   public static final int count2 = (int)Math.pow(16.0, 0.0);
   public static final int count3 = (int)Math.pow(16.0, 1.0);
   public static final int count = (int)Math.pow(16.0, 2.0);
   public final BitSet bitSet = new BitSet(4096);
   public static final int[] countArray = new int[1352];
   public int count4 = 4096;

   private Set resolveSet(int count5) {
      EnumSet enumSet = EnumSet.noneOf(EnumFacing.class);
      ArrayDeque arrayDeque = new ArrayDeque(384);
      arrayDeque.add(IntegerArrayWrapper.resolveInteger(count5));
      this.bitSet.set(count5, true);

      while (!arrayDeque.isEmpty()) {
         int count6 = (Integer)arrayDeque.poll();
         this.applySet(count6, enumSet);

         for (EnumFacing enumFacing : EnumFacing.VALUES) {
            int count7 = this.computeCount(count6, enumFacing);
            if (count7 >= 0 && !this.bitSet.get(count7)) {
               this.bitSet.set(count7, true);
               arrayDeque.add(IntegerArrayWrapper.resolveInteger(count7));
            }
         }
      }

      return enumSet;
   }

   public void applyBlockPos(BlockPos blockPos) {
      if (!XRay.activeState3) {
         this.bitSet.set(computeCount(blockPos), true);
         this.count4--;
      }
   }

   private void applySet(int count5, Set set) {
      int count6 = count5 >> 0 & 15;
      if (count6 == 0) {
         set.add(EnumFacing.WEST);
      } else if (count6 == 15) {
         set.add(EnumFacing.EAST);
      }

      int count7 = count5 >> 8 & 15;
      if (count7 == 0) {
         set.add(EnumFacing.DOWN);
      } else if (count7 == 15) {
         set.add(EnumFacing.UP);
      }

      int count8 = count5 >> 4 & 15;
      if (count8 == 0) {
         set.add(EnumFacing.NORTH);
      } else if (count8 == 15) {
         set.add(EnumFacing.SOUTH);
      }
   }

   private static int computeCount(BlockPos blockPos) {
      return computeCount(blockPos.getX() & 15, blockPos.getY() & 15, blockPos.getZ() & 15);
   }

   public Set resolveSet(BlockPos blockPos) {
      return this.resolveSet(computeCount(blockPos));
   }

   public PrimaryLongEntry getPrimaryLongEntry() {
      PrimaryLongEntry primaryLongEntry = new PrimaryLongEntry();
      if (4096 - this.count4 < 256) {
         primaryLongEntry.setEnabled(true);
      } else if (this.count4 == 0) {
         primaryLongEntry.setEnabled(false);
      } else {
         for (int count5 : countArray) {
            if (!this.bitSet.get(count5)) {
               primaryLongEntry.applySet(this.resolveSet(count5));
            }
         }
      }

      return primaryLongEntry;
   }

   private static int computeCount(int count5, int count6, int count7) {
      return count5 << 0 | count6 << 8 | count7 << 4;
   }

   private int computeCount(int count5, EnumFacing enumFacing) {
      switch (SplineSerializer.countArray[enumFacing.ordinal()]) {
         case 1:
            if ((count5 >> 8 & 15) == 0) {
               return -1;
            }

            return count5 - count;
         case 2:
            if ((count5 >> 8 & 15) == 15) {
               return -1;
            }

            return count5 + count;
         case 3:
            if ((count5 >> 4 & 15) == 0) {
               return -1;
            }

            return count5 - count3;
         case 4:
            if ((count5 >> 4 & 15) == 15) {
               return -1;
            }

            return count5 + count3;
         case 5:
            if ((count5 >> 0 & 15) == 0) {
               return -1;
            }

            return count5 - count2;
         case 6:
            if ((count5 >> 0 & 15) == 15) {
               return -1;
            }

            return count5 + count2;
         default:
            return -1;
      }
   }

   static {
      int count5 = 0;

      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            for (int index3 = 0; index3 < 16; index3++) {
               if (index == 0 || index == 15 || index2 == 0 || index2 == 15 || index3 == 0 || index3 == 15) {
                  countArray[count5++] = computeCount(index, index2, index3);
               }
            }
         }
      }
   }
}
