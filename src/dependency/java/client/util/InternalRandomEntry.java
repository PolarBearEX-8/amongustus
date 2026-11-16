package client.util;

import client.event.CompositeListener;
import client.setting.MovementThresholdProperty;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.CustomBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class InternalRandomEntry implements IChunkProvider {
   public Random random;
   public ClientManagerService clientManagerService;
   public ClientManagerService clientManagerService2;
   public ClientManagerService clientManagerService3;
   public ClientManagerService clientManagerService4;
   public ClientManagerService clientManagerService5;
   public World world;
   public double[] valueArray;
   public BiomeIDIsOutOfBoundsManager[] biomeIDIsOutOfBoundsManagerArray;
   public double[] valueArray2;
   public double[] valueArray3;
   public double[] valueArray4;
   public double[] valueArray5;
   public double[] valueArray6;

   public InternalRandomEntry(World world2, long value) {
      this.world = world2;
      this.random = new Random(value);
      this.clientManagerService = new ClientManagerService(this.random, 16);
      this.clientManagerService2 = new ClientManagerService(this.random, 16);
      this.clientManagerService3 = new ClientManagerService(this.random, 8);
      this.clientManagerService4 = new ClientManagerService(this.random, 10);
      this.clientManagerService5 = new ClientManagerService(this.random, 16);
   }

   public void applyShortArrayEntry(int count, int count2, ShortArrayEntry shortArrayEntry) {
      this.valueArray = this.resolveDoubleArray(this.valueArray, count * 2, 0, count2 * 2, 3, 33, 3);

      for (int index = 0; index < 2; index++) {
         for (int index2 = 0; index2 < 2; index2++) {
            for (int index3 = 0; index3 < 32; index3++) {
               double offset = 0.25;
               double offset2 = this.valueArray[((index + 0) * 3 + index2 + 0) * 33 + index3 + 0];
               double offset3 = this.valueArray[((index + 0) * 3 + index2 + 1) * 33 + index3 + 0];
               double offset4 = this.valueArray[((index + 1) * 3 + index2 + 0) * 33 + index3 + 0];
               double offset5 = this.valueArray[((index + 1) * 3 + index2 + 1) * 33 + index3 + 0];
               double offset6 = (this.valueArray[((index + 0) * 3 + index2 + 0) * 33 + index3 + 1] - offset2) * offset;
               double offset7 = (this.valueArray[((index + 0) * 3 + index2 + 1) * 33 + index3 + 1] - offset3) * offset;
               double offset8 = (this.valueArray[((index + 1) * 3 + index2 + 0) * 33 + index3 + 1] - offset4) * offset;
               double offset9 = (this.valueArray[((index + 1) * 3 + index2 + 1) * 33 + index3 + 1] - offset5) * offset;

               for (int index4 = 0; index4 < 4; index4++) {
                  double offset10 = 0.125;
                  double offset11 = offset2;
                  double offset12 = offset3;
                  double offset13 = (offset4 - offset2) * offset10;
                  double offset14 = (offset5 - offset3) * offset10;

                  for (int index5 = 0; index5 < 8; index5++) {
                     double offset15 = 0.125;
                     double offset16 = offset11;
                     double offset17 = (offset12 - offset11) * offset15;

                     for (int index6 = 0; index6 < 8; index6++) {
                        IBlockState blockState = null;
                        if (offset16 > 0.0) {
                           blockState = Blocks.block28.getDefaultState();
                        }

                        int count3 = index5 + index * 8;
                        int count4 = index4 + index3 * 4;
                        int count5 = index6 + index2 * 8;
                        shortArrayEntry.applyIBlockState(count3, count4, count5, blockState);
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
   public boolean checkIndex(int count, int count2) {
      return true;
   }

   @Override
   public void applyAlertNotificationSound(AlertNotificationSound alertNotificationSound, int count, int count2) {
   }

   public void applyShortArrayEntry(ShortArrayEntry shortArrayEntry) {
      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            int count = -1;
            IBlockState defaultState = Blocks.block28.getDefaultState();
            IBlockState defaultState2 = Blocks.block28.getDefaultState();

            for (int index3 = 127; index3 >= 0; index3--) {
               IBlockState blockState = shortArrayEntry.resolveIBlockState(index, index3, index2);
               if (blockState.getBlock().getMaterial() == Material.coreItemEntry) {
                  count = -1;
               } else if (blockState.getBlock() == Blocks.stone) {
                  if (count == -1) {
                     count = 1;
                     if (index3 >= 0) {
                        shortArrayEntry.applyIBlockState(index, index3, index2, defaultState);
                     } else {
                        shortArrayEntry.applyIBlockState(index, index3, index2, defaultState2);
                     }
                  } else if (count > 0) {
                     count--;
                     shortArrayEntry.applyIBlockState(index, index3, index2, defaultState2);
                  }
               }
            }
         }
      }
   }

   @Override
   public List resolveList(MovementThresholdProperty movementThresholdProperty, BlockPos blockPos) {
      return this.world.resolveBiomeIDIsOutOfBoundsManager(blockPos).resolveList(movementThresholdProperty);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   @Override
   public String getText() {
      return "RandomLevelSource";
   }

   @Override
   public boolean matchesIChunkProvider(IChunkProvider iChunkProvider, AlertNotificationSound alertNotificationSound, int count, int count2) {
      return false;
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(BlockPos blockPos) {
      return this.resolveAlertNotificationSound(blockPos.getX() >> 4, blockPos.getZ() >> 4);
   }

   @Override
   public BlockPos resolveBlockPos(World world2, String text, BlockPos blockPos) {
      return null;
   }

   @Override
   public boolean matchesCompositeListener(boolean flag, CompositeListener compositeListener) {
      return true;
   }

   @Override
   public void applyIChunkProvider(IChunkProvider iChunkProvider, int count, int count2) {
      CustomBlockAdapter.enabled = true;
      BlockPos blockPos = new BlockPos(count * 16, 0, count2 * 16);
      this.world.resolveBiomeIDIsOutOfBoundsManager(blockPos.add(16, 0, 16)).applyWorld(this.world, this.world.random, blockPos);
      CustomBlockAdapter.enabled = false;
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   @Override
   public int size() {
      return 0;
   }

   @Override
   public void updateState() {
   }

   @Override
   public AlertNotificationSound resolveAlertNotificationSound(int count, int count2) {
      this.random.setSeed((long)count * 341873128712L + (long)count2 * 132897987541L);
      ShortArrayEntry shortArrayEntry = new ShortArrayEntry();
      this.biomeIDIsOutOfBoundsManagerArray = this.world
         .getInvalidBiomeIdManager()
         .resolveBiomeIDIsOutOfBoundsManagerA2(this.biomeIDIsOutOfBoundsManagerArray, count * 16, count2 * 16, 16, 16);
      this.applyShortArrayEntry(count, count2, shortArrayEntry);
      this.applyShortArrayEntry(shortArrayEntry);
      AlertNotificationSound alertNotificationSound = new AlertNotificationSound(this.world, shortArrayEntry, count, count2);
      byte[] byteFlagArray = alertNotificationSound.getByteFlagArray();

      for (int index = 0; index < byteFlagArray.length; index++) {
         byteFlagArray[index] = (byte)this.biomeIDIsOutOfBoundsManagerArray[index].count;
      }

      alertNotificationSound.updateState8();
      return alertNotificationSound;
   }

   private double[] resolveDoubleArray(double[] offsetArray, int count, int count2, int count3, int count4, int count5, int count6) {
      double[] offsetArray2 = offsetArray;
      if (offsetArray == null) {
         offsetArray2 = new double[count4 * count5 * count6];
      }

      double offset = 684.412;
      double offset2 = 684.412;
      this.valueArray5 = this.clientManagerService4.resolveDoubleArray(this.valueArray5, count, count3, count4, count6, 1.121, 1.121, 0.5);
      this.valueArray6 = this.clientManagerService5.resolveDoubleArray(this.valueArray6, count, count3, count4, count6, 200.0, 200.0, 0.5);
      double offset3 = offset * 2.0;
      this.valueArray2 = this.clientManagerService3
         .resolveDoubleArray(this.valueArray2, count, count2, count3, count4, count5, count6, offset3 / 80.0, offset2 / 160.0, offset3 / 80.0);
      this.valueArray3 = this.clientManagerService.resolveDoubleArray(this.valueArray3, count, count2, count3, count4, count5, count6, offset3, offset2, offset3);
      this.valueArray4 = this.clientManagerService2.resolveDoubleArray(this.valueArray4, count, count2, count3, count4, count5, count6, offset3, offset2, offset3);
      int count7 = 0;

      for (int index = 0; index < count4; index++) {
         for (int index2 = 0; index2 < count6; index2++) {
            float factor = (float)(index + count) / 1.0F;
            float factor2 = (float)(index2 + count3) / 1.0F;
            float factor3 = 100.0F - MathHelper.sin(factor * factor + factor2 * factor2) * 8.0F;
            if (factor3 > 80.0F) {
               factor3 = 80.0F;
            }

            if (factor3 < -100.0F) {
               factor3 = -100.0F;
            }

            for (int index3 = 0; index3 < count5; index3++) {
               double offset4 = this.valueArray3[count7] / 512.0;
               double offset5 = this.valueArray4[count7] / 512.0;
               double offset6 = (this.valueArray2[count7] / 10.0 + 1.0) / 2.0;
               if (!(offset6 < 0.0)) {
                  if (offset6 > 1.0) {
                     offset4 = offset5;
                  } else {
                     offset4 += (offset5 - offset4) * offset6;
                  }
               }

               double offset7 = offset4 - 8.0 + (double)factor3;
               byte byteValue = 2;
               if (index3 > count5 / 2 - 2) {
                  double doubleItem = MathHelper.getDouble7((double)((float)(index3 - (count5 / 2 - byteValue)) / 64.0F), 0.0, 1.0);
                  offset7 = offset7 * (1.0 - doubleItem) + -3000.0 * doubleItem;
               }

               byte byteValue2 = 8;
               if (index3 < 8) {
                  double offset8 = (double)((float)(byteValue2 - index3) / ((float)byteValue2 - 1.0F));
                  offset7 = offset7 * (1.0 - offset8) + -30.0 * offset8;
               }

               offsetArray2[count7] = offset7;
               count7++;
            }
         }
      }

      return offsetArray2;
   }
}
