package net.minecraft.client.particle;

import net.augustus.Augustus;
import net.minecraft.init.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityBreakingFX extends EntityFX {
   public EntityBreakingFX(World world, double d, double d2, double d3, Item item, int n) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      this.setParticleIcon(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(item, n));
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
      this.particleGravity = Blocks.snow.blockParticleGravity;
      this.particleScale /= 2.0F;
   }

   public EntityBreakingFX(World world, double d, double d2, double d3, double d4, double d5, double d6, Item item, int n) {
      this(world, d, d2, d3, item, n);
      this.motionX *= 0.10000000149011612;
      this.motionY *= 0.10000000149011612;
      this.motionZ *= 0.10000000149011612;
      this.motionX += d4;
      this.motionY += d5;
      this.motionZ += d6;
   }

   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float f7 = ((float)this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
      float f8 = f7 + 0.015609375F;
      float f9 = ((float)this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
      float f10 = f9 + 0.015609375F;
      float f11 = 0.1F * this.particleScale;
      if (this.particleIcon != null) {
         f7 = this.particleIcon.getInterpolatedU((double)(this.particleTextureJitterX / 4.0F * 16.0F));
         f8 = this.particleIcon.getInterpolatedU((double)((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
         f9 = this.particleIcon.getInterpolatedV((double)(this.particleTextureJitterY / 4.0F * 16.0F));
         f10 = this.particleIcon.getInterpolatedV((double)((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
      }

      float f12 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
      float f13 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
      float f14 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
      int brightnessForRender = this.getBrightnessForRender(f);
      int n = brightnessForRender >> 16 & 65535;
      int n2 = brightnessForRender & 65535;
      worldRenderer.pos((double)(f12 - f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 - f4 * f11 - f6 * f11))
         .tex((double)f7, (double)f10)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f12 - f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 - f4 * f11 + f6 * f11))
         .tex((double)f7, (double)f9)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f12 + f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 + f4 * f11 + f6 * f11))
         .tex((double)f8, (double)f9)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f12 + f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 + f4 * f11 - f6 * f11))
         .tex((double)f8, (double)f10)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
   }

   public EntityBreakingFX(World world, double d, double d2, double d3, Item item) {
      this(world, d, d2, d3, item, 0);
   }

   public int getFXLayer() {
      return 1;
   }


   public static class Factory implements IParticleFactory {

      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         if (Augustus.getInstance().getModuleManager().exploitFixer.isToggled() && ints.length == 0) {
            return null;
         } else {
            int n2 = ints.length > 1 ? ints[1] : 0;
            return new EntityBreakingFX(world, d, d2, d3, d4, d5, d6, Item.getItemById(ints[0]), n2);
         }
      }

   }

   public static class SlimeFactory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityBreakingFX(world, d, d2, d3, Items.slime_ball);
      }
   }

   public static class SnowballFactory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityBreakingFX(world, d, d2, d3, Items.snowball);
      }
   }
}
