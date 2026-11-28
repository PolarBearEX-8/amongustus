package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;

public class BlockMushroom extends BlockBush implements IGrowable {
   public boolean generateBigMushroom(World world, BlockPos pos, IBlockState state, Random rand) {
      world.setBlockToAir(pos);
      WorldGenBigMushroom worldGenBigMushroom = null;
      if (this == Blocks.brown_mushroom) {
         worldGenBigMushroom = new WorldGenBigMushroom(Blocks.brown_mushroom_block);
      } else if (this == Blocks.red_mushroom) {
         worldGenBigMushroom = new WorldGenBigMushroom(Blocks.red_mushroom_block);
      }

      if (worldGenBigMushroom != null && worldGenBigMushroom.generate(world, rand, pos)) {
         return true;
      } else {
         world.setBlockState(pos, state, 3);
         return false;
      }
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (rand.nextInt(25) == 0) {
         int n = 5;

         for (BlockPos pos2 : (Iterable<BlockPos>)(BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)))) {
            if (world.getBlockState(pos2).getBlock() == this) {
               if (--n <= 0) {
                  return;
               }
            }
         }

         BlockPos pos3 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

         for (int i = 0; i < 4; i++) {
            if (world.isAirBlock(pos3) && this.canBlockStay(world, pos3, this.getDefaultState())) {
               pos = pos3;
            }

            pos3 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
         }

         if (world.isAirBlock(pos3) && this.canBlockStay(world, pos3, this.getDefaultState())) {
            world.setBlockState(pos3, this.getDefaultState(), 2);
         }
      }
   }

   @Override
   public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
      return (double)rand.nextFloat() < 0.4;
   }

   @Override
   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      if (pos.getY() >= 0 && pos.getY() < 256) {
         IBlockState blockState2 = world.getBlockState(pos.down());
         return blockState2.getBlock() == Blocks.mycelium
            ? true
            : (
               blockState2.getBlock() == Blocks.dirt && blockState2.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL
                  ? true
                  : world.getLightFromNeighbors(pos.getX(), pos.getY(), pos.getZ()) < 13 && this.canPlaceBlockOn(blockState2.getBlock())
            );
      } else {
         return false;
      }
   }

   @Override
   public boolean canPlaceBlockOn(Block block) {
      return block.isFullBlock();
   }

   public BlockMushroom() {
      float f = 0.2F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
      this.setTickRandomly(true);
   }

   @Override
   public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
      this.generateBigMushroom(world, pos, state, rand);
   }

   @Override
   public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean flag) {
      return true;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) && this.canBlockStay(world, pos, this.getDefaultState());
   }
}
