package net.minecraft.entity;

import client.render.RenderManager;
import client.render.WorldRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityZombifiedPiglinAdapter extends TertiaryEntityAdapter {
   public EntityLivingBase entityLivingBase;

   @Override
   public int getCount4() {
      return 3;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      if (this.entityLivingBase != null) {
         RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
         renderManager.setRenderPosition(TertiaryEntityAdapter.value8, TertiaryEntityAdapter.value6, TertiaryEntityAdapter.value7);
         float factor = 0.42553192F;
         float factor2 = ((float)this.count13 + posX) / (float)this.count14;
         GlStateManager.depthMask(true);
         GlStateManager.disableBlend();
         GlStateManager.disableDepth();
         GlStateManager.glStateAction3(770, 771);
         float factor3 = 240.0F;
         OpenGlHelper.applyIndex(OpenGlHelper.defaultTexUnit, factor3, factor3);
         GlStateManager.pushMatrix();
         float factor4 = 0.05F + 0.5F * MathHelper.abs(factor2 * (float) Math.PI);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, factor4);
         GlStateManager.translate(0.0F, 1.8F, 0.0F);
         GlStateManager.rotate(180.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(60.0F - 150.0F * factor2 - entity.rotationPitch, 1.0F, 0.0F, 0.0F);
         GlStateManager.translate(0.0F, -0.4F, -1.5F);
         GlStateManager.translate3f3(factor, factor, factor);
         this.entityLivingBase.rotationYaw = this.entityLivingBase.prevRotationYaw = 0.0F;
         this.entityLivingBase.scaleFactor12 = this.entityLivingBase.scaleFactor22 = 0.0F;
         renderManager.renderEntity(this.entityLivingBase, 0.0, 0.0, 0.0, 0.0F, posX);
         GlStateManager.popMatrix();
         GlStateManager.disableDepth();
      }
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.entityLivingBase == null) {
         EntityGuardian entityGuardian = new EntityGuardian(this.worldObj);
         entityGuardian.updateState48();
         this.entityLivingBase = entityGuardian;
      }
   }

   public EntityZombifiedPiglinAdapter(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ, 0.0, 0.0, 0.0);
      this.factor8 = this.factor11 = this.factor5 = 1.0F;
      this.motionX = this.motionY = this.motionZ = 0.0;
      this.factor6 = 0.0F;
      this.count14 = 30;
   }
}
