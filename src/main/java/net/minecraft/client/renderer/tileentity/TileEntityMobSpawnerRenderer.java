package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class TileEntityMobSpawnerRenderer extends TileEntitySpecialRenderer {
   public static void renderMob(MobSpawnerBaseLogic mobSpawnerBaseLogic, double d, double d2, double d3, float f) {
      Entity cachedEntity = mobSpawnerBaseLogic.getCachedEntity(mobSpawnerBaseLogic.getSpawnerWorld());
      if (cachedEntity != null) {
         float f2 = 0.4375F;
         GlStateManager.translate(0.0F, 0.4F, 0.0F);
         GlStateManager.rotate(
            (float)(mobSpawnerBaseLogic.getPrevMobRotation() + (mobSpawnerBaseLogic.getMobRotation() - mobSpawnerBaseLogic.getPrevMobRotation()) * (double)f) * 10.0F, 0.0F, 1.0F, 0.0F
         );
         GlStateManager.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.4F, 0.0F);
         GlStateManager.scale(f2, f2, f2);
         cachedEntity.setLocationAndAngles(d, d2, d3, 0.0F, 0.0F);
         Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(cachedEntity, 0.0, 0.0, 0.0, 0.0F, f);
      }
   }

   public void renderTileEntityAt(TileEntityMobSpawner tileEntityMobSpawner, double d, double d2, double d3, float f, int n) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d + 0.5F, (float)d2, (float)d3 + 0.5F);
      renderMob(tileEntityMobSpawner.getSpawnerBaseLogic(), d, d2, d3, f);
      GlStateManager.popMatrix();
   }

   @Override
   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntityMobSpawner)tileEntity, d, d2, d3, f, n);
   }
}
