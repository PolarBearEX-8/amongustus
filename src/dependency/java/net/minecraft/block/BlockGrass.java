package net.minecraft.block;

import client.event.PreMotionFilter;
import client.setting.BlockSolidType;
import client.setting.CombatFactorProperty;
import client.setting.MovementColorProperty;
import client.setting.MovementOffsetProperty;
import client.util.DefaultIntArrayEntry;
import client.util.IBlockState;
import client.util.ImmutableSetEntry2Entry;
import client.util.InternalImmutableSetEntry;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.GoldenShovel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGrass extends Block implements PreMotionFilter {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("snowy");

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state) {
         if (world.computeCountByIndex(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()) < 4
            && world.getIBlockState(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()).getBlock().getIndexOffset() > 2) {
            world.matchesBlockPos(blockPos, Blocks.block115.getDefaultState());
         } else if (world.computeCountByIndex(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()) >= 9) {
            for (int index = 0; index < 4; index++) {
               int count = blockPos.getX() + random.nextInt(3) - 1;
               int count2 = blockPos.getY() + random.nextInt(5) - 3;
               int count3 = blockPos.getZ() + random.nextInt(3) - 1;
               IBlockState iBlockState = world.getIBlockState(count, count2, count3);
               if (iBlockState.getBlock() == Blocks.block115
                  && iBlockState.getComparable(BlockDirt.immutableSetEntry) == MovementOffsetProperty.DIRT
                  && world.computeCountByIndex(count, count2 + 1, count3) >= 4
                  && world.getIBlockState(count, count2 + 1, count3).getBlock().getIndexOffset() <= 2) {
                  world.matchesBlockPos(new BlockPos(count, count2, count3), Blocks.blockGrass.getDefaultState());
               }
            }
         }
      }
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT_MIPPED;
   }

   @Override
   public int processAction2(IBlockState blockState) {
      return this.processAction6();
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      Block block = iBlockAccess.getIBlockState(count, count2 + 1, count3).getBlock();
      return blockState.resolveIBlockState(internalImmutableSetEntry, block == Blocks.block61 || block == Blocks.block74);
   }

   @Override
   public int processAction6() {
      return DefaultIntArrayEntry.computeCount(0.5, 1.0);
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      BlockPos blockPos2 = blockPos.up();

      outerLoop:
      for (int index = 0; index < 128; index++) {
         BlockPos blockPos3 = blockPos2;

         for (int index2 = 0; index2 < index / 16; index2++) {
            blockPos3 = blockPos3.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
            if (world.getBlockState(blockPos3.down()).getBlock() != Blocks.blockGrass || world.getBlockState(blockPos3).getBlock().isActive2()) {
               continue outerLoop;
            }
         }

         if (world.getBlockState(blockPos3).getBlock().coreItemEntry == Material.coreItemEntry) {
            if (random.nextInt(8) == 0) {
               CombatFactorProperty combatFactorProperty = world.resolveBiomeIDIsOutOfBoundsManager(blockPos3).nextInt(random, blockPos3);
               ImmutableSetEntry2Entry immutableSetEntry2Entry = combatFactorProperty.getCombatProfileProperty().getImmutableSetEntry2Entry();
               IBlockState blockState2 = immutableSetEntry2Entry.getDefaultState().resolveIBlockState(immutableSetEntry2Entry.getIProperty(), combatFactorProperty);
               if (immutableSetEntry2Entry.matchesWorld(world, blockPos3, blockState2)) {
                  world.matchesBlockPos(blockPos3, blockState2, 3);
               }
            } else {
               IBlockState blockState3 = Blocks.blockTallgrass.getDefaultState().resolveIBlockState(BlockTallgrass.immutableSetEntry, MovementColorProperty.GRASS);
               if (Blocks.blockTallgrass.matchesWorld(world, blockPos3, blockState3)) {
                  world.matchesBlockPos(blockPos3, blockState3, 3);
               }
            }
         }
      }
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag) {
      return true;
   }

   @Override
   public boolean nextFloat(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      return true;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Blocks.block115
         .processAction(Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.DIRT), random, count);
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      return GoldenShovel.computeCount(iBlockAccess, blockPos);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return 0;
   }

   public BlockGrass() {
      super(Material.material30);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE));
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
