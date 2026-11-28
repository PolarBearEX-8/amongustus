package net.minecraft.block;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDynamicLiquid extends BlockLiquid {
   public int adjacentSourceBlocks;

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      if (!this.checkForMixing(world, pos, state)) {
         world.scheduleUpdate(pos, this, this.tickRate(world));
      }
   }

   private boolean isBlocked(World world, BlockPos pos, IBlockState state) {
      Block block = world.getBlockState(pos).getBlock();
      return block instanceof BlockDoor
         || block == Blocks.standing_sign
         || block == Blocks.ladder
         || block == Blocks.reeds
         || block.blockMaterial == Material.portal
         || block.blockMaterial.blocksMovement();
   }

   private Set getPossibleFlowDirections(World world, BlockPos pos) {
      int n = 1000;
      EnumSet enumSet = EnumSet.noneOf(EnumFacing.class);

      for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         BlockPos pos2 = pos.offset(facing);
         IBlockState blockState2 = world.getBlockState(pos2);
         if (!this.isBlocked(world, pos2, blockState2) && (blockState2.getBlock().getMaterial() != this.blockMaterial || (Integer)blockState2.getValue(LEVEL) > 0)) {
            int n2;
            if (this.isBlocked(world, pos2.down(), world.getBlockState(pos2.down()))) {
               n2 = this.getSlopeDistance(world, pos2, 1, facing.getOpposite());
            } else {
               n2 = 0;
            }

            if (n2 < n) {
               enumSet.clear();
            }

            if (n2 <= n) {
               enumSet.add(facing);
               n = n2;
            }
         }
      }

      return enumSet;
   }

   private void tryFlowInto(World world, BlockPos pos, IBlockState state, int n) {
      if (this.canFlowInto(world, pos, state)) {
         if (state.getBlock() != Blocks.air) {
            if (this.blockMaterial == Material.lava) {
               this.triggerMixEffects(world, pos);
            } else {
               state.getBlock().dropBlockAsItem(world, pos, state, 0);
            }
         }

         world.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, n), 3);
      }
   }

   private boolean canFlowInto(World world, BlockPos pos, IBlockState state) {
      Material material = state.getBlock().getMaterial();
      return material != this.blockMaterial && material != Material.lava && !this.isBlocked(world, pos, state);
   }

   public int checkAdjacentBlock(World world, BlockPos pos, int n) {
      int level = this.getLevel(world, pos);
      if (level < 0) {
         return n;
      } else {
         if (level == 0) {
            this.adjacentSourceBlocks++;
         }

         if (level >= 8) {
            level = 0;
         }

         return n >= 0 && level >= n ? n : level;
      }
   }

   private int getSlopeDistance(World world, BlockPos pos, int n, EnumFacing facing) {
      int n2 = 1000;

      for (EnumFacing facing2 : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         if (facing2 != facing) {
            BlockPos pos2 = pos.offset(facing2);
            IBlockState blockState2 = world.getBlockState(pos2);
            if (!this.isBlocked(world, pos2, blockState2) && (blockState2.getBlock().getMaterial() != this.blockMaterial || (Integer)blockState2.getValue(LEVEL) > 0)) {
               if (!this.isBlocked(world, pos2.down(), blockState2)) {
                  return n;
               }

               if (n < 4) {
                  int slopeDistance = this.getSlopeDistance(world, pos2, n + 1, facing2.getOpposite());
                  if (slopeDistance < n2) {
                     n2 = slopeDistance;
                  }
               }
            }
         }
      }

      return n2;
   }

   private void placeStaticBlock(World world, BlockPos pos, IBlockState state) {
      world.setBlockState(pos, getStaticBlock(this.blockMaterial).getDefaultState().withProperty(LEVEL, state.getValue(LEVEL)), 2);
   }

   public BlockDynamicLiquid(Material material) {
      super(material);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      int value = (Integer)state.getValue(LEVEL);
      byte b = 1;
      if (this.blockMaterial == Material.lava && !world.provider.doesWaterVaporize()) {
         b = 2;
      }

      int n = this.tickRate(world);
      if (value > 0) {
         int n2 = -100;
         this.adjacentSourceBlocks = 0;

         for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
            n2 = this.checkAdjacentBlock(world, pos.offset(facing), n2);
         }

         int n3 = n2 + b;
         if (n3 >= 8 || n2 < 0) {
            n3 = -1;
         }

         if (this.getLevel(world, pos.up()) >= 0) {
            int level = this.getLevel(world, pos.up());
            if (level >= 8) {
               n3 = level;
            } else {
               n3 = level + 8;
            }
         }

         if (this.adjacentSourceBlocks >= 2 && this.blockMaterial == Material.water) {
            IBlockState blockState2 = world.getBlockState(pos.down());
            if (blockState2.getBlock().getMaterial().isSolid()) {
               n3 = 0;
            } else if (blockState2.getBlock().getMaterial() == this.blockMaterial && (Integer)blockState2.getValue(LEVEL) == 0) {
               n3 = 0;
            }
         }

         if (this.blockMaterial == Material.lava && value < 8 && n3 < 8 && n3 > value && rand.nextInt(4) != 0) {
            n *= 4;
         }

         if (n3 == value) {
            this.placeStaticBlock(world, pos, state);
         } else {
            value = n3;
            if (n3 < 0) {
               world.setBlockToAir(pos);
            } else {
               state = state.withProperty(LEVEL, n3);
               world.setBlockState(pos, state, 2);
               world.scheduleUpdate(pos, this, n);
               world.notifyNeighborsOfStateChange(pos, this);
            }
         }
      } else {
         this.placeStaticBlock(world, pos, state);
      }

      IBlockState blockState3 = world.getBlockState(pos.down());
      if (this.canFlowInto(world, pos.down(), blockState3)) {
         if (this.blockMaterial == Material.lava && world.getBlockState(pos.down()).getBlock().getMaterial() == Material.water) {
            world.setBlockState(pos.down(), Blocks.stone.getDefaultState());
            this.triggerMixEffects(world, pos.down());
            return;
         }

         if (value >= 8) {
            this.tryFlowInto(world, pos.down(), blockState3, value);
         } else {
            this.tryFlowInto(world, pos.down(), blockState3, value + 8);
         }
      } else if (value >= 0 && (value == 0 || this.isBlocked(world, pos.down(), blockState3))) {
         Set possibleFlowDirections = this.getPossibleFlowDirections(world, pos);
         int n4 = value + b;
         if (value >= 8) {
            n4 = 1;
         }

         if (n4 >= 8) {
            return;
         }

         for (EnumFacing facing2 : (Iterable<EnumFacing>)(possibleFlowDirections)) {
            this.tryFlowInto(world, pos.offset(facing2), world.getBlockState(pos.offset(facing2)), n4);
         }
      }
   }
}
