package net.minecraft.block;

import client.ClientGlobals;
import client.module.Module;
import client.util.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockBarrier extends Block {
   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
   }

   @Override
   public float getFactor() {
      return 1.0F;
   }

   @Override
   public int processAction13() {
      return ((Module)ClientGlobals.moduleManager.barriers).isToggled() ? 3 : -1;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockBarrier() {
      super(Material.material10);
      this.processAction();
      this.setHardness(6000001.0F);
      this.processAction12();
      this.state = true;
   }
}
