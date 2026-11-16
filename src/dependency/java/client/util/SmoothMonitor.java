package client.util;

import client.render.RenderManager;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.DiamondWeapon;

public class SmoothMonitor extends ExtraResourceLocationEntryAdapter {
   public void applyDiamondWeapon(DiamondWeapon diamondWeapon, float factor, IBlockState blockState) {
      super.applyEntityMinecart(diamondWeapon, factor, blockState);
      if (blockState.getBlock() == Blocks.block29) {
         AuxiliaryResourceLocationArrayHandler.applyPassiveStringEntry(diamondWeapon.getPassiveStringEntry(), diamondWeapon.posX, diamondWeapon.posY, diamondWeapon.posZ, factor);
      }
   }

   @Override
   public void applyEntityMinecart(EntityMinecart entityMinecart, float factor, IBlockState blockState) {
      this.applyDiamondWeapon((DiamondWeapon)entityMinecart, factor, blockState);
   }

   public SmoothMonitor(RenderManager renderManager) {
      super(renderManager);
   }
}
