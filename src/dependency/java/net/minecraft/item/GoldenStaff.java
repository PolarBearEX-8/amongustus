package net.minecraft.item;

import client.util.DistanceAggregator;
import client.util.HitBoxCalculator;
import client.util.HitBoxMatcher;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class GoldenStaff extends CustomItemEntryAdapter {
   public EnumFacingController enumFacingController2;
   public EnumFacingController enumFacingController3;
   public List list = Lists.newArrayList();

   private void applyWorld(boolean flag, int count, World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesDistanceAggregator(distanceAggregator, count, 0, count + 23, 20)) {
         this.applyWorld(world, distanceAggregator, count + 0, 0, 0, count + 24, 0, 20, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, count + 0, 1, 0, count + 24, 10, 20, false);

         for (int index = 0; index < 4; index++) {
            this.applyWorld(world, distanceAggregator, count + index, index + 1, index, count + index, index + 1, 20, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, count + index + 7, index + 5, index + 7, count + index + 7, index + 5, 20, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, count + 17 - index, index + 5, index + 7, count + 17 - index, index + 5, 20, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, count + 24 - index, index + 1, index, count + 24 - index, index + 1, 20, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, count + index + 1, index + 1, index, count + 23 - index, index + 1, index, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, count + index + 8, index + 5, index + 7, count + 16 - index, index + 5, index + 7, iBlockState, iBlockState, false);
         }

         this.applyWorld(world, distanceAggregator, count + 4, 4, 4, count + 6, 4, 20, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, count + 7, 4, 4, count + 17, 4, 6, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, count + 18, 4, 4, count + 20, 4, 20, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, count + 11, 8, 11, count + 13, 8, 20, iBlockState4, iBlockState4, false);
         this.applyWorld(world, iBlockState5, count + 12, 9, 12, distanceAggregator);
         this.applyWorld(world, iBlockState5, count + 12, 9, 15, distanceAggregator);
         this.applyWorld(world, iBlockState5, count + 12, 9, 18, distanceAggregator);
         int count2 = flag ? count + 19 : count + 5;
         int count3 = flag ? count + 5 : count + 19;

         for (int byteValue = 20; byteValue >= 5; byteValue -= 3) {
            this.applyWorld(world, iBlockState5, count2, 5, byteValue, distanceAggregator);
         }

         for (int byteValue2 = 19; byteValue2 >= 7; byteValue2 -= 3) {
            this.applyWorld(world, iBlockState5, count3, 5, byteValue2, distanceAggregator);
         }

         for (int index2 = 0; index2 < 4; index2++) {
            int count4 = flag ? count + (24 - (17 - index2 * 3)) : count + 17 - index2 * 3;
            this.applyWorld(world, iBlockState5, count4, 5, 5, distanceAggregator);
         }

         this.applyWorld(world, iBlockState5, count3, 5, 5, distanceAggregator);
         this.applyWorld(world, distanceAggregator, count + 11, 1, 12, count + 13, 7, 12, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, count + 12, 1, 11, count + 12, 7, 13, iBlockState4, iBlockState4, false);
      }
   }

   public GoldenStaff(Random random, int count, int count2, EnumFacing enumFacing) {
      super(0);
      this.enumFacing = enumFacing;
      switch (GoldenStaffEnumFacingSwitchMap.countArray[this.enumFacing.ordinal()]) {
         case 1:
         case 2:
            this.distanceAggregator = new DistanceAggregator(count, 39, count2, count + 58 - 1, 61, count2 + 58 - 1);
            break;
         default:
            this.distanceAggregator = new DistanceAggregator(count, 39, count2, count + 58 - 1, 61, count2 + 58 - 1);
      }

      List list2 = this.resolveList(random);
      this.enumFacingController2.enabled = true;
      this.list.add(new HitBoxMatcher(this.enumFacing, this.enumFacingController2));
      this.list.add(new GoldenArtifact(this.enumFacing, this.enumFacingController3, random));
      ArrayList list3 = Lists.newArrayList();
      list3.add(new CustomItemEntryAdaptCustomItemEntryAdaptSe(null));
      list3.add(new CustomItemEntryAdaptCustomItemEntryAdaptSeHandler(null));
      list3.add(new CustomItemEntryAdaptCustomItemEntryAdaptSeImpl(null));
      list3.add(new EnumFacingControllerCustomItemEntryAdaptSe(null));
      list3.add(new CustomItemEntryAdaptCustomItemEntryAdaptSeAdapter(null));
      list3.add(new CustomItemEntryAdaptCustomItemEntryAdaptSeProvider(null));
      list3.add(new CustomItemEntryAdaptCustomItemEntryAdaptSeBridge(null));

      for (EnumFacingController enumFacingController : (Iterable<EnumFacingController>) (list2)) {
         if (!enumFacingController.enabled && !enumFacingController.isActive()) {
            for (CustomItemEntryAdaptService customItemEntryAdaptService : (Iterable<CustomItemEntryAdaptService>) (list3)) {
               if (customItemEntryAdaptService.matchesEnumFacingController(enumFacingController)) {
                  this.list.add(customItemEntryAdaptService.resolveCustomItemEntryAdapter(this.enumFacing, enumFacingController, random));
                  break;
               }
            }
         }
      }

      int count3 = this.distanceAggregator.count6;
      int count4 = this.computeCountByIndex(9, 22);
      int count5 = this.computeCount(9, 22);
      Iterator iterator = this.list.iterator();

      while (iterator.hasNext()) {
         ((CustomItemEntryAdapter)iterator.next()).getDistanceAggregator().applyIndex(count4, count3, count5);
      }

      DistanceAggregator distanceAggregator = DistanceAggregator.resolveDistanceAggregator(
         this.computeCountByIndex(1, 1),
         this.computeCount(1),
         this.computeCount(1, 1),
         this.computeCountByIndex(23, 21),
         this.computeCount(8),
         this.computeCount(23, 21)
      );
      DistanceAggregator distanceAggregator2 = DistanceAggregator.resolveDistanceAggregator(
         this.computeCountByIndex(34, 1),
         this.computeCount(1),
         this.computeCount(34, 1),
         this.computeCountByIndex(56, 21),
         this.computeCount(8),
         this.computeCount(56, 21)
      );
      DistanceAggregator distanceAggregator3 = DistanceAggregator.resolveDistanceAggregator(
         this.computeCountByIndex(22, 22),
         this.computeCount(13),
         this.computeCount(22, 22),
         this.computeCountByIndex(35, 35),
         this.computeCount(17),
         this.computeCount(35, 35)
      );
      int count6 = random.nextInt();
      this.list.add(new WorldCustomItemEntryAdapter(this.enumFacing, distanceAggregator, count6++));
      this.list.add(new WorldCustomItemEntryAdapter(this.enumFacing, distanceAggregator2, count6++));
      this.list.add(new HitBoxCalculator(this.enumFacing, distanceAggregator3));
   }

   private void applyWorld3(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesDistanceAggregator(distanceAggregator, 22, 5, 35, 17)) {
         this.applyWorld(world, distanceAggregator, 25, 0, 0, 32, 8, 20, false);

         for (int index = 0; index < 4; index++) {
            this.applyWorld(world, distanceAggregator, 24, 2, 5 + index * 4, 24, 4, 5 + index * 4, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 22, 4, 5 + index * 4, 23, 4, 5 + index * 4, iBlockState, iBlockState, false);
            this.applyWorld(world, iBlockState, 25, 5, 5 + index * 4, distanceAggregator);
            this.applyWorld(world, iBlockState, 26, 6, 5 + index * 4, distanceAggregator);
            this.applyWorld(world, iBlockState3, 26, 5, 5 + index * 4, distanceAggregator);
            this.applyWorld(world, distanceAggregator, 33, 2, 5 + index * 4, 33, 4, 5 + index * 4, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 34, 4, 5 + index * 4, 35, 4, 5 + index * 4, iBlockState, iBlockState, false);
            this.applyWorld(world, iBlockState, 32, 5, 5 + index * 4, distanceAggregator);
            this.applyWorld(world, iBlockState, 31, 6, 5 + index * 4, distanceAggregator);
            this.applyWorld(world, iBlockState3, 31, 5, 5 + index * 4, distanceAggregator);
            this.applyWorld(world, distanceAggregator, 27, 6, 5 + index * 4, 30, 6, 5 + index * 4, iBlockState4, iBlockState4, false);
         }
      }
   }

   public GoldenStaff() {
   }

   private void applyWorld5(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesDistanceAggregator(distanceAggregator, 21, 21, 36, 36)) {
         this.applyWorld(world, distanceAggregator, 21, 0, 22, 36, 0, 36, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 21, 1, 22, 36, 23, 36, false);

         for (int index = 0; index < 4; index++) {
            this.applyWorld(world, distanceAggregator, 21 + index, 13 + index, 21 + index, 36 - index, 13 + index, 21 + index, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 21 + index, 13 + index, 36 - index, 36 - index, 13 + index, 36 - index, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 21 + index, 13 + index, 22 + index, 21 + index, 13 + index, 35 - index, iBlockState, iBlockState, false);
            this.applyWorld(world, distanceAggregator, 36 - index, 13 + index, 22 + index, 36 - index, 13 + index, 35 - index, iBlockState, iBlockState, false);
         }

         this.applyWorld(world, distanceAggregator, 25, 16, 25, 32, 16, 32, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 25, 17, 25, 25, 19, 25, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 32, 17, 25, 32, 19, 25, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 25, 17, 32, 25, 19, 32, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 32, 17, 32, 32, 19, 32, iBlockState, iBlockState, false);
         this.applyWorld(world, iBlockState, 26, 20, 26, distanceAggregator);
         this.applyWorld(world, iBlockState, 27, 21, 27, distanceAggregator);
         this.applyWorld(world, iBlockState3, 27, 20, 27, distanceAggregator);
         this.applyWorld(world, iBlockState, 26, 20, 31, distanceAggregator);
         this.applyWorld(world, iBlockState, 27, 21, 30, distanceAggregator);
         this.applyWorld(world, iBlockState3, 27, 20, 30, distanceAggregator);
         this.applyWorld(world, iBlockState, 31, 20, 31, distanceAggregator);
         this.applyWorld(world, iBlockState, 30, 21, 30, distanceAggregator);
         this.applyWorld(world, iBlockState3, 30, 20, 30, distanceAggregator);
         this.applyWorld(world, iBlockState, 31, 20, 26, distanceAggregator);
         this.applyWorld(world, iBlockState, 30, 21, 27, distanceAggregator);
         this.applyWorld(world, iBlockState3, 30, 20, 27, distanceAggregator);
         this.applyWorld(world, distanceAggregator, 28, 21, 27, 29, 21, 27, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 27, 21, 28, 27, 21, 29, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 28, 21, 30, 29, 21, 30, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 30, 21, 28, 30, 21, 29, iBlockState4, iBlockState4, false);
      }
   }

   private void applyWorld2(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesDistanceAggregator(distanceAggregator, 14, 21, 20, 43)) {
         this.applyWorld(world, distanceAggregator, 14, 0, 21, 20, 0, 43, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 14, 1, 22, 20, 14, 43, false);
         this.applyWorld(world, distanceAggregator, 18, 12, 22, 20, 12, 39, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 18, 12, 21, 20, 12, 21, iBlockState, iBlockState, false);

         for (int index = 0; index < 4; index++) {
            this.applyWorld(world, distanceAggregator, index + 14, index + 9, 21, index + 14, index + 9, 43 - index, iBlockState, iBlockState, false);
         }

         for (int byteValue = 23; byteValue <= 39; byteValue += 3) {
            this.applyWorld(world, iBlockState5, 19, 13, byteValue, distanceAggregator);
         }
      }

      if (this.matchesDistanceAggregator(distanceAggregator, 37, 21, 43, 43)) {
         this.applyWorld(world, distanceAggregator, 37, 0, 21, 43, 0, 43, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 37, 1, 22, 43, 14, 43, false);
         this.applyWorld(world, distanceAggregator, 37, 12, 22, 39, 12, 39, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 37, 12, 21, 39, 12, 21, iBlockState, iBlockState, false);

         for (int index2 = 0; index2 < 4; index2++) {
            this.applyWorld(world, distanceAggregator, 43 - index2, index2 + 9, 21, 43 - index2, index2 + 9, 43 - index2, iBlockState, iBlockState, false);
         }

         for (int byteValue2 = 23; byteValue2 <= 39; byteValue2 += 3) {
            this.applyWorld(world, iBlockState5, 38, 13, byteValue2, distanceAggregator);
         }
      }

      if (this.matchesDistanceAggregator(distanceAggregator, 15, 37, 42, 43)) {
         this.applyWorld(world, distanceAggregator, 21, 0, 37, 36, 0, 43, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 21, 1, 37, 36, 14, 43, false);
         this.applyWorld(world, distanceAggregator, 21, 12, 37, 36, 12, 39, iBlockState4, iBlockState4, false);

         for (int index3 = 0; index3 < 4; index3++) {
            this.applyWorld(world, distanceAggregator, 15 + index3, index3 + 9, 43 - index3, 42 - index3, index3 + 9, 43 - index3, iBlockState, iBlockState, false);
         }

         for (int byteValue3 = 21; byteValue3 <= 36; byteValue3 += 3) {
            this.applyWorld(world, iBlockState5, byteValue3, 13, 38, distanceAggregator);
         }
      }
   }

   private List resolveList(Random random) {
      EnumFacingController[] enumFacingControllerArray = new EnumFacingController[75];

      for (int index = 0; index < 5; index++) {
         for (int index2 = 0; index2 < 4; index2++) {
            int count = computeCount(index, 0, index2);
            enumFacingControllerArray[count] = new EnumFacingController(count);
         }
      }

      for (int index3 = 0; index3 < 5; index3++) {
         for (int index4 = 0; index4 < 4; index4++) {
            int count6 = computeCount(index3, 1, index4);
            enumFacingControllerArray[count6] = new EnumFacingController(count6);
         }
      }

      for (int index5 = 1; index5 < 4; index5++) {
         for (int index6 = 0; index6 < 2; index6++) {
            int count7 = computeCount(index5, 2, index6);
            enumFacingControllerArray[count7] = new EnumFacingController(count7);
         }
      }

      this.enumFacingController2 = enumFacingControllerArray[count2];

      for (int index7 = 0; index7 < 5; index7++) {
         for (int index8 = 0; index8 < 5; index8++) {
            for (int index9 = 0; index9 < 3; index9++) {
               int count8 = computeCount(index7, index9, index8);
               if (enumFacingControllerArray[count8] != null) {
                  for (EnumFacing enumFacing : EnumFacing.getEntries()) {
                     int count9 = index7 + enumFacing.getInt2();
                     int count10 = index9 + enumFacing.getInt3();
                     int count11 = index8 + enumFacing.getInt();
                     if (count9 >= 0 && count9 < 5 && count11 >= 0 && count11 < 5 && count10 >= 0 && count10 < 3) {
                        int count12 = computeCount(count9, count10, count11);
                        if (enumFacingControllerArray[count12] != null) {
                           if (count11 != index8) {
                              enumFacingControllerArray[count8].applyEnumFacing(enumFacing.getEnumFacing5(), enumFacingControllerArray[count12]);
                           } else {
                              enumFacingControllerArray[count8].applyEnumFacing(enumFacing, enumFacingControllerArray[count12]);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      EnumFacingController enumFacingController;
      enumFacingControllerArray[count3].applyEnumFacing(EnumFacing.UP, enumFacingController = new EnumFacingController(1003));
      EnumFacingController enumFacingController4;
      enumFacingControllerArray[count4].applyEnumFacing(EnumFacing.SOUTH, enumFacingController4 = new EnumFacingController(1001));
      EnumFacingController enumFacingController5;
      enumFacingControllerArray[count5].applyEnumFacing(EnumFacing.SOUTH, enumFacingController5 = new EnumFacingController(1002));
      enumFacingController.enabled = true;
      enumFacingController4.enabled = true;
      enumFacingController5.enabled = true;
      this.enumFacingController2.enabled2 = true;
      this.enumFacingController3 = enumFacingControllerArray[computeCount(random.nextInt(4), 0, 2)];
      this.enumFacingController3.enabled = true;
      this.enumFacingController3.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()].enabled = true;
      this.enumFacingController3.enumFacingControllerArray[EnumFacing.NORTH.getIndexOffset1()].enabled = true;
      this.enumFacingController3.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()].enumFacingControllerArray[EnumFacing.NORTH.getIndexOffset1()].enabled = true;
      this.enumFacingController3.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()].enabled = true;
      this.enumFacingController3.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()].enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()].enabled = true;
      this.enumFacingController3.enumFacingControllerArray[EnumFacing.NORTH.getIndexOffset1()].enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()].enabled = true;
      this.enumFacingController3.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()].enumFacingControllerArray[EnumFacing.NORTH.getIndexOffset1()].enumFacingControllerArray[EnumFacing.UP
            .getIndexOffset1()]
         .enabled = true;
      ArrayList list2 = Lists.newArrayList();

      for (EnumFacingController enumFacingController6 : enumFacingControllerArray) {
         if (enumFacingController6 != null) {
            enumFacingController6.updateState();
            list2.add(enumFacingController6);
         }
      }

      enumFacingController.updateState();
      Collections.shuffle(list2, random);
      int count13 = 1;

      for (EnumFacingController enumFacingController7 : (Iterable<EnumFacingController>) (list2)) {
         int count14 = 0;
         int count15 = 0;

         while (count14 < 2 && count15 < 5) {
            count15++;
            int count16 = random.nextInt(6);
            if (enumFacingController7.enabledArray[count16]) {
               int indexOffset = EnumFacing.getEnumFacing6(count16).getEnumFacing5().getIndexOffset1();
               enumFacingController7.enabledArray[count16] = false;
               enumFacingController7.enumFacingControllerArray[count16].enabledArray[indexOffset] = false;
               if (enumFacingController7.checkIndex(count13++) && enumFacingController7.enumFacingControllerArray[count16].checkIndex(count13++)) {
                  count14++;
               } else {
                  enumFacingController7.enabledArray[count16] = true;
                  enumFacingController7.enumFacingControllerArray[count16].enabledArray[indexOffset] = true;
               }
            }
         }
      }

      list2.add(enumFacingController);
      list2.add(enumFacingController4);
      list2.add(enumFacingController5);
      return list2;
   }

   @Override
   public boolean matchesWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      int count = Math.max(world.getIndexOffset1(), 64) - this.distanceAggregator.count6;
      this.applyWorld(world, distanceAggregator, 0, 0, 0, 58, count, 58, false);
      this.applyWorld(false, 0, world, random, distanceAggregator);
      this.applyWorld(true, 33, world, random, distanceAggregator);
      this.applyWorld3(world, random, distanceAggregator);
      this.applyWorld(world, random, distanceAggregator);
      this.applyWorld5(world, random, distanceAggregator);
      this.applyWorld4(world, random, distanceAggregator);
      this.applyWorldByWorld(world, random, distanceAggregator);
      this.applyWorld2(world, random, distanceAggregator);

      for (int index = 0; index < 7; index++) {
         int count2 = 0;

         while (count2 < 7) {
            if (count2 == 0 && index == 3) {
               count2 = 6;
            }

            int count3 = index * 9;
            int count4 = count2 * 9;

            for (int index2 = 0; index2 < 4; index2++) {
               for (int index3 = 0; index3 < 4; index3++) {
                  this.applyWorld(world, iBlockState, count3 + index2, 0, count4 + index3, distanceAggregator);
                  this.applyWorldByWorld(world, iBlockState, count3 + index2, -1, count4 + index3, distanceAggregator);
               }
            }

            if (index != 0 && index != 6) {
               count2 += 6;
            } else {
               count2++;
            }
         }
      }

      for (int index4 = 0; index4 < 5; index4++) {
         this.applyWorld(world, distanceAggregator, -1 - index4, 0 + index4 * 2, -1 - index4, -1 - index4, 23, 58 + index4, false);
         this.applyWorld(world, distanceAggregator, 58 + index4, 0 + index4 * 2, -1 - index4, 58 + index4, 23, 58 + index4, false);
         this.applyWorld(world, distanceAggregator, 0 - index4, 0 + index4 * 2, -1 - index4, 57 + index4, 23, -1 - index4, false);
         this.applyWorld(world, distanceAggregator, 0 - index4, 0 + index4 * 2, 58 + index4, 57 + index4, 23, 58 + index4, false);
      }

      for (CustomItemEntryAdapter customItemEntryAdapter : (Iterable<CustomItemEntryAdapter>) (this.list)) {
         if (customItemEntryAdapter.getDistanceAggregator().matchesDistanceAggregator(distanceAggregator)) {
            customItemEntryAdapter.matchesWorld(world, random, distanceAggregator);
         }
      }

      return true;
   }

   private void applyWorldByWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesDistanceAggregator(distanceAggregator, 7, 21, 13, 50)) {
         this.applyWorld(world, distanceAggregator, 7, 0, 21, 13, 0, 50, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 7, 1, 21, 13, 10, 50, false);
         this.applyWorld(world, distanceAggregator, 11, 8, 21, 13, 8, 53, iBlockState4, iBlockState4, false);

         for (int index = 0; index < 4; index++) {
            this.applyWorld(world, distanceAggregator, index + 7, index + 5, 21, index + 7, index + 5, 54, iBlockState, iBlockState, false);
         }

         for (int byteValue = 21; byteValue <= 45; byteValue += 3) {
            this.applyWorld(world, iBlockState5, 12, 9, byteValue, distanceAggregator);
         }
      }

      if (this.matchesDistanceAggregator(distanceAggregator, 44, 21, 50, 54)) {
         this.applyWorld(world, distanceAggregator, 44, 0, 21, 50, 0, 50, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 44, 1, 21, 50, 10, 50, false);
         this.applyWorld(world, distanceAggregator, 44, 8, 21, 46, 8, 53, iBlockState4, iBlockState4, false);

         for (int index2 = 0; index2 < 4; index2++) {
            this.applyWorld(world, distanceAggregator, 50 - index2, index2 + 5, 21, 50 - index2, index2 + 5, 54, iBlockState, iBlockState, false);
         }

         for (int byteValue2 = 21; byteValue2 <= 45; byteValue2 += 3) {
            this.applyWorld(world, iBlockState5, 45, 9, byteValue2, distanceAggregator);
         }
      }

      if (this.matchesDistanceAggregator(distanceAggregator, 8, 44, 49, 54)) {
         this.applyWorld(world, distanceAggregator, 14, 0, 44, 43, 0, 50, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 14, 1, 44, 43, 10, 50, false);

         for (int byteValue3 = 12; byteValue3 <= 45; byteValue3 += 3) {
            this.applyWorld(world, iBlockState5, byteValue3, 9, 45, distanceAggregator);
            this.applyWorld(world, iBlockState5, byteValue3, 9, 52, distanceAggregator);
            if (byteValue3 == 12 || byteValue3 == 18 || byteValue3 == 24 || byteValue3 == 33 || byteValue3 == 39 || byteValue3 == 45) {
               this.applyWorld(world, iBlockState5, byteValue3, 9, 47, distanceAggregator);
               this.applyWorld(world, iBlockState5, byteValue3, 9, 50, distanceAggregator);
               this.applyWorld(world, iBlockState5, byteValue3, 10, 45, distanceAggregator);
               this.applyWorld(world, iBlockState5, byteValue3, 10, 46, distanceAggregator);
               this.applyWorld(world, iBlockState5, byteValue3, 10, 51, distanceAggregator);
               this.applyWorld(world, iBlockState5, byteValue3, 10, 52, distanceAggregator);
               this.applyWorld(world, iBlockState5, byteValue3, 11, 47, distanceAggregator);
               this.applyWorld(world, iBlockState5, byteValue3, 11, 50, distanceAggregator);
               this.applyWorld(world, iBlockState5, byteValue3, 12, 48, distanceAggregator);
               this.applyWorld(world, iBlockState5, byteValue3, 12, 49, distanceAggregator);
            }
         }

         for (int index3 = 0; index3 < 3; index3++) {
            this.applyWorld(world, distanceAggregator, 8 + index3, 5 + index3, 54, 49 - index3, 5 + index3, 54, iBlockState4, iBlockState4, false);
         }

         this.applyWorld(world, distanceAggregator, 11, 8, 54, 46, 8, 54, iBlockState, iBlockState, false);
         this.applyWorld(world, distanceAggregator, 14, 8, 44, 43, 8, 53, iBlockState4, iBlockState4, false);
      }
   }

   private void applyWorld(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesDistanceAggregator(distanceAggregator, 15, 20, 42, 21)) {
         this.applyWorld(world, distanceAggregator, 15, 0, 21, 42, 0, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 26, 1, 21, 31, 3, 21, false);
         this.applyWorld(world, distanceAggregator, 21, 12, 21, 36, 12, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 17, 11, 21, 40, 11, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 16, 10, 21, 41, 10, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 15, 7, 21, 42, 9, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 16, 6, 21, 41, 6, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 17, 5, 21, 40, 5, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 21, 4, 21, 36, 4, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 22, 3, 21, 26, 3, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 31, 3, 21, 35, 3, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 23, 2, 21, 25, 2, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 32, 2, 21, 34, 2, 21, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 28, 4, 20, 29, 4, 21, iBlockState, iBlockState, false);
         this.applyWorld(world, iBlockState, 27, 3, 21, distanceAggregator);
         this.applyWorld(world, iBlockState, 30, 3, 21, distanceAggregator);
         this.applyWorld(world, iBlockState, 26, 2, 21, distanceAggregator);
         this.applyWorld(world, iBlockState, 31, 2, 21, distanceAggregator);
         this.applyWorld(world, iBlockState, 25, 1, 21, distanceAggregator);
         this.applyWorld(world, iBlockState, 32, 1, 21, distanceAggregator);

         for (int index = 0; index < 7; index++) {
            this.applyWorld(world, iBlockState2, 28 - index, 6 + index, 21, distanceAggregator);
            this.applyWorld(world, iBlockState2, 29 + index, 6 + index, 21, distanceAggregator);
         }

         for (int index2 = 0; index2 < 4; index2++) {
            this.applyWorld(world, iBlockState2, 28 - index2, 9 + index2, 21, distanceAggregator);
            this.applyWorld(world, iBlockState2, 29 + index2, 9 + index2, 21, distanceAggregator);
         }

         this.applyWorld(world, iBlockState2, 28, 12, 21, distanceAggregator);
         this.applyWorld(world, iBlockState2, 29, 12, 21, distanceAggregator);

         for (int index3 = 0; index3 < 3; index3++) {
            this.applyWorld(world, iBlockState2, 22 - index3 * 2, 8, 21, distanceAggregator);
            this.applyWorld(world, iBlockState2, 22 - index3 * 2, 9, 21, distanceAggregator);
            this.applyWorld(world, iBlockState2, 35 + index3 * 2, 8, 21, distanceAggregator);
            this.applyWorld(world, iBlockState2, 35 + index3 * 2, 9, 21, distanceAggregator);
         }

         this.applyWorld(world, distanceAggregator, 15, 13, 21, 42, 15, 21, false);
         this.applyWorld(world, distanceAggregator, 15, 1, 21, 15, 6, 21, false);
         this.applyWorld(world, distanceAggregator, 16, 1, 21, 16, 5, 21, false);
         this.applyWorld(world, distanceAggregator, 17, 1, 21, 20, 4, 21, false);
         this.applyWorld(world, distanceAggregator, 21, 1, 21, 21, 3, 21, false);
         this.applyWorld(world, distanceAggregator, 22, 1, 21, 22, 2, 21, false);
         this.applyWorld(world, distanceAggregator, 23, 1, 21, 24, 1, 21, false);
         this.applyWorld(world, distanceAggregator, 42, 1, 21, 42, 6, 21, false);
         this.applyWorld(world, distanceAggregator, 41, 1, 21, 41, 5, 21, false);
         this.applyWorld(world, distanceAggregator, 37, 1, 21, 40, 4, 21, false);
         this.applyWorld(world, distanceAggregator, 36, 1, 21, 36, 3, 21, false);
         this.applyWorld(world, distanceAggregator, 33, 1, 21, 34, 1, 21, false);
         this.applyWorld(world, distanceAggregator, 35, 1, 21, 35, 2, 21, false);
      }
   }

   private void applyWorld4(World world, Random random, DistanceAggregator distanceAggregator) {
      if (this.matchesDistanceAggregator(distanceAggregator, 0, 21, 6, 58)) {
         this.applyWorld(world, distanceAggregator, 0, 0, 21, 6, 0, 57, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 0, 1, 21, 6, 7, 57, false);
         this.applyWorld(world, distanceAggregator, 4, 4, 21, 6, 4, 53, iBlockState4, iBlockState4, false);

         for (int index = 0; index < 4; index++) {
            this.applyWorld(world, distanceAggregator, index, index + 1, 21, index, index + 1, 57 - index, iBlockState, iBlockState, false);
         }

         for (int byteValue = 23; byteValue < 53; byteValue += 3) {
            this.applyWorld(world, iBlockState5, 5, 5, byteValue, distanceAggregator);
         }

         this.applyWorld(world, iBlockState5, 5, 5, 52, distanceAggregator);

         for (int index2 = 0; index2 < 4; index2++) {
            this.applyWorld(world, distanceAggregator, index2, index2 + 1, 21, index2, index2 + 1, 57 - index2, iBlockState, iBlockState, false);
         }

         this.applyWorld(world, distanceAggregator, 4, 1, 52, 6, 3, 52, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 5, 1, 51, 5, 3, 53, iBlockState4, iBlockState4, false);
      }

      if (this.matchesDistanceAggregator(distanceAggregator, 51, 21, 58, 58)) {
         this.applyWorld(world, distanceAggregator, 51, 0, 21, 57, 0, 57, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 51, 1, 21, 57, 7, 57, false);
         this.applyWorld(world, distanceAggregator, 51, 4, 21, 53, 4, 53, iBlockState4, iBlockState4, false);

         for (int index3 = 0; index3 < 4; index3++) {
            this.applyWorld(world, distanceAggregator, 57 - index3, index3 + 1, 21, 57 - index3, index3 + 1, 57 - index3, iBlockState, iBlockState, false);
         }

         for (int byteValue2 = 23; byteValue2 < 53; byteValue2 += 3) {
            this.applyWorld(world, iBlockState5, 52, 5, byteValue2, distanceAggregator);
         }

         this.applyWorld(world, iBlockState5, 52, 5, 52, distanceAggregator);
         this.applyWorld(world, distanceAggregator, 51, 1, 52, 53, 3, 52, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 52, 1, 51, 52, 3, 53, iBlockState4, iBlockState4, false);
      }

      if (this.matchesDistanceAggregator(distanceAggregator, 0, 51, 57, 57)) {
         this.applyWorld(world, distanceAggregator, 7, 0, 51, 50, 0, 57, iBlockState4, iBlockState4, false);
         this.applyWorld(world, distanceAggregator, 7, 1, 51, 50, 10, 57, false);

         for (int index4 = 0; index4 < 4; index4++) {
            this.applyWorld(world, distanceAggregator, index4 + 1, index4 + 1, 57 - index4, 56 - index4, index4 + 1, 57 - index4, iBlockState, iBlockState, false);
         }
      }
   }
}
