package client.util;

import client.ClientGlobals;
import client.render.EntitySkinLocation;
import client.render.RenderManager;
import client.render.WorldRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class ExtendedResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/particle/particles.png");

   public Vec3 getVec3() {
      if (!ClientGlobals.moduleManager.legacyAnimations.canExecute9()) {
         return new Vec3(-0.36, 0.03, 0.35);
      } else {
         double offset = (double)Minecraft.getMinecraft().gameSettings.scaleFactor4 / 110.0;
         return new Vec3(-offset + offset / 2.5 - offset / 8.0 + 0.16, 0.0, 0.4);
      }
   }

   public void applyEntityFishHook(EntityFishHook entityFishHook, double posX, double posY, double posZ, float factor, float factor2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
      GlStateManager.disableLighting();
      GlStateManager.translate3f3(0.5F, 0.5F, 0.5F);
      this.matchesEntity3(entityFishHook);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.rotate(180.0F - this.renderManager.scaleFactor, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-this.renderManager.scaleFactor7, 1.0F, 0.0F, 0.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry12);
      worldRenderer.getWorldRenderer(-0.5, -0.5, 0.0).getWorldRenderer6(0.0625, 0.1875).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(0.5, -0.5, 0.0).getWorldRenderer6(0.125, 0.1875).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(0.5, 0.5, 0.0).getWorldRenderer6(0.125, 0.125).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      worldRenderer.getWorldRenderer(-0.5, 0.5, 0.0).getWorldRenderer6(0.0625, 0.125).resolveWorldRenderer(0.0F, 1.0F, 0.0F).updateState();
      tessellator.updateState();
      GlStateManager.applyState8();
      GlStateManager.popMatrix();
      if (entityFishHook.targetPlayer != null) {
         float factor3 = MathHelper.abs(MathHelper.sin(entityFishHook.targetPlayer.computeFactorByFloat(factor2)) * (float) Math.PI);
         Vec3 vec = this.getVec3()
            .resolveVecByFloat(-(entityFishHook.targetPlayer.prevRotationPitch + (entityFishHook.targetPlayer.rotationPitch - entityFishHook.targetPlayer.prevRotationPitch) * factor2) * (float) Math.PI / 180.0F)
            .resolveVec3(-(entityFishHook.targetPlayer.prevRotationYaw + (entityFishHook.targetPlayer.rotationYaw - entityFishHook.targetPlayer.prevRotationYaw) * factor2) * (float) Math.PI / 180.0F)
            .resolveVec3(factor3 * 0.5F)
            .resolveVecByFloat(-factor3 * 0.7F);
         double offset = entityFishHook.targetPlayer.prevPosX + (entityFishHook.targetPlayer.posX - entityFishHook.targetPlayer.prevPosX) * (double)factor2 + vec.xCoord;
         double offset2 = entityFishHook.targetPlayer.prevPosY + (entityFishHook.targetPlayer.posY - entityFishHook.targetPlayer.prevPosY) * (double)factor2 + vec.yCoord;
         double offset3 = entityFishHook.targetPlayer.prevPosZ + (entityFishHook.targetPlayer.posZ - entityFishHook.targetPlayer.prevPosZ) * (double)factor2 + vec.zCoord;
         double height = (double)entityFishHook.targetPlayer.getHeight2();
         if (this.renderManager.gameSettings != null && this.renderManager.gameSettings.indexOffset64 > 0
            || entityFishHook.targetPlayer != Minecraft.getMinecraft().thePlayer) {
            float factor4 = (entityFishHook.targetPlayer.factor12 + (entityFishHook.targetPlayer.scaleFactor8 - entityFishHook.targetPlayer.factor12) * factor2) * (float) Math.PI / 180.0F;
            double offset4 = (double)MathHelper.abs(factor4);
            double offset5 = (double)MathHelper.cos(factor4);
            offset = entityFishHook.targetPlayer.prevPosX + (entityFishHook.targetPlayer.posX - entityFishHook.targetPlayer.prevPosX) * (double)factor2 - offset5 * 0.35 - offset4 * 0.8;
            offset2 = entityFishHook.targetPlayer.prevPosY + height + (entityFishHook.targetPlayer.posY - entityFishHook.targetPlayer.prevPosY) * (double)factor2 - 0.45;
            offset3 = entityFishHook.targetPlayer.prevPosZ + (entityFishHook.targetPlayer.posZ - entityFishHook.targetPlayer.prevPosZ) * (double)factor2 - offset4 * 0.35 + offset5 * 0.8;
            height = entityFishHook.targetPlayer.isSprinting() ? -0.1875 : 0.0;
         }

         double offset6 = entityFishHook.prevPosX + (entityFishHook.posX - entityFishHook.prevPosX) * (double)factor2;
         double offset7 = entityFishHook.prevPosY + (entityFishHook.posY - entityFishHook.prevPosY) * (double)factor2 + 0.25;
         double offset8 = entityFishHook.prevPosZ + (entityFishHook.posZ - entityFishHook.prevPosZ) * (double)factor2;
         double offset9 = (double)((float)(offset - offset6));
         double offset10 = (double)((float)(offset2 - offset7)) + height;
         double offset11 = (double)((float)(offset3 - offset8));
         GlStateManager.enableTexture2D();
         GlStateManager.applyState3();
         worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry3);

         for (int index = 0; index <= 16; index++) {
            float factor5 = (float)index / 16.0F;
            worldRenderer.getWorldRenderer(posX + offset9 * (double)factor5, posY + offset10 * (double)(factor5 * factor5 + factor5) * 0.5 + 0.25, posZ + offset11 * (double)factor5)
               .resolveWorldRenderer(0, 0, 0, 255)
               .updateState();
         }

         tessellator.updateState();
         GlStateManager.applyState13();
         GlStateManager.disableTexture2D();
         super.applyEntity(entityFishHook, posX, posY, posZ, factor, factor2);
      }
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityFishHook((EntityFishHook)entity, posX, posY, posZ, factor, factor2);
   }

   public ResourceLocation resolveResourceLocationByEntityFishHook(EntityFishHook entityFishHook) {
      return resourceLocation3;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityFishHook((EntityFishHook)entity);
   }

   public ExtendedResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
   }
}
