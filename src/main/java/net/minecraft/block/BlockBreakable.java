package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BlockBreakable extends Block {
   private boolean ignoreSimilarity;

   public BlockBreakable(Material material, boolean flag, MapColor mapColor) {
      super(material, mapColor);
      this.ignoreSimilarity = flag;
   }

   public BlockBreakable(Material material, boolean flag) {
      this(material, flag, material.getMaterialMapColor());
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      Block block = blockState2.getBlock();
      if (this == Blocks.glass || this == Blocks.stained_glass) {
         EnumFacing opposite = facing.getOpposite();
         if (world.getBlockState2(n + opposite.getFrontOffsetX(), n2 + opposite.getFrontOffsetY(), n3 + opposite.getFrontOffsetZ()) != blockState2) {
            return true;
         }

         if (block == this) {
            return false;
         }
      }

      return (this.ignoreSimilarity || block != this) && super.shouldSideBeRendered(world, n, n2, n3, facing);
   }
}
