package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityPortalFX extends EntityFX {
   private float portalParticleScale;
   private double portalPosX;
   private double portalPosY;
   private double portalPosZ;

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      float f = (float)this.particleAge / (float)this.particleMaxAge;
      f = -f + f * f * 2.0F;
      f = 1.0F - f;
      this.posX = this.portalPosX + this.motionX * (double)f;
      this.posY = this.portalPosY + this.motionY * (double)f + (double)(1.0F - f);
      this.posZ = this.portalPosZ + this.motionZ * (double)f;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setDead();
      }
   }

   @Override
   public int getBrightnessForRender(float f) {
      int brightnessForRender = super.getBrightnessForRender(f);
      float f2 = (float)this.particleAge / (float)this.particleMaxAge;
      f2 *= f2;
      f2 *= f2;
      int n = brightnessForRender & 0xFF;
      int n2 = brightnessForRender >> 16 & 0xFF;
      n2 += (int)(f2 * 15.0F * 16.0F);
      if (n2 > 240) {
         n2 = 240;
      }

      return n | n2 << 16;
   }

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
      f7 = 1.0F - f7;
      f7 *= f7;
      f7 = 1.0F - f7;
      this.particleScale = this.portalParticleScale * f7;
      super.renderParticle(worldRenderer, entity, f, f2, f3, f4, f5, f6);
   }

   @Override
   public float getBrightness(float f) {
      float brightness = super.getBrightness(f);
      float f2 = (float)this.particleAge / (float)this.particleMaxAge;
      f2 = f2 * f2 * f2 * f2;
      return brightness * (1.0F - f2) + f2;
   }

   public EntityPortalFX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, d4, d5, d6);
      this.motionX = d4;
      this.motionY = d5;
      this.motionZ = d6;
      this.portalPosX = this.posX = d;
      this.portalPosY = this.posY = d2;
      this.portalPosZ = this.posZ = d3;
      float f = this.rand.nextFloat() * 0.6F + 0.4F;
      this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F * f;
      this.particleGreen *= 0.3F;
      this.particleRed *= 0.9F;
      this.particleMaxAge = (int)(Math.random() * 10.0) + 40;
      this.noClip = true;
      this.setParticleTextureIndex((int)(Math.random() * 8.0));
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityPortalFX(world, d, d2, d3, d4, d5, d6);
      }
   }
}
