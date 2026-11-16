package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobspawner;

public class AuxiliaryResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public static void applyPassiveStringEntry(PassiveStringEntry passiveStringEntry, double posX, double posY, double posZ, float factor) {
      Entity entity = passiveStringEntry.resolveEntity(passiveStringEntry.getWorld());
      if (entity != null) {
         float factor2 = 0.4375F;
         GlStateManager.translate(0.0F, 0.4F, 0.0F);
         GlStateManager.rotate((float)(passiveStringEntry.getValue2() + (passiveStringEntry.getValue() - passiveStringEntry.getValue2()) * (double)factor) * 10.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.4F, 0.0F);
         GlStateManager.translate3f3(factor2, factor2, factor2);
         entity.applyDoubleByDouble(posX, posY, posZ, 0.0F, 0.0F);
         Minecraft.getMinecraft().getRenderManager().renderEntity(entity, 0.0, 0.0, 0.0, 0.0F, factor);
      }
   }

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntityMobspawner((TileEntityMobspawner)tileEntity, posX, posY, posZ, factor, count);
   }

   public void applyTileEntityMobspawner(TileEntityMobspawner tileEntityMobspawner, double posX, double posY, double posZ, float factor, int count) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX + 0.5F, (float)posY, (float)posZ + 0.5F);
      applyPassiveStringEntry(tileEntityMobspawner.getPassiveStringEntry(), posX, posY, posZ, factor);
      GlStateManager.popMatrix();
   }
}
