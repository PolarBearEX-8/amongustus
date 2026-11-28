package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFX extends Entity {
   public int particleTextureIndexX;
   public int particleTextureIndexY;
   public float particleTextureJitterX;
   public float particleTextureJitterY;
   public int particleAge;
   public int particleMaxAge;
   public float particleScale;
   public float particleGravity;
   public float particleRed;
   public float particleGreen;
   public float particleBlue;
   public float particleAlpha = 1.0F;
   public TextureAtlasSprite particleIcon;
   public static double interpPosX;
   public static double interpPosY;
   public static double interpPosZ;

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (this.particleAge++ >= this.particleMaxAge) {
         this.setDead();
      }

      this.motionY = this.motionY - 0.04 * (double)this.particleGravity;
      this.moveEntity(this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.98F;
      this.motionY *= 0.98F;
      this.motionZ *= 0.98F;
      if (this.onGround) {
         this.motionX *= 0.7F;
         this.motionZ *= 0.7F;
      }
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound tag) {
   }

   public float getBlueColorF() {
      return this.particleBlue;
   }

   public EntityFX(World world, double d, double d2, double d3) {
      super(world);
      this.setSize(0.2F, 0.2F);
      this.setPosition(d, d2, d3);
      this.lastTickPosX = this.prevPosX = d;
      this.lastTickPosY = this.prevPosY = d2;
      this.lastTickPosZ = this.prevPosZ = d3;
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
      this.particleTextureJitterX = this.rand.nextFloat() * 3.0F;
      this.particleTextureJitterY = this.rand.nextFloat() * 3.0F;
      this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
      this.particleMaxAge = (int)(4.0F / (this.rand.nextFloat() * 0.9F + 0.1F));
      this.particleAge = 0;
   }

   public int getFXLayer() {
      return 0;
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName()
         + ", Pos ("
         + this.posX
         + ","
         + this.posY
         + ","
         + this.posZ
         + "), RGBA ("
         + this.particleRed
         + ","
         + this.particleGreen
         + ","
         + this.particleBlue
         + ","
         + this.particleAlpha
         + "), Age "
         + this.particleAge;
   }

   public EntityFX(World world, double d, double d2, double d3, double d4, double d5, double d6) {
      this(world, d, d2, d3);
      this.motionX = d4 + (Math.random() * 2.0 - 1.0) * 0.4F;
      this.motionY = d5 + (Math.random() * 2.0 - 1.0) * 0.4F;
      this.motionZ = d6 + (Math.random() * 2.0 - 1.0) * 0.4F;
      float f = (float)(Math.random() + Math.random() + 1.0) * 0.15F;
      float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
      this.motionX = this.motionX / (double)f2 * (double)f * 0.4F;
      this.motionY = this.motionY / (double)f2 * (double)f * 0.4F + 0.1F;
      this.motionZ = this.motionZ / (double)f2 * (double)f * 0.4F;
   }

   public void setParticleIcon(TextureAtlasSprite textureAtlasSprite) {
      if (this.getFXLayer() == 1) {
         this.particleIcon = textureAtlasSprite;
      } else {
         throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
      }
   }

   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = (float)this.particleTextureIndexX / 16.0F;
      float f8 = f7 + 0.0624375F;
      float f9 = (float)this.particleTextureIndexY / 16.0F;
      float f10 = f9 + 0.0624375F;
      float f11 = 0.1F * this.particleScale;
      if (this.particleIcon != null) {
         f7 = this.particleIcon.getMinU();
         f8 = this.particleIcon.getMaxU();
         f9 = this.particleIcon.getMinV();
         f10 = this.particleIcon.getMaxV();
      }

      float f12 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
      float f13 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
      float f14 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
      int brightnessForRender = this.getBrightnessForRender(f);
      int n = brightnessForRender >> 16 & 65535;
      int n2 = brightnessForRender & 65535;
      worldRenderer.pos((double)(f12 - f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 - f4 * f11 - f6 * f11))
         .tex((double)f8, (double)f10)
         .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f12 - f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 - f4 * f11 + f6 * f11))
         .tex((double)f8, (double)f9)
         .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f12 + f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 + f4 * f11 + f6 * f11))
         .tex((double)f7, (double)f9)
         .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f12 + f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 + f4 * f11 - f6 * f11))
         .tex((double)f7, (double)f10)
         .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
         .lightmap(n, n2)
         .endVertex();
   }

   public EntityFX multipleParticleScaleBy(float f) {
      this.setSize(0.2F * f, 0.2F * f);
      this.particleScale *= f;
      return this;
   }

   public EntityFX multiplyVelocity(float f) {
      this.motionX *= (double)f;
      this.motionY = (this.motionY - 0.1F) * (double)f + 0.1F;
      this.motionZ *= (double)f;
      return this;
   }

   @Override
   public void entityInit() {
   }

   public float getGreenColorF() {
      return this.particleGreen;
   }

   public float getRedColorF() {
      return this.particleRed;
   }

   @Override
   public boolean canAttackWithItem() {
      return false;
   }

   @Override
   public boolean canTriggerWalking() {
      return false;
   }

   public void setRBGColorF(float f, float f2, float f3) {
      this.particleRed = f;
      this.particleGreen = f2;
      this.particleBlue = f3;
   }

   public float getAlpha() {
      return this.particleAlpha;
   }

   public void setAlphaF(float f) {
      if (this.particleAlpha == 1.0F && f < 1.0F) {
         Minecraft.getMinecraft().effectRenderer.moveToAlphaLayer(this);
      } else if (this.particleAlpha < 1.0F && f == 1.0F) {
         Minecraft.getMinecraft().effectRenderer.moveToNoAlphaLayer(this);
      }

      this.particleAlpha = f;
   }

   public void nextTextureIndexX() {
      this.particleTextureIndexX++;
   }

   public void setParticleTextureIndex(int n) {
      if (this.getFXLayer() != 0) {
         throw new RuntimeException("Invalid call to Particle.setMiscTex");
      } else {
         this.particleTextureIndexX = n % 16;
         this.particleTextureIndexY = n / 16;
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound tag) {
   }
}
