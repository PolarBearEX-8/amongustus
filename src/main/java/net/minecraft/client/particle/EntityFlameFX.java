package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFlameFX extends EntityFX {
   private float flameScale;

   public EntityFlameFX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, d4, d5, d6);
      this.motionX = this.motionX * 0.01F + d4;
      this.motionY = this.motionY * 0.01F + d5;
      this.motionZ = this.motionZ * 0.01F + d6;
      this.posX = this.posX + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
      this.posY = this.posY + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
      this.posZ = this.posZ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
      this.flameScale = this.particleScale;
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
      this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.2)) + 4;
      this.noClip = true;
      this.setParticleTextureIndex(48);
   }

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
      this.particleScale = this.flameScale * (1.0F - f7 * f7 * 0.5F);
      super.renderParticle(worldRenderer, entity, f, f2, f3, f4, f5, f6);
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setDead();
      }

      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.96F;
      this.motionY *= 0.96F;
      this.motionZ *= 0.96F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   @Override
   public int getBrightnessForRender(float f) {
      float f2 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      int brightnessForRender = super.getBrightnessForRender(f);
      int n = brightnessForRender & 0xFF;
      int n2 = brightnessForRender >> 16 & 0xFF;
      n += (int)(f2 * 15.0F * 16.0F);
      if (n > 240) {
         n = 240;
      }

      return n | n2 << 16;
   }

   @Override
   public float getBrightness(float f) {
      float f2 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      return super.getBrightness(f) * f2 + (1.0F - f2);
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityFlameFX(world, d, d2, d3, d4, d5, d6);
      }
   }
}
