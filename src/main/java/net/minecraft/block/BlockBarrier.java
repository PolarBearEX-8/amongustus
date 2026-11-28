package net.minecraft.block;

import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockBarrier extends Block {

   @Override
   public int getRenderType() {
      return ((Module)ClientManagers.moduleManager.barriers).isToggled() ? 3 : -1;
   }

   @Override
   public float getAmbientOcclusionLightValue() {
      return 1.0F;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public BlockBarrier() {
      super(Material.barrier);
      this.setBlockUnbreakable();
      this.setResistance(6000001.0F);
      this.disableStats();
      this.translucent = true;
   }
}
