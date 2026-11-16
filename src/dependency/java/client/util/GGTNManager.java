package client.util;

import client.setting.CombatAlphaProperty;
import client.setting.LocalOptionType;
import client.setting.MovementOffsetProperty;
import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStainedHardenedClay;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GGTNManager extends BiomeIDIsOutOfBoundsManager {
   public IBlockState[] iBlockStateArray;
   public long timestamp;
   public AuxiliaryClientManagerService auxiliaryClientManagerService5;
   public AuxiliaryClientManagerService auxiliaryClientManagerService4;
   public AuxiliaryClientManagerService auxiliaryClientManagerService3;
   public boolean enabled4;
   public boolean enabled3;

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      super.applyWorld(world, random, blockPos);
   }

   @Override
   public int computeCount(BlockPos blockPos) {
      return 10387789;
   }

   @Override
   public void applyWorld(World world, Random random, ShortArrayEntry shortArrayEntry, int count, int count2, double offset) {
      if (this.iBlockStateArray == null || this.timestamp != world.getTimestamp()) {
         this.applyTime(world.getTimestamp());
      }

      if (this.auxiliaryClientManagerService5 == null || this.auxiliaryClientManagerService4 == null || this.timestamp != world.getTimestamp()) {
         Random random2 = new Random(this.timestamp);
         this.auxiliaryClientManagerService5 = new AuxiliaryClientManagerService(random2, 4);
         this.auxiliaryClientManagerService4 = new AuxiliaryClientManagerService(random2, 1);
      }

      this.timestamp = world.getTimestamp();
      double offset2 = 0.0;
      if (this.enabled4) {
         int count3 = (count & -16) + (count2 & 15);
         int count4 = (count2 & -16) + (count & 15);
         double offset3 = Math.min(Math.abs(offset), this.auxiliaryClientManagerService5.computeValue((double)count3 * 0.25, (double)count4 * 0.25));
         if (offset3 > 0.0) {
            double offset4 = 0.001953125;
            double offset5 = Math.abs(this.auxiliaryClientManagerService4.computeValue((double)count3 * offset4, (double)count4 * offset4));
            double offset6 = offset3 * offset3 * 2.5;
            double offset7 = Math.ceil(offset5 * 50.0) + 14.0;
            if (offset6 > offset7) {
               offset6 = offset7;
            }

            offset2 = offset6 + 64.0;
         }
      }

      int count5 = count & 15;
      int count6 = count2 & 15;
      int indexOffset = world.getIndexOffset1();
      IBlockState defaultState = Blocks.block107.getDefaultState();
      IBlockState iBlockState = this.iBlockState2;
      int count7 = (int)(offset / 3.0 + 3.0 + random.nextDouble() * 0.25);
      boolean flag = Math.cos(offset / 3.0 * Math.PI) > 0.0;
      int count8 = -1;
      boolean flag2 = false;

      for (int index = 255; index >= 0; index--) {
         if (shortArrayEntry.resolveIBlockState(count6, index, count5).getBlock().getMaterial() == Material.coreItemEntry && index < (int)offset2) {
            shortArrayEntry.applyIBlockState(count6, index, count5, Blocks.stone.getDefaultState());
         }

         if (index <= random.nextInt(5)) {
            shortArrayEntry.applyIBlockState(count6, index, count5, Blocks.block95.getDefaultState());
         } else {
            IBlockState blockState = shortArrayEntry.resolveIBlockState(count6, index, count5);
            if (blockState.getBlock().getMaterial() == Material.coreItemEntry) {
               count8 = -1;
            } else if (blockState.getBlock() == Blocks.stone) {
               if (count8 == -1) {
                  flag2 = false;
                  if (count7 <= 0) {
                     defaultState = null;
                     iBlockState = Blocks.stone.getDefaultState();
                  } else if (index >= indexOffset - 4 && index <= indexOffset + 1) {
                     defaultState = Blocks.block107.getDefaultState();
                     iBlockState = this.iBlockState2;
                  }

                  if (index < indexOffset && (defaultState == null || defaultState.getBlock().getMaterial() == Material.coreItemEntry)) {
                     defaultState = Blocks.blockLava2.getDefaultState();
                  }

                  count8 = count7 + Math.max(0, index - indexOffset);
                  if (index < indexOffset - 1) {
                     shortArrayEntry.applyIBlockState(count6, index, count5, iBlockState);
                     if (iBlockState.getBlock() == Blocks.block107) {
                        shortArrayEntry.applyIBlockState(
                           count6,
                           index,
                           count5,
                           iBlockState.getBlock().getDefaultState().resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.ORANGE)
                        );
                     }
                  } else if (!this.enabled3 || index <= 86 + count7 * 2) {
                     if (index <= indexOffset + 3 + count7) {
                        shortArrayEntry.applyIBlockState(count6, index, count5, this.iBlockState);
                        flag2 = true;
                     } else {
                        IBlockState blockState2;
                        if (index < 64 || index > 127) {
                           blockState2 = Blocks.block107.getDefaultState().resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.ORANGE);
                        } else if (flag) {
                           blockState2 = Blocks.block98.getDefaultState();
                        } else {
                           blockState2 = this.resolveIBlockState(count, index, count2);
                        }

                        shortArrayEntry.applyIBlockState(count6, index, count5, blockState2);
                     }
                  } else if (flag) {
                     shortArrayEntry.applyIBlockState(
                        count6,
                        index,
                        count5,
                        Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.COARSE_DIRT)
                     );
                  } else {
                     shortArrayEntry.applyIBlockState(count6, index, count5, Blocks.blockGrass.getDefaultState());
                  }
               } else if (count8 > 0) {
                  count8--;
                  if (flag2) {
                     shortArrayEntry.applyIBlockState(
                        count6,
                        index,
                        count5,
                        Blocks.block107.getDefaultState().resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.ORANGE)
                     );
                  } else {
                     IBlockState blockState3 = this.resolveIBlockState(count, index, count2);
                     shortArrayEntry.applyIBlockState(count6, index, count5, blockState3);
                  }
               }
            }
         }
      }
   }

   private IBlockState resolveIBlockState(int count, int count2, int count3) {
      int count4 = (int)Math.round(this.auxiliaryClientManagerService3.computeValue((double)count * 1.0 / 512.0, (double)count * 1.0 / 512.0) * 2.0);
      return this.iBlockStateArray[(count2 + count4 + 64) % 64];
   }

   @Override
   public int computeCountByBlockPos(BlockPos blockPos) {
      return 9470285;
   }

   private void applyTime(long value) {
      this.iBlockStateArray = new IBlockState[64];
      Arrays.fill(this.iBlockStateArray, Blocks.block98.getDefaultState());
      Random random = new Random(value);
      this.auxiliaryClientManagerService3 = new AuxiliaryClientManagerService(random, 1);

      for (int index = 0; index < 64; index++) {
         index += random.nextInt(5) + 1;
         if (index < 64) {
            this.iBlockStateArray[index] = Blocks.block107
               .getDefaultState()
               .resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.ORANGE);
         }
      }

      int count = random.nextInt(4) + 2;

      for (int index2 = 0; index2 < count; index2++) {
         int count2 = random.nextInt(3) + 1;
         int count3 = random.nextInt(64);

         for (int index3 = 0; count3 + index3 < 64 && index3 < count2; index3++) {
            this.iBlockStateArray[count3 + index3] = Blocks.block107
               .getDefaultState()
               .resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.YELLOW);
         }
      }

      int count4 = random.nextInt(4) + 2;

      for (int index4 = 0; index4 < count4; index4++) {
         int count5 = random.nextInt(3) + 2;
         int count6 = random.nextInt(64);

         for (int index5 = 0; count6 + index5 < 64 && index5 < count5; index5++) {
            this.iBlockStateArray[count6 + index5] = Blocks.block107
               .getDefaultState()
               .resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.BROWN);
         }
      }

      int count7 = random.nextInt(4) + 2;

      for (int index6 = 0; index6 < count7; index6++) {
         int count8 = random.nextInt(3) + 1;
         int count9 = random.nextInt(64);

         for (int index7 = 0; count9 + index7 < 64 && index7 < count8; index7++) {
            this.iBlockStateArray[count9 + index7] = Blocks.block107
               .getDefaultState()
               .resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.RED);
         }
      }

      int count10 = random.nextInt(3) + 3;
      int count11 = 0;

      for (int index8 = 0; index8 < count10; index8++) {
         byte byteValue = 1;
         count11 += random.nextInt(16) + 4;

         for (int index9 = 0; count11 + index9 < 64 && index9 < byteValue; index9++) {
            this.iBlockStateArray[count11 + index9] = Blocks.block107
               .getDefaultState()
               .resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.WHITE);
            if (count11 + index9 > 1 && random.nextBoolean()) {
               this.iBlockStateArray[count11 + index9 - 1] = Blocks.block107
                  .getDefaultState()
                  .resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.SILVER);
            }

            if (count11 + index9 < 63 && random.nextBoolean()) {
               this.iBlockStateArray[count11 + index9 + 1] = Blocks.block107
                  .getDefaultState()
                  .resolveIBlockState(BlockStainedHardenedClay.immutableSetEntry, LocalOptionType.SILVER);
            }
         }
      }
   }

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return this.secondaryIBlockStateEntry;
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManagerByIndex(int count) {
      boolean flag = this.count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager5.count;
      GGTNManager gGTNManager = new GGTNManager(count, flag, this.enabled3);
      if (!flag) {
         gGTNManager.resolveBiomeIDIsOutOfBoundsManager(coreFloatEntry5);
         gGTNManager.withLabelText(this.labelText + " M");
      } else {
         gGTNManager.withLabelText(this.labelText + " (Bryce)");
      }

      gGTNManager.resolveBiomeIDIsOutOfBoundsManager(this.count3, true);
      return gGTNManager;
   }

   public GGTNManager(int count, boolean flag, boolean flag2) {
      super(count);
      this.enabled4 = flag;
      this.enabled3 = flag2;
      this.getBiomeIDIsOutOfBoundsManager();
      this.resolveBiomeIDIsOutOfBoundsManager(2.0F, 0.0F);
      this.list4.clear();
      this.iBlockState = Blocks.blockSand.getDefaultState().resolveIBlockState(BlockSand.immutableSetEntry, CombatAlphaProperty.RED_SAND);
      this.iBlockState2 = Blocks.block107.getDefaultState();
      this.alreadyDecoratingManager.count9 = -999;
      this.alreadyDecoratingManager.count = 20;
      this.alreadyDecoratingManager.count4 = 3;
      this.alreadyDecoratingManager.count11 = 5;
      this.alreadyDecoratingManager.count8 = 0;
      this.list4.clear();
      if (flag2) {
         this.alreadyDecoratingManager.count9 = 5;
      }
   }
}
