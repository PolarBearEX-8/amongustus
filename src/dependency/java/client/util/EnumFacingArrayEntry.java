package client.util;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EnumFacingArrayEntry {
   public static final int count5 = 63;
   public static final EnumFacing[][] enumFacingArray2 = resolveEnumFacingArray(false);
   public static final EnumFacing[][] enumFacingArray = resolveEnumFacingArray(true);
   public static int count2 = 0;
   public static int count = -1;
   public static int count3 = 16;
   public static World world = null;
   public static int count6 = Integer.MIN_VALUE;
   public static int count4 = Integer.MIN_VALUE;

   public static int computeCount(World world2, Entity entity, int count7) {
      int count8 = MathHelper.ceiling_double_int(entity.posX) >> 4;
      int count9 = MathHelper.ceiling_double_int(entity.posY) >> 4;
      int count10 = MathHelper.ceiling_double_int(entity.posZ) >> 4;
      AlertNotificationSound loggerEntry = world2.getLoggerEntry2(count8, count10);
      int count11 = count8 - count7;
      int count12 = count8 + count7;
      int count13 = count10 - count7;
      int count14 = count10 + count7;
      if (world2 != world || count8 != count6 || count10 != count4) {
         count2 = 0;
         count3 = 16;
         world = world2;
         count6 = count8;
         count4 = count10;
      }

      if (count2 == 0) {
         count = -1;
      }

      int count15 = count;
      switch (count2) {
         case 0:
            count12 = count8;
            count14 = count10;
            break;
         case 1:
            count11 = count8;
            count14 = count10;
            break;
         case 2:
            count12 = count8;
            count13 = count10;
            break;
         case 3:
            count11 = count8;
            count13 = count10;
      }

      while (count11 < count12) {
         for (int index = count13; index < count14; index++) {
            AlertNotificationSound loggerEntry2 = world2.getLoggerEntry2(count11, index);
            if (!loggerEntry2.canExecute()) {
               InternalCharArrayEntry[] internalCharArrayEntryArray = loggerEntry2.getInternalCharArrayEntryArray();

               for (int index2 = internalCharArrayEntryArray.length - 1; index2 > count15; index2--) {
                  InternalCharArrayEntry internalCharArrayEntry = internalCharArrayEntryArray[index2];
                  if (internalCharArrayEntry != null && !internalCharArrayEntry.isActiveAsFlag()) {
                     count15 = index2;
                     break;
                  }
               }

               try {
                  Map map = loggerEntry2.getMap();
                  if (!map.isEmpty()) {
                     Iterator iterator = map.keySet().iterator();

                     while (iterator.hasNext()) {
                        int count16 = ((BlockPos)iterator.next()).getY() >> 4;
                        if (count16 > count15) {
                           count15 = count16;
                        }
                     }
                  }
               } catch (ConcurrentModificationException concurrentModificationException) {
               }

               DonTKnowHowToSearchForAbstractSet[] donTKnowHowToSearchForAbstractSetArray = loggerEntry2.getDonTKnowHowToSearchForAbstractSetArray();

               for (int index3 = donTKnowHowToSearchForAbstractSetArray.length - 1; index3 > count15; index3--) {
                  DonTKnowHowToSearchForAbstractSet donTKnowHowToSearchForAbstractSet = donTKnowHowToSearchForAbstractSetArray[index3];
                  if (!donTKnowHowToSearchForAbstractSet.isEmpty() && (loggerEntry2 != loggerEntry || index3 != count9 || donTKnowHowToSearchForAbstractSet.size() != 1)) {
                     count15 = index3;
                     break;
                  }
               }
            }
         }

         count11++;
      }

      if (count2 < 3) {
         count = count15;
         count15 = count3;
      } else {
         count3 = count15;
         count = -1;
      }

      count2 = (count2 + 1) % 4;
      return count15 << 4;
   }

   public static EnumFacing[] resolveEnumFacingArray(int count7) {
      int count8 = ~count7 & 63;
      return enumFacingArray[count8];
   }

   private static EnumFacing[][] resolveEnumFacingArray(boolean flag) {
      EnumFacing[][] enumFacingMatrix = new EnumFacing[64][];

      for (int index = 0; index < 64; index++) {
         ArrayList list = new ArrayList();

         for (int index2 = 0; index2 < EnumFacing.VALUES.length; index2++) {
            EnumFacing enumFacing = EnumFacing.VALUES[index2];
            EnumFacing enumFacing2 = flag ? enumFacing.getEnumFacing5() : enumFacing;
            int count7 = 1 << enumFacing2.ordinal();
            if ((index & count7) != 0) {
               list.add(enumFacing);
            }
         }

         EnumFacing[] enumFacingArray3 = ((EnumFacing[])list.toArray(new EnumFacing[list.size()]));
         enumFacingMatrix[index] = enumFacingArray3;
      }

      return enumFacingMatrix;
   }

   public static void updateState() {
      world = null;
   }

   public static boolean isActive() {
      return count2 == 0;
   }
}
