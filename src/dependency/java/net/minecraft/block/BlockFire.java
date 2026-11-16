package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.BlockSolidType;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.EndManager;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFire extends Block {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("age", 0, 15);
   public static final InternalImmutableSetEntry internalImmutableSetEntry3 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("flip");
   public static final InternalImmutableSetEntry internalImmutableSetEntry2 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("alt");
   public static final InternalImmutableSetEntry internalImmutableSetEntry6 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("north");
   public static final InternalImmutableSetEntry internalImmutableSetEntry4 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("east");
   public static final InternalImmutableSetEntry internalImmutableSetEntry5 = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("south");
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("west");
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry2 = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("upper", 0, 2);
   public final Map map2 = Maps.newIdentityHashMap();
   public final Map map = Maps.newIdentityHashMap();

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      IBlockState blockState2 = blockState;
      if (world.getDoFireTickManager().matchesText("doFireTick")) {
         if (!this.processAction7(world, blockPos)) {
            world.matchesBlockPos8(blockPos);
         }

         Block block = world.getBlockState(blockPos.down()).getBlock();
         boolean flag = block == Blocks.block96;
         if (world.secondaryFloatArrayEntry instanceof EndManager && block == Blocks.block95) {
            flag = true;
         }

         if (!flag && world.isActive() && this.matchesWorld5(world, blockPos)) {
            world.matchesBlockPos8(blockPos);
         } else {
            int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
            if (comparable < 15) {
               blockState2 = blockState.resolveIBlockState(auxiliaryImmutableSetEntry, comparable + random.nextInt(3) / 2);
               world.matchesBlockPos(blockPos, blockState2, 4);
            }

            world.applyBlockPos(blockPos, this, this.processAction(world) + random.nextInt(10));
            if (!flag) {
               if (!this.matchesWorld3(world, blockPos)) {
                  if (!World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()) || comparable > 3) {
                     world.matchesBlockPos8(blockPos);
                  }

                  return;
               }

               if (!this.matchesIBlockAccessByIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()) && comparable == 15 && random.nextInt(4) == 0) {
                  world.matchesBlockPos8(blockPos);
                  return;
               }
            }

            boolean flag2 = world.matchesBlockPos6(blockPos);
            byte byteValue = 0;
            if (flag2) {
               byteValue = -50;
            }

            this.applyWorldByWorld(world, blockPos.getBlockPos3(), 300 + byteValue, random, comparable);
            this.applyWorldByWorld(world, blockPos.getBlockPos(), 300 + byteValue, random, comparable);
            this.applyWorldByWorld(world, blockPos.down(), 250 + byteValue, random, comparable);
            this.applyWorldByWorld(world, blockPos.up(), 250 + byteValue, random, comparable);
            this.applyWorldByWorld(world, blockPos.getBlockPosAsBlockPos(), 300 + byteValue, random, comparable);
            this.applyWorldByWorld(world, blockPos.getBlockPos2(), 300 + byteValue, random, comparable);

            for (int index = -1; index <= 1; index++) {
               for (int index2 = -1; index2 <= 1; index2++) {
                  for (int index3 = -1; index3 <= 4; index3++) {
                     if (index != 0 || index3 != 0 || index2 != 0) {
                        int count = 100;
                        if (index3 > 1) {
                           count += (index3 - 1) * 100;
                        }

                        BlockPos blockPos2 = blockPos.add(index, index3, index2);
                        int count2 = this.computeCount2(world, blockPos2);
                        if (count2 > 0) {
                           int count3 = (count2 + 40 + world.getGameSettingsPeacefulType().getIndex() * 7) / (comparable + 30);
                           if (flag2) {
                              count3 /= 2;
                           }

                           if (count3 > 0 && random.nextInt(count) <= count3 && (!world.isActive() || !this.matchesWorld5(world, blockPos2))) {
                              int count4 = comparable + random.nextInt(5) / 4;
                              if (count4 > 15) {
                                 count4 = 15;
                              }

                              world.matchesBlockPos(blockPos2, blockState2.resolveIBlockState(auxiliaryImmutableSetEntry, count4), 3);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   private void applyWorldByWorld(World world, BlockPos blockPos, int count, Random random, int count2) {
      int count3 = this.computeCount(world.getBlockState(blockPos).getBlock());
      if (random.nextInt(count) < count3) {
         IBlockState blockState = world.getBlockState(blockPos);
         if (random.nextInt(count2 + 10) < 5 && !world.matchesBlockPos(blockPos)) {
            int count4 = count2 + random.nextInt(5) / 4;
            if (count4 > 15) {
               count4 = 15;
            }

            world.matchesBlockPos(blockPos, this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count4), 3);
         } else {
            world.matchesBlockPos8(blockPos);
         }

         if (blockState.getBlock() == Blocks.block135) {
            Blocks.block135.breakBlock(world, blockPos, blockState.resolveIBlockState(BlockTnt.internalImmutableSetEntry, Boolean.TRUE));
         }
      }
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      if (!World.matchesIBlockAccess(iBlockAccess, count, count2 - 1, count3) && !Blocks.blockFire.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2 - 1, count3)) {
         boolean flag = (count + count + count & 1) == 1;
         boolean flag2 = (count / 2 + count / 2 + count / 2 & 1) == 1;
         int count4 = 0;
         if (this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2 + 1, count3)) {
            count4 = flag ? 1 : 2;
         }

         return blockState.resolveIBlockState(internalImmutableSetEntry6, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 - 1))
            .resolveIBlockState(internalImmutableSetEntry4, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count + 1, count2, count3))
            .resolveIBlockState(internalImmutableSetEntry5, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count, count2, count3 + 1))
            .resolveIBlockState(internalImmutableSetEntry, this.matchesIBlockAccessByIBlockAccess(iBlockAccess, count - 1, count2, count3))
            .resolveIBlockState(auxiliaryImmutableSetEntry2, count4)
            .resolveIBlockState(internalImmutableSetEntry3, flag2)
            .resolveIBlockState(internalImmutableSetEntry2, flag);
      } else {
         return this.getDefaultState();
      }
   }

   @Override
   public boolean processAction5() {
      return false;
   }

   public boolean matchesIBlockAccessByIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return this.computeCountByBlock(iBlockAccess.getIBlockState(count, count2, count3).getBlock()) > 0;
   }

   public boolean matchesWorld5(World world, BlockPos blockPos) {
      return world.matchesBlockPos(blockPos)
         || world.matchesBlockPos(blockPos.getBlockPos())
         || world.matchesBlockPos(blockPos.getBlockPos3())
         || world.matchesBlockPos(blockPos.getBlockPosAsBlockPos())
         || world.matchesBlockPos(blockPos.getBlockPos2());
   }

   @Override
   public int processAction(World world) {
      return 30;
   }

   private boolean matchesWorld3(World world, BlockPos blockPos) {
      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         if (this.matchesIBlockAccessByIBlockAccess(world, blockPos.getX() + enumFacing.getInt2(), blockPos.getY() + enumFacing.getInt3(), blockPos.getZ() + enumFacing.getInt())) {
            return true;
         }
      }

      return false;
   }

   public void applyBlock(Block block, int count, int count2) {
      this.map2.put(block, count);
      this.map.put(block, count2);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return MapColor.mapColor8;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()) && !this.matchesWorld3(world, blockPos)) {
         world.matchesBlockPos8(blockPos);
      }
   }

   private int computeCount2(World world, BlockPos blockPos) {
      if (!world.isAirBlock(blockPos)) {
         return 0;
      } else {
         int count = 0;

         for (EnumFacing enumFacing : EnumFacing.getEntries()) {
            count = Math.max(this.computeCountByBlock(world.getBlockState(blockPos.offset(enumFacing)).getBlock()), count);
         }

         return count;
      }
   }

   public static void updateState2() {
      Blocks.blockFire.applyBlock(Blocks.block142, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.extraBlockAdapter, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.extraBlockAdapter2, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block117, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block43, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block73, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block31, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block101, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block116, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block111, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block38, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block27, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block87, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block147, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block97, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block3, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block118, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block85, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block18, 5, 20);
      Blocks.blockFire.applyBlock(Blocks.block140, 5, 5);
      Blocks.blockFire.applyBlock(Blocks.block104, 5, 5);
      Blocks.blockFire.applyBlock(Blocks.decayableManager2, 30, 60);
      Blocks.blockFire.applyBlock(Blocks.decayableManager, 30, 60);
      Blocks.blockFire.applyBlock(Blocks.block8, 30, 20);
      Blocks.blockFire.applyBlock(Blocks.block135, 15, 100);
      Blocks.blockFire.applyBlock(Blocks.blockTallgrass, 60, 100);
      Blocks.blockFire.applyBlock(Blocks.blockDoublePlant, 60, 100);
      Blocks.blockFire.applyBlock(Blocks.immutableSetEntry2Entry, 60, 100);
      Blocks.blockFire.applyBlock(Blocks.immutableSetEntry2Entry2, 60, 100);
      Blocks.blockFire.applyBlock(Blocks.blockDeadbush, 60, 100);
      Blocks.blockFire.applyBlock(Blocks.wool, 30, 60);
      Blocks.blockFire.applyBlock(Blocks.block144, 15, 100);
      Blocks.blockFire.applyBlock(Blocks.block9, 5, 5);
      Blocks.blockFire.applyBlock(Blocks.block137, 60, 20);
      Blocks.blockFire.applyBlock(Blocks.block141, 60, 20);
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()) || this.matchesWorld3(world, blockPos);
   }

   @Override
   public boolean processAction10() {
      return false;
   }

   private int computeCountByBlock(Block block) {
      Integer count = (Integer)this.map2.get(block);
      return count == null ? 0 : count;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      if (world.secondaryFloatArrayEntry.getCountAsIndex() > 0 || !Blocks.blockPortal.matchesWorld3(world, blockPos)) {
         if (!World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()) && !this.matchesWorld3(world, blockPos)) {
            world.matchesBlockPos8(blockPos);
         } else {
            world.applyBlockPos(blockPos, this, this.processAction(world) + world.random.nextInt(10));
         }
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(
         this,
         new IProperty[]{
            auxiliaryImmutableSetEntry,
            internalImmutableSetEntry6,
            internalImmutableSetEntry4,
            internalImmutableSetEntry5,
            internalImmutableSetEntry,
            auxiliaryImmutableSetEntry2,
            internalImmutableSetEntry3,
            internalImmutableSetEntry2
         }
      );
   }

   private int computeCount(Block block) {
      Integer count = (Integer)this.map.get(block);
      return count == null ? 0 : count;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (random.nextInt(24) == 0) {
         world.applyText(
            (double)((float)blockPos.getX() + 0.5F),
            (double)((float)blockPos.getY() + 0.5F),
            (double)((float)blockPos.getZ() + 0.5F),
            "fire.fire",
            1.0F + random.nextFloat(),
            random.nextFloat() * 0.7F + 0.3F,
            false
         );
      }

      if (!World.matchesIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())
         && !Blocks.blockFire.matchesIBlockAccessByIBlockAccess(world, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())) {
         if (Blocks.blockFire.matchesIBlockAccessByIBlockAccess(world, blockPos.getX() - 1, blockPos.getY(), blockPos.getZ())) {
            for (int index = 0; index < 2; index++) {
               double offset = (double)blockPos.getX() + random.nextDouble() * 0.1F;
               double offset2 = (double)blockPos.getY() + random.nextDouble();
               double offset3 = (double)blockPos.getZ() + random.nextDouble();
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_LARGE, offset, offset2, offset3, 0.0, 0.0, 0.0, new int[0]);
            }
         }

         if (Blocks.blockFire.matchesIBlockAccessByIBlockAccess(world, blockPos.getX() + 1, blockPos.getY(), blockPos.getZ())) {
            for (int index2 = 0; index2 < 2; index2++) {
               double offset4 = (double)(blockPos.getX() + 1) - random.nextDouble() * 0.1F;
               double offset5 = (double)blockPos.getY() + random.nextDouble();
               double offset6 = (double)blockPos.getZ() + random.nextDouble();
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_LARGE, offset4, offset5, offset6, 0.0, 0.0, 0.0, new int[0]);
            }
         }

         if (Blocks.blockFire.matchesIBlockAccessByIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ() - 1)) {
            for (int index3 = 0; index3 < 2; index3++) {
               double offset7 = (double)blockPos.getX() + random.nextDouble();
               double offset8 = (double)blockPos.getY() + random.nextDouble();
               double offset9 = (double)blockPos.getZ() + random.nextDouble() * 0.1F;
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_LARGE, offset7, offset8, offset9, 0.0, 0.0, 0.0, new int[0]);
            }
         }

         if (Blocks.blockFire.matchesIBlockAccessByIBlockAccess(world, blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1)) {
            for (int index4 = 0; index4 < 2; index4++) {
               double offset10 = (double)blockPos.getX() + random.nextDouble();
               double offset11 = (double)blockPos.getY() + random.nextDouble();
               double offset12 = (double)(blockPos.getZ() + 1) - random.nextDouble() * 0.1F;
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_LARGE, offset10, offset11, offset12, 0.0, 0.0, 0.0, new int[0]);
            }
         }

         if (Blocks.blockFire.matchesIBlockAccessByIBlockAccess(world, blockPos.getX(), blockPos.getY() + 1, blockPos.getZ())) {
            for (int index5 = 0; index5 < 2; index5++) {
               double offset13 = (double)blockPos.getX() + random.nextDouble();
               double offset14 = (double)(blockPos.getY() + 1) - random.nextDouble() * 0.1F;
               double offset15 = (double)blockPos.getZ() + random.nextDouble();
               world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_LARGE, offset13, offset14, offset15, 0.0, 0.0, 0.0, new int[0]);
            }
         }
      } else {
         for (int index6 = 0; index6 < 3; index6++) {
            double offset16 = (double)blockPos.getX() + random.nextDouble();
            double offset17 = (double)blockPos.getY() + random.nextDouble() * 0.5 + 0.5;
            double offset18 = (double)blockPos.getZ() + random.nextDouble();
            world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_LARGE, offset16, offset17, offset18, 0.0, 0.0, 0.0, new int[0]);
         }
      }
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public BlockFire() {
      super(Material.material6);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(auxiliaryImmutableSetEntry, 0)
            .resolveIBlockState(internalImmutableSetEntry3, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry2, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry6, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry4, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry5, Boolean.FALSE)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
            .resolveIBlockState(auxiliaryImmutableSetEntry2, 0)
      );
      this.processAction(true);
   }
}
