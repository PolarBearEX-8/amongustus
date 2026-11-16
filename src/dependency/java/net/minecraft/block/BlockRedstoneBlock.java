package net.minecraft.block;

import client.util.IBlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BlockRedstoneBlock extends Block {
   @Override
   public boolean processAction7() {
      return true;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState blockState, EnumFacing enumFacing) {
      return 15;
   }

   public BlockRedstoneBlock(Material material, MapColor mapColor) {
      super(material, mapColor);
   }
}
