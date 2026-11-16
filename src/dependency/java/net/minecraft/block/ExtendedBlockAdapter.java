package net.minecraft.block;

import client.util.IBlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class ExtendedBlockAdapter extends Block {
   public boolean enabled;

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      Block block = iBlockState.getBlock();
      if (this == Blocks.block68 || this == Blocks.blockStainedGlass) {
         EnumFacing enumFacing2 = enumFacing.getEnumFacing5();
         int count4 = count + enumFacing2.getInt2();
         int count5 = count2 + enumFacing2.getInt3();
         int count6 = count3 + enumFacing2.getInt();
         if (iBlockAccess.getIBlockState(count4, count5, count6) != iBlockState) {
            return true;
         }

         if (block == this) {
            return false;
         }
      }

      return (this.enabled || block != this) && super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing);
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public ExtendedBlockAdapter(Material material, boolean flag, MapColor mapColor) {
      super(material, mapColor);
      this.enabled = flag;
   }

   public ExtendedBlockAdapter(Material material, boolean flag) {
      this(material, flag, material.getMapColor());
   }
}
