package net.minecraft.client.particle;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
public class EntityFirework {


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         EntityFirework.SparkFX sparkFX = new EntityFirework.SparkFX(world, d, d2, d3, d4, d5, d6, Minecraft.getMinecraft().effectRenderer);
         sparkFX.setAlphaF(0.99F);
         return sparkFX;
      }
   }

   public static class SparkFX extends EntityFX {
      private int baseTextureIndex = 160;
      private boolean trail;
      private boolean twinkle;
      private final EffectRenderer effectRenderer;
      private float fadeColourRed;
      private float fadeColourGreen;
      private float fadeColourBlue;
      private boolean hasFadeColour;

      @Override
      public void onUpdate() {
         this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
         }

         if (this.particleAge > this.particleMaxAge / 2) {
            this.setAlphaF(1.0F - ((float)this.particleAge - (float)(this.particleMaxAge / 2)) / (float)this.particleMaxAge);
            if (this.hasFadeColour) {
               this.particleRed = this.particleRed + (this.fadeColourRed - this.particleRed) * 0.2F;
               this.particleGreen = this.particleGreen + (this.fadeColourGreen - this.particleGreen) * 0.2F;
               this.particleBlue = this.particleBlue + (this.fadeColourBlue - this.particleBlue) * 0.2F;
            }
         }

         this.setParticleTextureIndex(this.baseTextureIndex + (7 - this.particleAge * 8 / this.particleMaxAge));
         this.motionY -= 0.004;
         this.moveEntity(this.motionX, this.motionY, this.motionZ);
         this.motionX *= 0.91F;
         this.motionY *= 0.91F;
         this.motionZ *= 0.91F;
         if (this.onGround) {
            this.motionX *= 0.7F;
            this.motionZ *= 0.7F;
         }

         if (this.trail && this.particleAge < this.particleMaxAge / 2 && (this.particleAge + this.particleMaxAge) % 2 == 0) {
            SparkFX sparkFX = new SparkFX(this.entityWorld, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0, this.effectRenderer);
            sparkFX.setAlphaF(0.99F);
            sparkFX.setRBGColorF(this.particleRed, this.particleGreen, this.particleBlue);
            sparkFX.particleAge = sparkFX.particleMaxAge / 2;
            if (this.hasFadeColour) {
               sparkFX.hasFadeColour = true;
               sparkFX.fadeColourRed = this.fadeColourRed;
               sparkFX.fadeColourGreen = this.fadeColourGreen;
               sparkFX.fadeColourBlue = this.fadeColourBlue;
            }

            sparkFX.twinkle = this.twinkle;
            this.effectRenderer.addEffect(sparkFX);
         }
      }

      @Override
      public int getBrightnessForRender(float f) {
         return 15728880;
      }

      @Override
      public AxisAlignedBB getCollisionBoundingBox() {
         return null;
      }

      public void setFadeColour(int n) {
         this.fadeColourRed = (float)((n & 0xFF0000) >> 16) / 255.0F;
         this.fadeColourGreen = (float)((n & 0xFF00) >> 8) / 255.0F;
         this.fadeColourBlue = (float)((n & 0xFF) >> 0) / 255.0F;
         this.hasFadeColour = true;
      }

      @Override
      public float getBrightness(float f) {
         return 1.0F;
      }

      public void setTrail(boolean flag) {
         this.trail = flag;
      }

      public SparkFX(World world, double d, double d2, double d3, double d4, double d5, double d6, EffectRenderer effectRenderer2) {
         super(world, d, d2, d3);
         this.motionX = d4;
         this.motionY = d5;
         this.motionZ = d6;
         this.effectRenderer = effectRenderer2;
         this.particleScale *= 0.75F;
         this.particleMaxAge = 48 + this.rand.nextInt(12);
         this.noClip = false;
      }

      @Override
      public boolean canBePushed() {
         return false;
      }

      public void setTwinkle(boolean flag) {
         this.twinkle = flag;
      }

      @Override
      public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
         if (!this.twinkle || this.particleAge < this.particleMaxAge / 3 || (this.particleAge + this.particleMaxAge) / 3 % 2 == 0) {
            super.renderParticle(worldRenderer, entity, f, f2, f3, f4, f5, f6);
         }
      }

      public void setColour(int n) {
         float f = (float)((n & 0xFF0000) >> 16) / 255.0F;
         float f2 = (float)((n & 0xFF00) >> 8) / 255.0F;
         float f3 = (float)((n & 0xFF) >> 0) / 255.0F;
         float f4 = 1.0F;
         this.setRBGColorF(f * f4, f2 * f4, f3 * f4);
      }
   }

   public static class OverlayFX extends EntityFX {
      @Override
      public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
         float f7 = 0.25F;
         float f8 = 0.5F;
         float f9 = 0.125F;
         float f10 = 0.375F;
         float f11 = 7.1F * MathHelper.sin(((float)this.particleAge + f - 1.0F) * 0.25F * (float) Math.PI);
         this.particleAlpha = 0.6F - ((float)this.particleAge + f - 1.0F) * 0.25F * 0.5F;
         float f12 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
         float f13 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
         float f14 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
         int brightnessForRender = this.getBrightnessForRender(f);
         int n = brightnessForRender >> 16 & 65535;
         int n2 = brightnessForRender & 65535;
         worldRenderer.pos((double)(f12 - f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 - f4 * f11 - f6 * f11))
            .tex(0.5, 0.375)
            .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
            .lightmap(n, n2)
            .endVertex();
         worldRenderer.pos((double)(f12 - f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 - f4 * f11 + f6 * f11))
            .tex(0.5, 0.125)
            .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
            .lightmap(n, n2)
            .endVertex();
         worldRenderer.pos((double)(f12 + f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 + f4 * f11 + f6 * f11))
            .tex(0.25, 0.125)
            .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
            .lightmap(n, n2)
            .endVertex();
         worldRenderer.pos((double)(f12 + f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 + f4 * f11 - f6 * f11))
            .tex(0.25, 0.375)
            .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
            .lightmap(n, n2)
            .endVertex();
      }

      public OverlayFX(World world, double d, double d2, double d3) {
         super(world, d, d2, d3);
         this.particleMaxAge = 4;
      }
   }

   public static class StarterFX extends EntityFX {
      private int fireworkAge;
      private final EffectRenderer theEffectRenderer;
      private NBTTagList fireworkExplosions;
      public boolean twinkle;

      private void createShaped(double d, double[][] doublesArr, int[] ints, int[] ints2, boolean flag, boolean flag2, boolean flag3) {
         double d2 = doublesArr[0][0];
         double d3 = doublesArr[0][1];
         this.createParticle(this.posX, this.posY, this.posZ, d2 * d, d3 * d, 0.0, ints, ints2, flag, flag2);
         float f = this.rand.nextFloat() * (float) Math.PI;
         double d4 = flag3 ? 0.034 : 0.34;

         for (int i = 0; i < 3; i++) {
            double d5 = (double)f + (double)((float)i * (float) Math.PI) * d4;
            double d6 = d2;
            double d7 = d3;

            for (int j = 1; j < doublesArr.length; j++) {
               double d8 = doublesArr[j][0];
               double d9 = doublesArr[j][1];

               for (double k = 0.25; k <= 1.0; k += 0.25) {
                  double d10 = (d6 + (d8 - d6) * k) * d;
                  double d11 = (d7 + (d9 - d7) * k) * d;
                  double d12 = d10 * Math.sin(d5);
                  d10 *= Math.cos(d5);

                  for (double m = -1.0; m <= 1.0; m += 2.0) {
                     this.createParticle(this.posX, this.posY, this.posZ, d10 * m, d11, d12 * m, ints, ints2, flag, flag2);
                  }
               }

               d6 = d8;
               d7 = d9;
            }
         }
      }

      public StarterFX(
         World world, double d, double d2, double d3, double d4, double d5, double d6, EffectRenderer effectRenderer, NBTTagCompound tag
      ) {
         super(world, d, d2, d3, 0.0, 0.0, 0.0);
         this.motionX = d4;
         this.motionY = d5;
         this.motionZ = d6;
         this.theEffectRenderer = effectRenderer;
         this.particleMaxAge = 8;
         if (tag != null) {
            this.fireworkExplosions = tag.getTagList("Explosions", 10);
            if (this.fireworkExplosions.tagCount() == 0) {
               this.fireworkExplosions = null;
            } else {
               this.particleMaxAge = this.fireworkExplosions.tagCount() * 2 - 1;

               for (int i = 0; i < this.fireworkExplosions.tagCount(); i++) {
                  if (this.fireworkExplosions.getCompoundTagAt(i).getBoolean("Flicker")) {
                     this.twinkle = true;
                     this.particleMaxAge += 15;
                     break;
                  }
               }
            }
         }
      }

      private void createBurst(int[] ints, int[] ints2, boolean flag, boolean flag2) {
         double d = this.rand.nextGaussian() * 0.05;
         double d2 = this.rand.nextGaussian() * 0.05;

         for (int i = 0; i < 70; i++) {
            double d3 = this.motionX * 0.5 + this.rand.nextGaussian() * 0.15 + d;
            double d4 = this.motionZ * 0.5 + this.rand.nextGaussian() * 0.15 + d2;
            double d5 = this.motionY * 0.5 + this.rand.nextDouble() * 0.5;
            this.createParticle(this.posX, this.posY, this.posZ, d3, d5, d4, ints, ints2, flag, flag2);
         }
      }

      private void createBall(double d, int n, int[] ints, int[] ints2, boolean flag, boolean flag2) {
         double posX2 = this.posX;
         double posY2 = this.posY;
         double posZ2 = this.posZ;

         for (int i = -n; i <= n; i++) {
            for (int j = -n; j <= n; j++) {
               for (int k = -n; k <= n; k++) {
                  double d2 = (double)j + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5;
                  double d3 = (double)i + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5;
                  double d4 = (double)k + (this.rand.nextDouble() - this.rand.nextDouble()) * 0.5;
                  double d5 = (double)MathHelper.sqrt_double(d2 * d2 + d3 * d3 + d4 * d4) / d + this.rand.nextGaussian() * 0.05;
                  this.createParticle(posX2, posY2, posZ2, d2 / d5, d3 / d5, d4 / d5, ints, ints2, flag, flag2);
                  if (i != -n && i != n && j != -n && j != n) {
                     k += n * 2 - 1;
                  }
               }
            }
         }
      }

      private boolean isFarFromCamera() {
         Minecraft minecraft = Minecraft.getMinecraft();
         return minecraft == null || minecraft.getRenderViewEntity() == null || minecraft.getRenderViewEntity().getDistanceSq(this.posX, this.posY, this.posZ) >= 256.0;
      }

      @Override
      public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      }

      @Override
      public int getFXLayer() {
         return 0;
      }

      @Override
      public void onUpdate() {
         if (this.fireworkAge == 0 && this.fireworkExplosions != null) {
            boolean isFarFromCamera = this.isFarFromCamera();
            boolean flag = false;
            if (this.fireworkExplosions.tagCount() >= 3) {
               flag = true;
            } else {
               for (int i = 0; i < this.fireworkExplosions.tagCount(); i++) {
                  if (this.fireworkExplosions.getCompoundTagAt(i).getByte("Type") == 1) {
                     flag = true;
                     break;
                  }
               }
            }

            String str = "fireworks." + (flag ? "largeBlast" : "blast") + (isFarFromCamera ? "_far" : "");
            this.entityWorld.playSound(this.posX, this.posY, this.posZ, str, 20.0F, 0.95F + this.rand.nextFloat() * 0.1F, true);
         }

         if (this.fireworkAge % 2 == 0 && this.fireworkExplosions != null && this.fireworkAge / 2 < this.fireworkExplosions.tagCount()) {
            int n = this.fireworkAge / 2;
            NBTTagCompound compoundTagAt2 = this.fireworkExplosions.getCompoundTagAt(n);
            byte b = compoundTagAt2.getByte("Type");
            boolean flag2 = compoundTagAt2.getBoolean("Trail");
            boolean flag3 = compoundTagAt2.getBoolean("Flicker");
            int[] intArray = compoundTagAt2.getIntArray("Colors");
            int[] intArray2 = compoundTagAt2.getIntArray("FadeColors");
            if (intArray.length == 0) {
               intArray = new int[]{ItemDye.dyeColors[0]};
            }

            if (b == 1) {
               this.createBall(0.5, 4, intArray, intArray2, flag2, flag3);
            } else if (b == 2) {
               this.createShaped(
                  0.5,
                  new double[][]{
                     {0.0, 1.0},
                     {0.3455, 0.309},
                     {0.9511, 0.309},
                     {0.3795918367346939, -0.12653061224489795},
                     {0.6122448979591837, -0.8040816326530612},
                     {0.0, -0.35918367346938773}
                  },
                  intArray,
                  intArray2,
                  flag2,
                  flag3,
                  false
               );
            } else if (b == 3) {
               this.createShaped(
                  0.5,
                  new double[][]{
                     {0.0, 0.2},
                     {0.2, 0.2},
                     {0.2, 0.6},
                     {0.6, 0.6},
                     {0.6, 0.2},
                     {0.2, 0.2},
                     {0.2, 0.0},
                     {0.4, 0.0},
                     {0.4, -0.6},
                     {0.2, -0.6},
                     {0.2, -0.4},
                     {0.0, -0.4}
                  },
                  intArray,
                  intArray2,
                  flag2,
                  flag3,
                  true
               );
            } else if (b == 4) {
               this.createBurst(intArray, intArray2, flag2, flag3);
            } else {
               this.createBall(0.25, 2, intArray, intArray2, flag2, flag3);
            }

            int n2 = intArray[0];
            float f = (float)((n2 & 0xFF0000) >> 16) / 255.0F;
            float f2 = (float)((n2 & 0xFF00) >> 8) / 255.0F;
            float f3 = (float)((n2 & 0xFF) >> 0) / 255.0F;
            EntityFirework.OverlayFX overlayFX = new EntityFirework.OverlayFX(this.entityWorld, this.posX, this.posY, this.posZ);
            overlayFX.setRBGColorF(f, f2, f3);
            this.theEffectRenderer.addEffect(overlayFX);
         }

         this.fireworkAge++;
         if (this.fireworkAge > this.particleMaxAge) {
            if (this.twinkle) {
               boolean isFarFromCamera2 = this.isFarFromCamera();
               String str2 = "fireworks." + (isFarFromCamera2 ? "twinkle_far" : "twinkle");
               this.entityWorld.playSound(this.posX, this.posY, this.posZ, str2, 20.0F, 0.9F + this.rand.nextFloat() * 0.15F, true);
            }

            this.setDead();
         }
      }

      private void createParticle(
         double d, double d2, double d3, double d4, double d5, double d6, int[] ints, int[] ints2, boolean flag, boolean flag2
      ) {
         EntityFirework.SparkFX sparkFX = new EntityFirework.SparkFX(this.entityWorld, d, d2, d3, d4, d5, d6, this.theEffectRenderer);
         sparkFX.setAlphaF(0.99F);
         sparkFX.setTrail(flag);
         sparkFX.setTwinkle(flag2);
         sparkFX.setColour(ints[this.rand.nextInt(ints.length)]);
         if (ints2 != null && ints2.length > 0) {
            sparkFX.setFadeColour(ints2[this.rand.nextInt(ints2.length)]);
         }

         this.theEffectRenderer.addEffect(sparkFX);
      }
   }
}
