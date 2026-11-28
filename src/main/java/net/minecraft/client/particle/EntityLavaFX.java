package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLavaFX extends EntityFX {
   private float lavaParticleScale;

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
      this.particleScale = this.lavaParticleScale * (1.0F - f7 * f7);
      super.renderParticle(worldRenderer, entity, f, f2, f3, f4, f5, f6);
   }

   @Override
   public float getBrightness(float f) {
      return 1.0F;
   }

   @Override
   public int getBrightnessForRender(float f) {
      float f2 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
      f2 = MathHelper.clamp_float(f2, 0.0F, 1.0F);
      return ((short)240) | (super.getBrightnessForRender(f) >> 16 & 0xFF) << 16;
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setDead();
      }

      float f = (float)this.particleAge / (float)this.particleMaxAge;
      if (this.rand.nextFloat() > f) {
         this.entityWorld.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, new int[0]);
      }

      this.motionY -= 0.03;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.999F;
      this.motionY *= 0.999F;
      this.motionZ *= 0.999F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   public EntityLavaFX(World world, double d, double d2, double d3) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      this.motionX *= 0.8F;
      this.motionY *= 0.8F;
      this.motionZ *= 0.8F;
      this.motionY = (double)(this.rand.nextFloat() * 0.4F + 0.05F);
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
      this.particleScale = this.particleScale * (this.rand.nextFloat() * 2.0F + 0.2F);
      this.lavaParticleScale = this.particleScale;
      this.particleMaxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
      this.noClip = false;
      this.setParticleTextureIndex(49);
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityLavaFX(world, d, d2, d3);
      }
   }
}
