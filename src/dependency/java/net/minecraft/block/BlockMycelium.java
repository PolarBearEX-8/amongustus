package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.setting.MovementOffsetProperty;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMycelium extends Block {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("snowy");

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (!world.state) {
         if (world.computeCountByIndex(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()) < 4 && world.getBlockState(blockPos.up()).getBlock().getIndexOffset() > 2) {
            world.matchesBlockPos(blockPos, Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.DIRT));
         } else if (world.computeCountByIndex(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()) >= 9) {
            for (int index = 0; index < 4; index++) {
               BlockPos blockPos2 = blockPos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
               IBlockState blockState2 = world.getBlockState(blockPos2);
               Block block = world.getBlockState(blockPos2.up()).getBlock();
               if (blockState2.getBlock() == Blocks.block115
                  && blockState2.getComparable(BlockDirt.immutableSetEntry) == MovementOffsetProperty.DIRT
                  && world.computeCountByIndex(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ()) >= 4
                  && block.getIndexOffset() <= 2) {
                  world.matchesBlockPos(blockPos2, this.getDefaultState());
               }
            }
         }
      }
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      Block block = iBlockAccess.getIBlockState(count, count2 + 1, count3).getBlock();
      return blockState.resolveIBlockState(internalImmutableSetEntry, block == Blocks.block61 || block == Blocks.block74);
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Blocks.block115
         .processAction(Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.DIRT), random, count);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return 0;
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      super.processAction3(world, blockPos, blockState, random);
      if (random.nextInt(10) == 0) {
         world.applyAttackEffectsExplosionNorm(
            AttackEffectsExplosionNormalType.TOWN_AURA,
            (double)((float)blockPos.getX() + random.nextFloat()),
            (double)((float)blockPos.getY() + 1.1F),
            (double)((float)blockPos.getZ() + random.nextFloat()),
            0.0,
            0.0,
            0.0,
            new int[0]
         );
      }
   }

   public BlockMycelium() {
      super(Material.material30, MapColor.mapColor35);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE));
      this.processAction(true);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
