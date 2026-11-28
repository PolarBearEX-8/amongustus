package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNoteFX extends EntityFX {
   public float noteParticleScale;

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = ((float)this.particleAge + f) / (float)this.particleMaxAge * 32.0F;
      f7 = MathHelper.clamp_float(f7, 0.0F, 1.0F);
      this.particleScale = this.noteParticleScale * f7;
      super.renderParticle(worldRenderer, entity, f, f2, f3, f4, f5, f6);
   }

   public EntityNoteFX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      this(world, d, d2, d3, d4, d5, d6, 2.0F);
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
      if (this.posY == this.prevPosY) {
         this.motionX *= 1.1;
         this.motionZ *= 1.1;
      }

      this.motionX *= 0.66F;
      this.motionY *= 0.66F;
      this.motionZ *= 0.66F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   public EntityNoteFX(World world, double d, double d2, double d3, double d4, double d5, double d6, float f) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      this.motionX *= 0.01F;
      this.motionY *= 0.01F;
      this.motionZ *= 0.01F;
      this.motionY += 0.2;
      this.particleRed = MathHelper.sin(((float)d4 + 0.0F) * (float) Math.PI * 2.0F) * 0.65F + 0.35F;
      this.particleGreen = MathHelper.sin(((float)d4 + 0.33333334F) * (float) Math.PI * 2.0F) * 0.65F + 0.35F;
      this.particleBlue = MathHelper.sin(((float)d4 + 0.6666667F) * (float) Math.PI * 2.0F) * 0.65F + 0.35F;
      this.particleScale *= 0.75F;
      this.particleScale *= f;
      this.noteParticleScale = this.particleScale;
      this.particleMaxAge = 6;
      this.noClip = false;
      this.setParticleTextureIndex(64);
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityNoteFX(world, d, d2, d3, d4, d5, d6);
      }
   }
}
