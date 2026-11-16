package net.minecraft.item;

import client.event.PreMotionDispatcher;
import client.render.GeometryPipeline;
import client.util.IBlockState;
import client.util.ListenerAdapter27;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityEnderman;
import net.minecraft.entity.EntityLivingBase;

public class DiamondMaterial implements PreMotionDispatcher {
   public final GeometryPipeline geometryPipeline;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityEnderman((EntityEnderman)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public void applyEntityEnderman(EntityEnderman entityEnderman, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      IBlockState iBlockState = entityEnderman.getIBlockState();
      if (iBlockState.getBlock().getMaterial() != Material.coreItemEntry) {
         GoldenCrystal goldenCrystal = Minecraft.getMinecraft().getGoldenCrystal();
         GlStateManager.disableLighting();
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, 0.6875F, -0.75F);
         GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.25F, 0.1875F, 0.25F);
         float factor = 0.5F;
         GlStateManager.translate3f3(-factor, -factor, factor);
         int count = entityEnderman.computeCount(posX3);
         int count2 = count % 65536;
         int count3 = count / 65536;
         OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, (float)count2 / 1.0F, (float)count3 / 1.0F);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.geometryPipeline.applyResourceLocation2(ListenerAdapter27.resourceLocation);
         goldenCrystal.applyIBlockState(iBlockState, 1.0F);
         GlStateManager.popMatrix();
         GlStateManager.applyState8();
      }
   }

   public DiamondMaterial(GeometryPipeline geometryPipeline2) {
      this.geometryPipeline = geometryPipeline2;
   }
}
