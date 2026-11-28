package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSoulSand extends Block {
   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      entity.motionX *= 0.4;
      entity.motionZ *= 0.4;
   }

   public BlockSoulSand() {
      super(Material.sand, MapColor.brownColor);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return new AxisAlignedBB((double)n, (double)n2, (double)n3, (double)(n + 1), (double)((float)(n2 + 1) - 0.125F), (double)(n3 + 1));
   }
}
