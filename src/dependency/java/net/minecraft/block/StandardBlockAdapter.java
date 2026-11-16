package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class StandardBlockAdapter extends Block {
   public boolean enabled;

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return (this.enabled || iBlockAccess.getIBlockState(count, count2, count3).getBlock() != this) && super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing);
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public StandardBlockAdapter(Material material, boolean flag) {
      super(material);
      this.enabled = flag;
   }
}
