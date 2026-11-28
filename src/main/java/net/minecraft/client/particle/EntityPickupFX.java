package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.optifine.config.Config;
import net.optifine.shaders.Program;
import net.optifine.shaders.Shaders;

public class EntityPickupFX extends EntityFX {
   private Entity itemEntity;
   private Entity targetEntity;
   private int age;
   private int maxAge;
   private float yOffset;
   private RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

   @Override
   public void onUpdate() {
      this.age++;
      if (this.age == this.maxAge) {
         this.setDead();
      }
   }

   @Override
   public int getFXLayer() {
      return 3;
   }

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      Program program = null;
      if (Config.isShaderPackLoaded()) {
         program = Shaders.activeProgram;
         Shaders.nextEntity(this.itemEntity);
      }

      float f7 = ((float)this.age + f) / (float)this.maxAge;
      f7 *= f7;
      double posX2 = this.itemEntity.posX;
      double posY2 = this.itemEntity.posY;
      double posZ2 = this.itemEntity.posZ;
      double d = this.targetEntity.lastTickPosX + (this.targetEntity.posX - this.targetEntity.lastTickPosX) * (double)f;
      double d2 = this.targetEntity.lastTickPosY + (this.targetEntity.posY - this.targetEntity.lastTickPosY) * (double)f + (double)this.yOffset;
      double d3 = this.targetEntity.lastTickPosZ + (this.targetEntity.posZ - this.targetEntity.lastTickPosZ) * (double)f;
      double d4 = posX2 + (d - posX2) * (double)f7;
      double d5 = posY2 + (d2 - posY2) * (double)f7;
      double d6 = posZ2 + (d3 - posZ2) * (double)f7;
      int brightnessForRender = this.getBrightnessForRender(f);
      int n = brightnessForRender % 65536;
      int n2 = brightnessForRender / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n / 1.0F, (float)n2 / 1.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      d4 -= interpPosX;
      d5 -= interpPosY;
      d6 -= interpPosZ;
      this.renderManager.renderEntityWithPosYaw(this.itemEntity, (double)((float)d4), (double)((float)d5), (double)((float)d6), this.itemEntity.rotationYaw, f);
      if (Config.isShaderPackLoaded()) {
         Shaders.setEntityId((Entity)null);
         Shaders.useProgram(program);
      }
   }

   public EntityPickupFX(World world, Entity entity, Entity entity2, float f) {
      super(world, entity.posX, entity.posY, entity.posZ, entity.motionX, entity.motionY, entity.motionZ);
      this.itemEntity = entity;
      this.targetEntity = entity2;
      this.maxAge = 3;
      this.yOffset = f;
   }
}
