package client.util;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BlockSolidType;
import client.setting.MovementProperty;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StandardBlockAdapter;
import net.minecraft.block.material.Material;
import net.minecraft.item.GoldenShovel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class DecayableManager extends StandardBlockAdapter {
   public static final InternalImmutableSetEntry internalImmutableSetEntry2;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;
   public int[] countArray;
   public int count;
   public boolean enabled3;

   @Override
   public int processAction2(IBlockState blockState) {
      return CoreIntArrayEntry.getCount();
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state && (Boolean)blockState.getComparable(internalImmutableSetEntry) && (Boolean)blockState.getComparable(internalImmutableSetEntry2)) {
         int coordX = blockPos.getX();
         int coordY = blockPos.getY();
         int coordZ = blockPos.getZ();
         int count2 = 32 / 2;
         if (this.countArray == null) {
            this.countArray = new int[32768];
         }

         if (world.matchesBlockPos(new BlockPos(coordX - 5, coordY - 5, coordZ - 5), new BlockPos(coordX + 5, coordY + 5, coordZ + 5))) {
            AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

            for (int index = -4; index <= 4; index++) {
               for (int index2 = -4; index2 <= 4; index2++) {
                  for (int index3 = -4; index3 <= 4; index3++) {
                     Block block = world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(coordX + index, coordY + index2, coordZ + index3)).getBlock();
                     if (block != Blocks.block140 && block != Blocks.block104) {
                        if (block.getMaterial() == Material.material9) {
                           this.countArray[(index + count2) * 1024 + (index2 + count2) * 32 + index3 + count2] = -2;
                        } else {
                           this.countArray[(index + count2) * 1024 + (index2 + count2) * 32 + index3 + count2] = -1;
                        }
                     } else {
                        this.countArray[(index + count2) * 1024 + (index2 + count2) * 32 + index3 + count2] = 0;
                     }
                  }
               }
            }

            for (int index4 = 1; index4 <= 4; index4++) {
               for (int index5 = -4; index5 <= 4; index5++) {
                  for (int index6 = -4; index6 <= 4; index6++) {
                     for (int index7 = -4; index7 <= 4; index7++) {
                        if (this.countArray[(index5 + count2) * 1024 + (index6 + count2) * 32 + index7 + count2] == index4 - 1) {
                           if (this.countArray[(index5 + count2 - 1) * 1024 + (index6 + count2) * 32 + index7 + count2] == -2) {
                              this.countArray[(index5 + count2 - 1) * 1024 + (index6 + count2) * 32 + index7 + count2] = index4;
                           }

                           if (this.countArray[(index5 + count2 + 1) * 1024 + (index6 + count2) * 32 + index7 + count2] == -2) {
                              this.countArray[(index5 + count2 + 1) * 1024 + (index6 + count2) * 32 + index7 + count2] = index4;
                           }

                           if (this.countArray[(index5 + count2) * 1024 + (index6 + count2 - 1) * 32 + index7 + count2] == -2) {
                              this.countArray[(index5 + count2) * 1024 + (index6 + count2 - 1) * 32 + index7 + count2] = index4;
                           }

                           if (this.countArray[(index5 + count2) * 1024 + (index6 + count2 + 1) * 32 + index7 + count2] == -2) {
                              this.countArray[(index5 + count2) * 1024 + (index6 + count2 + 1) * 32 + index7 + count2] = index4;
                           }

                           if (this.countArray[(index5 + count2) * 1024 + (index6 + count2) * 32 + (index7 + count2 - 1)] == -2) {
                              this.countArray[(index5 + count2) * 1024 + (index6 + count2) * 32 + (index7 + count2 - 1)] = index4;
                           }

                           if (this.countArray[(index5 + count2) * 1024 + (index6 + count2) * 32 + index7 + count2 + 1] == -2) {
                              this.countArray[(index5 + count2) * 1024 + (index6 + count2) * 32 + index7 + count2 + 1] = index4;
                           }
                        }
                     }
                  }
               }
            }
         }

         if (this.countArray[count2 * 1024 + count2 * 32 + count2] >= 0) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE), 4);
         } else {
            this.applyWorld2(world, blockPos);
         }
      }
   }

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count2) {
      if (!world.state) {
         int count3 = this.computeCount(blockState);
         if (count2 > 0) {
            count3 -= 2 << count2;
            if (count3 < 10) {
               count3 = 10;
            }
         }

         if (world.random.nextInt(count3) == 0) {
            Item item = this.processAction(blockState, world.random, count2);
            applyWorld(world, blockPos, new ItemStack(item, 1, this.getMetaFromState(blockState)));
         }

         int count4 = 200;
         if (count2 > 0) {
            count4 -= 10 << count2;
            if (count4 < 40) {
               count4 = 40;
            }
         }

         this.applyWorldByWorld(world, blockPos, blockState, count4);
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return this.enabled3 ? BlockSolidType.CUTOUT_MIPPED : BlockSolidType.SOLID;
   }

   public void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, int count2) {
   }

   @Override
   public int processAction6() {
      return CoreIntArrayEntry.computeCount(0.5, 1.0);
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();
      if (world.matchesBlockPos(new BlockPos(coordX - 2, coordY - 2, coordZ - 2), new BlockPos(coordX + 2, coordY + 2, coordZ + 2))) {
         for (int index = -1; index <= 1; index++) {
            for (int index2 = -1; index2 <= 1; index2++) {
               for (int index3 = -1; index3 <= 1; index3++) {
                  BlockPos blockPos2 = blockPos.add(index, index2, index3);
                  IBlockState blockState2 = world.getBlockState(blockPos2);
                  if (blockState2.getBlock().getMaterial() == Material.material9 && !(Boolean)blockState2.getComparable(internalImmutableSetEntry)) {
                     world.matchesBlockPos(blockPos2, blockState2.resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE), 4);
                  }
               }
            }
         }
      }
   }

   public int computeCount(IBlockState blockState) {
      return 20;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count2) {
      return GoldenShovel.computeCount2(iBlockAccess, blockPos);
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count2) {
      return Item.processAction2(Blocks.block83);
   }

   @Override
   public int processAction(Random random) {
      return random.nextInt(20) == 0 ? 1 : 0;
   }

   public abstract MovementProperty resolveMovementProperty(int count2);

   public void setEnabled(boolean enabled) {
      this.enabled3 = enabled;
      this.enabled = enabled;
      this.count = enabled ? 0 : 1;
   }

   private void applyWorld2(World world, BlockPos blockPos) {
      this.processAction(world, blockPos, world.getBlockState(blockPos), 0);
      world.matchesBlockPos8(blockPos);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (world.matchesBlockPos(blockPos.up()) && !World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()) && random.nextInt(15) == 1) {
         double offset = (double)((float)blockPos.getX() + random.nextFloat());
         double offset2 = (double)blockPos.getY() - 0.05;
         double offset3 = (double)((float)blockPos.getZ() + random.nextFloat());
         world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.DRIP_WATER, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
      }
   }

   @Override
   public boolean isActive3() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return !this.enabled;
   }

   public DecayableManager() {
      super(Material.material9, false);
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager8);
      this.getBlock5(0.2F);
      this.getBlockById(1);
      this.processAction(stepManager26);
   }

   static {
      String[] textArray = new String[]{"decayable", "check_decay"};
      internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[0]);
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[1]);
   }
}
