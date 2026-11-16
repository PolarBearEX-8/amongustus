package net.minecraft.block;

import client.util.IBlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSoulSand extends Block {
   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      entity.motionX *= 0.4;
      entity.motionZ *= 0.4;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return new AxisAlignedBB((double)count, (double)count2, (double)count3, (double)(count + 1), (double)((float)(count2 + 1) - 0.125F), (double)(count3 + 1));
   }

   public BlockSoulSand() {
      super(Material.material28, MapColor.mapColor13);
      this.processAction(ItemGroupManager.itemGroupManager10);
   }
}
