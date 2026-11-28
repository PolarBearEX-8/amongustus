package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockFalling extends Block {
   public static boolean fallInstantly;

   public void onEndFalling(World world, BlockPos pos) {
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      world.scheduleUpdate(pos, this, this.tickRate(world));
   }

   @Override
   public int tickRate(World world) {
      return 2;
   }

   public void onStartFalling(EntityFallingBlock entityFallingBlock) {
   }

   public BlockFalling(Material material) {
      super(material);
   }

   public BlockFalling() {
      super(Material.sand);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote) {
         this.checkFallable(world, pos);
      }
   }

   public static boolean canFallThrough(IBlockState state) {
      Block block = state.getBlock();
      Material material = block.getMaterial();
      return block == Blocks.fire || material == Material.air || material == Material.water || material == Material.lava;
   }

   public static boolean canFallInto(World world, BlockPos pos) {
      return canFallThrough(world.getBlockState(pos));
   }

   private void checkFallable(World world, BlockPos pos) {
      if (canFallInto(world, pos.down()) && pos.getY() >= 0) {
         byte b = 32;
         if (fallInstantly || !world.isAreaLoaded(pos.add(-b, -b, -b), pos.add(b, b, b))) {
            world.setBlockToAir(pos);
            BlockPos pos2 = pos.down();

            while (canFallInto(world, pos2) && pos2.getY() > 0) {
               pos2 = pos2.down();
            }

            if (pos2.getY() > 0) {
               world.setBlockState(pos2.up(), this.getDefaultState());
            }
         } else if (!world.isRemote) {
            EntityFallingBlock entityFallingBlock = new EntityFallingBlock(
               world, (double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, world.getBlockState(pos)
            );
            this.onStartFalling(entityFallingBlock);
            world.spawnEntityInWorld(entityFallingBlock);
         }
      }
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      world.scheduleUpdate(pos, this, this.tickRate(world));
   }
}
