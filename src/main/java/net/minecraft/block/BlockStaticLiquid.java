package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockStaticLiquid extends BlockLiquid {
   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (this.blockMaterial == Material.lava && world.getGameRules().getBoolean("doFireTick")) {
         int n = rand.nextInt(3);
         if (n > 0) {
            BlockPos pos2 = pos;

            for (int i = 0; i < n; i++) {
               pos2 = pos2.add(rand.nextInt(3) - 1, 1, rand.nextInt(3) - 1);
               Block block = world.getBlockState(pos2).getBlock();
               if (block.blockMaterial == Material.air) {
                  if (this.isSurroundingBlockFlammable(world, pos2)) {
                     world.setBlockState(pos2, Blocks.fire.getDefaultState());
                     return;
                  }
               } else if (block.blockMaterial.blocksMovement()) {
                  return;
               }
            }
         } else {
            for (int j = 0; j < 3; j++) {
               BlockPos pos3 = pos.add(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);
               if (world.isAirBlock(pos3.up()) && this.getCanBlockBurn(world, pos3)) {
                  world.setBlockState(pos3.up(), Blocks.fire.getDefaultState());
               }
            }
         }
      }
   }

   private boolean getCanBlockBurn(World world, BlockPos pos) {
      return world.getBlockState(pos).getBlock().getMaterial().getCanBurn();
   }

   public boolean isSurroundingBlockFlammable(World world, BlockPos pos) {
      for (EnumFacing facing : EnumFacing.getValues()) {
         if (this.getCanBlockBurn(world, pos.offset(facing))) {
            return true;
         }
      }

      return false;
   }

   public BlockStaticLiquid(Material material) {
      super(material);
      this.setTickRandomly(false);
      if (material == Material.lava) {
         this.setTickRandomly(true);
      }
   }

   private void updateLiquid(World world, BlockPos pos, IBlockState state) {
      BlockDynamicLiquid flowingBlock = getFlowingBlock(this.blockMaterial);
      world.setBlockState(pos, flowingBlock.getDefaultState().withProperty(LEVEL, state.getValue(LEVEL)), 2);
      world.scheduleUpdate(pos, flowingBlock, this.tickRate(world));
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!this.checkForMixing(world, pos, state)) {
         this.updateLiquid(world, pos, state);
      }
   }
}
