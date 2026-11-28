package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BlockLeavesBase extends Block {
   public boolean fancyGraphics;

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public BlockLeavesBase(Material material, boolean flag) {
      super(material);
      this.fancyGraphics = flag;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return (this.fancyGraphics || world.getBlockState2(n, n2, n3).getBlock() != this) && super.shouldSideBeRendered(world, n, n2, n3, facing);
   }
}
