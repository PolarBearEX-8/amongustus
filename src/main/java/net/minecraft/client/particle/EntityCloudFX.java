package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCloudFX extends EntityFX {
   public float oSize;

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = ((float)this.particleAge + f) / (float)this.particleMaxAge * 32.0F;
      f7 = MathHelper.clamp_float(f7, 0.0F, 1.0F);
      this.particleScale = this.oSize * f7;
      super.renderParticle(worldRenderer, entity, f, f2, f3, f4, f5, f6);
   }

   public EntityCloudFX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      float f = 2.5F;
      this.motionX *= 0.1F;
      this.motionY *= 0.1F;
      this.motionZ *= 0.1F;
      this.motionX += d4;
      this.motionY += d5;
      this.motionZ += d6;
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F - (float)(Math.random() * 0.3F);
      this.particleScale *= 0.75F;
      this.particleScale *= f;
      this.oSize = this.particleScale;
      this.particleMaxAge = (int)(8.0 / (Math.random() * 0.8 + 0.3));
      this.particleMaxAge = (int)((float)this.particleMaxAge * f);
      this.noClip = false;
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setDead();
      }

      this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.96F;
      this.motionY *= 0.96F;
      this.motionZ *= 0.96F;
      EntityPlayer closestPlayerToEntity = this.entityWorld.getClosestPlayerToEntity(this, 2.0);
      if (closestPlayerToEntity != null && this.posY > closestPlayerToEntity.getEntityBoundingBox().minY) {
         this.posY = this.posY + (closestPlayerToEntity.getEntityBoundingBox().minY - this.posY) * 0.2;
         this.motionY = this.motionY + (closestPlayerToEntity.motionY - this.motionY) * 0.2;
         this.setPosition(this.posX, this.posY, this.posZ);
      }

      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityCloudFX(world, d, d2, d3, d4, d5, d6);
      }
   }
}
