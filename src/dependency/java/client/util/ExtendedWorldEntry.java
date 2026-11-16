package client.util;

import client.event.CompositeListener;
import client.setting.MovementThresholdProperty;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BaseBlockEntry;
import net.minecraft.block.Blocks;
import net.minecraft.block.CustomBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class ExtendedWorldEntry implements IChunkProvider {
   public final World world;
   public final boolean enabled;
   public final Random random;
   public double[] valueArray = new double[256];
   public double[] valueArray2 = new double[256];
   public double[] valueArray3 = new double[256];
   public double[] valueArray4;
   public final ClientManagerService clientManagerService;
   public final ClientManagerService clientManagerService2;
   public final ClientManagerService clientManagerService3;
   public final ClientManagerService clientManagerService4;
   public final ClientManagerService clientManagerService5;
   public final ClientManagerService clientManagerService6;
   public final ClientManagerService clientManagerService7;
   public final RotationCoordinator rotationCoordinator = new RotationCoordinator();
   public final RotationPredictor rotationPredictor = new RotationPredictor();
   public final RotationBuffer rotationBuffer = new RotationBuffer();
   public final CurveParser curveParser = new IBlockStateEntry(
      Blocks.block4.getDefaultState(), 14, SpecialPredicateAdapter.resolveSpecialPredicateAdapter(Blocks.block96)
   );
   public final BaseBlockEntry baseBlockEntry = new BaseBlockEntry(Blocks.blockFlowingLava2, true);
   public final BaseBlockEntry baseBlockEntry2 = new BaseBlockEntry(Blocks.blockFlowingLava2, false);
   public final AuxiliaryClientManagerController auxiliaryClientManagerController = new AuxiliaryClientManagerController(Blocks.secondaryBlockAdapter2);
   public final AuxiliaryClientManagerController auxiliaryClientManagerController2 = new AuxiliaryClientManagerController(Blocks.secondaryBlockAdapter);
   public final VirtualListEntry virtualListEntry = new VirtualListEntry();
   public final TertiaryRandomEntry tertiaryRandomEntry = new AuxiliaryRandomEntryAdapter();
   public double[] valueArray5;
   public double[] valueArray6;
   public double[] valueArray7;
   public double[] valueArray8;
   public double[] valueArray9;

   public void applyShortArrayEntry(int count, int count2, ShortArrayEntry shortArrayEntry) {
      int count3 = this.world.getIndexOffset1() / 2 + 1;
      this.valueArray4 = this.resolveDoubleArray(this.valueArray4, count * 4, 0, count2 * 4, 5, 17, 5);

      for (int index = 0; index < 4; index++) {
         for (int index2 = 0; index2 < 4; index2++) {
            for (int index3 = 0; index3 < 16; index3++) {
               double offset = 0.125;
               double offset2 = this.valueArray4[((index + 0) * 5 + index2 + 0) * 17 + index3 + 0];
               double offset3 = this.valueArray4[((index + 0) * 5 + index2 + 1) * 17 + index3 + 0];
               double offset4 = this.valueArray4[((index + 1) * 5 + index2 + 0) * 17 + index3 + 0];
               double offset5 = this.valueArray4[((index + 1) * 5 + index2 + 1) * 17 + index3 + 0];
               double offset6 = (this.valueArray4[((index + 0) * 5 + index2 + 0) * 17 + index3 + 1] - offset2) * offset;
               double offset7 = (this.valueArray4[((index + 0) * 5 + index2 + 1) * 17 + index3 + 1] - offset3) * offset;
               double offset8 = (this.valueArray4[((index + 1) * 5 + index2 + 0) * 17 + index3 + 1] - offset4) * offset;
               double offset9 = (this.valueArray4[((index + 1) * 5 + index2 + 1) * 17 + index3 + 1] - offset5) * offset;

               for (int index4 = 0; index4 < 8; index4++) {
                  double offset10 = 0.25;
                  double offset11 = offset2;
                  double offset12 = offset3;
                  double offset13 = (offset4 - offset2) * offset10;
                  double offset14 = (offset5 - offset3) * offset10;

                  for (int index5 = 0; index5 < 4; index5++) {
                     double offset15 = 0.25;
                     double offset16 = offset11;
                     double offset17 = (offset12 - offset11) * offset15;

                     for (int index6 = 0; index6 < 4; index6++) {
                        IBlockState blockState = null;
                        if (index3 * 8 + index4 < count3) {
                           blockState = Blocks.blockLava.getDefaultState();
                        }

                        if (offset16 > 0.0) {
                           blockState = Blocks.block96.getDefaultState();
                        }

                        int count4 = index5 + index * 4;
                        int count5 = index4 + index3 * 8;
                        int count6 = index6 + index2 * 4;
                        shortArrayEntry.applyIBlockState(count4, count5, count6, blockState);
                        offset16 += offset17;
                     }

                     offset11 += offset13;
                     offset12 += offset14;
                  }

                  offset2 += offset6;
                  offset3 += offset7;
                  offset4 += offset8;
                  offset5 += offset9;
               }
            }
         }
      }
   }

   @Override
   public boolean matchesIChunkProvider(IChunkProvider iChunkProvider, AlertNotificationSound alertNotificationSound, int count, int count2) {
      return false;
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(int count, int count2) {
      this.random.setSeed((long)count * 341873128712L + (long)count2 * 132897987541L);
      ShortArrayEntry shortArrayEntry = new ShortArrayEntry();
      this.applyShortArrayEntry(count, count2, shortArrayEntry);
      this.applyShortArrayEntryByShortArrayEntry(count, count2, shortArrayEntry);
      this.tertiaryRandomEntry.applyIChunkProvider(this, this.world, count, count2, shortArrayEntry);
      if (this.enabled) {
         this.virtualListEntry.applyIChunkProvider(this, this.world, count, count2, shortArrayEntry);
      }

      AlertNotificationSound alertNotificationSound = new AlertNotificationSound(this.world, shortArrayEntry, count, count2);
      BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray = this.world
         .getInvalidBiomeIdManager()
         .resolveBiomeIDIsOutOfBoundsManagerA2((BiomeIDIsOutOfBoundsManager[])null, count * 16, count2 * 16, 16, 16);
      byte[] byteFlagArray = alertNotificationSound.getByteFlagArray();

      for (int index = 0; index < byteFlagArray.length; index++) {
         byteFlagArray[index] = (byte)biomeIDIsOutOfBoundsManagerArray[index].count;
      }

      alertNotificationSound.updateState();
      return alertNotificationSound;
   }

   @Override
   public String getText() {
      return "HellRandomLevelSource";
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public BlockPos resolveBlockPos(World world2, String text, BlockPos blockPos) {
      return null;
   }

   private double[] resolveDoubleArray(double[] offsetArray, int count, int count2, int count3, int count4, int count5, int count6) {
      double[] offsetArray2 = offsetArray;
      if (offsetArray == null) {
         offsetArray2 = new double[count4 * count5 * count6];
      }

      double offset = 684.412;
      double offset2 = 2053.236;
      this.valueArray8 = this.clientManagerService6.resolveDoubleArray(this.valueArray8, count, count2, count3, count4, 1, count6, 1.0, 0.0, 1.0);
      this.valueArray9 = this.clientManagerService7.resolveDoubleArray(this.valueArray9, count, count2, count3, count4, 1, count6, 100.0, 0.0, 100.0);
      this.valueArray5 = this.clientManagerService3
         .resolveDoubleArray(this.valueArray5, count, count2, count3, count4, count5, count6, offset / 80.0, offset2 / 60.0, offset / 80.0);
      this.valueArray6 = this.clientManagerService.resolveDoubleArray(this.valueArray6, count, count2, count3, count4, count5, count6, offset, offset2, offset);
      this.valueArray7 = this.clientManagerService2.resolveDoubleArray(this.valueArray7, count, count2, count3, count4, count5, count6, offset, offset2, offset);
      int count7 = 0;
      double[] offsetArray3 = new double[count5];

      for (int index = 0; index < count5; index++) {
         offsetArray3[index] = Math.cos((double)index * Math.PI * 6.0 / (double)count5) * 2.0;
         double offset3 = (double)index;
         if (index > count5 / 2) {
            offset3 = (double)(count5 - 1 - index);
         }

         if (offset3 < 4.0) {
            double offset4 = 4.0 - offset3;
            offsetArray3[index] -= offset4 * offset4 * offset4 * 10.0;
         }
      }

      for (int index2 = 0; index2 < count4; index2++) {
         for (int index3 = 0; index3 < count6; index3++) {
            double offset5 = 0.0;

            for (int index4 = 0; index4 < count5; index4++) {
               double offset6 = offsetArray3[index4];
               double offset7 = this.valueArray6[count7] / 512.0;
               double offset8 = this.valueArray7[count7] / 512.0;
               double offset9 = (this.valueArray5[count7] / 10.0 + 1.0) / 2.0;
               if (!(offset9 < 0.0)) {
                  if (offset9 > 1.0) {
                     offset7 = offset8;
                  } else {
                     offset7 += (offset8 - offset7) * offset9;
                  }
               }

               double offset10 = offset7 - offset6;
               if (index4 > count5 - 4) {
                  double offset11 = (double)((float)(index4 - (count5 - 4)) / 3.0F);
                  offset10 = offset10 * (1.0 - offset11) + -10.0 * offset11;
               }

               if ((double)index4 < offset5) {
                  double doubleItem = MathHelper.getDouble7((offset5 - (double)index4) / 4.0, 0.0, 1.0);
                  offset10 = offset10 * (1.0 - doubleItem) + -10.0 * doubleItem;
               }

               offsetArray2[count7] = offset10;
               count7++;
            }
         }
      }

      return offsetArray2;
   }

   @Override
   public void updateState() {
   }

   @Override
   public boolean checkIndex(int count, int count2) {
      return true;
   }

   public void applyShortArrayEntryByShortArrayEntry(int count, int count2, ShortArrayEntry shortArrayEntry) {
      int count3 = this.world.getIndexOffset1() + 1;
      double offset = 0.03125;
      this.valueArray = this.clientManagerService4.resolveDoubleArray(this.valueArray, count * 16, count2 * 16, 0, 16, 16, 1, offset, offset, 1.0);
      this.valueArray2 = this.clientManagerService4.resolveDoubleArray(this.valueArray2, count * 16, 109, count2 * 16, 16, 1, 16, offset, 1.0, offset);
      this.valueArray3 = this.clientManagerService5
         .resolveDoubleArray(this.valueArray3, count * 16, count2 * 16, 0, 16, 16, 1, offset * 2.0, offset * 2.0, offset * 2.0);

      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            boolean flag = this.valueArray[index + index2 * 16] + this.random.nextDouble() * 0.2 > 0.0;
            boolean flag2 = this.valueArray2[index + index2 * 16] + this.random.nextDouble() * 0.2 > 0.0;
            int count4 = (int)(this.valueArray3[index + index2 * 16] / 3.0 + 3.0 + this.random.nextDouble() * 0.25);
            int count5 = -1;
            IBlockState defaultState = Blocks.block96.getDefaultState();
            IBlockState defaultState2 = Blocks.block96.getDefaultState();

            for (int index3 = 127; index3 >= 0; index3--) {
               if (index3 < 127 - this.random.nextInt(5) && index3 > this.random.nextInt(5)) {
                  IBlockState blockState = shortArrayEntry.resolveIBlockState(index2, index3, index);
                  if (blockState.getBlock() == null || blockState.getBlock().getMaterial() == Material.coreItemEntry) {
                     count5 = -1;
                  } else if (blockState.getBlock() == Blocks.block96) {
                     if (count5 == -1) {
                        if (count4 <= 0) {
                           defaultState = null;
                           defaultState2 = Blocks.block96.getDefaultState();
                        } else if (index3 >= count3 - 4 && index3 <= count3 + 1) {
                           defaultState = Blocks.block96.getDefaultState();
                           defaultState2 = Blocks.block96.getDefaultState();
                           if (flag2) {
                              defaultState = Blocks.block45.getDefaultState();
                              defaultState2 = Blocks.block96.getDefaultState();
                           }

                           if (flag) {
                              defaultState = Blocks.block126.getDefaultState();
                              defaultState2 = Blocks.block126.getDefaultState();
                           }
                        }

                        if (index3 < count3 && (defaultState == null || defaultState.getBlock().getMaterial() == Material.coreItemEntry)) {
                           defaultState = Blocks.blockLava.getDefaultState();
                        }

                        count5 = count4;
                        if (index3 >= count3 - 1) {
                           shortArrayEntry.applyIBlockState(index2, index3, index, defaultState);
                        } else {
                           shortArrayEntry.applyIBlockState(index2, index3, index, defaultState2);
                        }
                     } else if (count5 > 0) {
                        count5--;
                        shortArrayEntry.applyIBlockState(index2, index3, index, defaultState2);
                     }
                  }
               } else {
                  shortArrayEntry.applyIBlockState(index2, index3, index, Blocks.block95.getDefaultState());
               }
            }
         }
      }
   }

   @Override
   public int size() {
      return 0;
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   @Override
   public void applyIChunkProvider(IChunkProvider iChunkProvider, int count, int count2) {
      CustomBlockAdapter.enabled = true;
      BlockPos blockPos = new BlockPos(count * 16, 0, count2 * 16);
      InterpolationCollector interpolationCollector = new InterpolationCollector(count, count2);
      this.virtualListEntry.matchesWorld(this.world, this.random, interpolationCollector);

      for (int index = 0; index < 8; index++) {
         this.baseBlockEntry2
            .matchesWorld(this.world, this.random, blockPos.add(this.random.nextInt(16) + 8, this.random.nextInt(120) + 4, this.random.nextInt(16) + 8));
      }

      for (int index2 = 0; index2 < this.random.nextInt(this.random.nextInt(10) + 1) + 1; index2++) {
         this.rotationCoordinator
            .matchesWorld(this.world, this.random, blockPos.add(this.random.nextInt(16) + 8, this.random.nextInt(120) + 4, this.random.nextInt(16) + 8));
      }

      for (int index3 = 0; index3 < this.random.nextInt(this.random.nextInt(10) + 1); index3++) {
         this.rotationPredictor
            .matchesWorld(this.world, this.random, blockPos.add(this.random.nextInt(16) + 8, this.random.nextInt(120) + 4, this.random.nextInt(16) + 8));
      }

      for (int index4 = 0; index4 < 10; index4++) {
         this.rotationBuffer
            .matchesWorld(this.world, this.random, blockPos.add(this.random.nextInt(16) + 8, this.random.nextInt(128), this.random.nextInt(16) + 8));
      }

      if (this.random.nextBoolean()) {
         this.auxiliaryClientManagerController
            .matchesWorld(this.world, this.random, blockPos.add(this.random.nextInt(16) + 8, this.random.nextInt(128), this.random.nextInt(16) + 8));
      }

      if (this.random.nextBoolean()) {
         this.auxiliaryClientManagerController2
            .matchesWorld(this.world, this.random, blockPos.add(this.random.nextInt(16) + 8, this.random.nextInt(128), this.random.nextInt(16) + 8));
      }

      for (int index5 = 0; index5 < 16; index5++) {
         this.curveParser.matchesWorld(this.world, this.random, blockPos.add(this.random.nextInt(16), this.random.nextInt(108) + 10, this.random.nextInt(16)));
      }

      for (int index6 = 0; index6 < 16; index6++) {
         this.baseBlockEntry.matchesWorld(this.world, this.random, blockPos.add(this.random.nextInt(16), this.random.nextInt(108) + 10, this.random.nextInt(16)));
      }

      CustomBlockAdapter.enabled = false;
   }

   @Override
   public List resolveList(MovementThresholdProperty movementThresholdProperty, BlockPos blockPos) {
      if (movementThresholdProperty == MovementThresholdProperty.MONSTER) {
         if (this.virtualListEntry.matchesBlockPos(blockPos)) {
            return this.virtualListEntry.getListAsList();
         }

         if (this.virtualListEntry.matchesWorld(this.world, blockPos) && this.world.getBlockState(blockPos.down()).getBlock() == Blocks.block86) {
            return this.virtualListEntry.getListAsList();
         }
      }

      return this.world.resolveBiomeIDIsOutOfBoundsManager(blockPos).resolveList(movementThresholdProperty);
   }

   @Override
   public void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound, int count, int count2) {
      this.virtualListEntry.applyIChunkProvider(this, this.world, count, count2, (ShortArrayEntry)null);
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(BlockPos blockPos) {
      return this.resolveAlertNotificationSound(blockPos.getX() >> 4, blockPos.getZ() >> 4);
   }

   @Override
   public boolean matchesCompositeListener(boolean flag, CompositeListener compositeListener) {
      return true;
   }

   public ExtendedWorldEntry(World world2, boolean flag, long value) {
      this.world = world2;
      this.enabled = flag;
      this.random = new Random(value);
      this.clientManagerService = new ClientManagerService(this.random, 16);
      this.clientManagerService2 = new ClientManagerService(this.random, 16);
      this.clientManagerService3 = new ClientManagerService(this.random, 8);
      this.clientManagerService4 = new ClientManagerService(this.random, 4);
      this.clientManagerService5 = new ClientManagerService(this.random, 4);
      this.clientManagerService6 = new ClientManagerService(this.random, 10);
      this.clientManagerService7 = new ClientManagerService(this.random, 16);
      world2.setIndexOffset1(63);
   }
}
