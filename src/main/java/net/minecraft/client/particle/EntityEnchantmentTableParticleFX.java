package net.minecraft.client.particle;

import net.minecraft.world.World;

public class EntityEnchantmentTableParticleFX extends EntityFX {
   private float oSize;
   private double coordX;
   private double coordY;
   private double coordZ;

   @Override
   public float getBrightness(float f) {
      float brightness = super.getBrightness(f);
      float f2 = (float)this.particleAge / (float)this.particleMaxAge;
      f2 *= f2;
      f2 *= f2;
      return brightness * (1.0F - f2) + f2;
   }

   public EntityEnchantmentTableParticleFX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, d4, d5, d6);
      this.motionX = d4;
      this.motionY = d5;
      this.motionZ = d6;
      this.coordX = d;
      this.coordY = d2;
      this.coordZ = d3;
      this.posX = this.prevPosX = d + d4;
      this.posY = this.prevPosY = d2 + d5;
      this.posZ = this.prevPosZ = d3 + d6;
      float f = this.rand.nextFloat() * 0.6F + 0.4F;
      this.oSize = this.particleScale = this.rand.nextFloat() * 0.5F + 0.2F;
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F * f;
      this.particleGreen *= 0.9F;
      this.particleRed *= 0.9F;
      this.particleMaxAge = (int)(Math.random() * 10.0) + 30;
      this.noClip = true;
      this.setParticleTextureIndex((int)(Math.random() * 26.0 + 1.0 + 224.0));
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      float f = (float)this.particleAge / (float)this.particleMaxAge;
      f = 1.0F - f;
      float f2 = 1.0F - f;
      f2 *= f2;
      f2 *= f2;
      this.posX = this.coordX + this.motionX * (double)f;
      this.posY = this.coordY + this.motionY * (double)f - (double)(f2 * 1.2F);
      this.posZ = this.coordZ + this.motionZ * (double)f;
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


   public static class EnchantmentTable implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityEnchantmentTableParticleFX(world, d, d2, d3, d4, d5, d6);
      }
   }
}
