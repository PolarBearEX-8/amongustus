package net.minecraft.client.particle;

import net.minecraft.init.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class Barrier extends EntityFX {
   @Override
   public int getFXLayer() {
      return 1;
   }

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      float minU = this.particleIcon.getMinU();
      float maxU = this.particleIcon.getMaxU();
      float minV = this.particleIcon.getMinV();
      float maxV = this.particleIcon.getMaxV();
      float f7 = 0.5F;
      float f8 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
      float f9 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
      float f10 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
      int brightnessForRender = this.getBrightnessForRender(f);
      int n = brightnessForRender >> 16 & 65535;
      int n2 = brightnessForRender & 65535;
      worldRenderer.pos((double)(f8 - f2 * 0.5F - f5 * 0.5F), (double)(f9 - f3 * 0.5F), (double)(f10 - f4 * 0.5F - f6 * 0.5F))
         .tex((double)maxU, (double)maxV)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f8 - f2 * 0.5F + f5 * 0.5F), (double)(f9 + f3 * 0.5F), (double)(f10 - f4 * 0.5F + f6 * 0.5F))
         .tex((double)maxU, (double)minV)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f8 + f2 * 0.5F + f5 * 0.5F), (double)(f9 + f3 * 0.5F), (double)(f10 + f4 * 0.5F + f6 * 0.5F))
         .tex((double)minU, (double)minV)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
      worldRenderer.pos((double)(f8 + f2 * 0.5F - f5 * 0.5F), (double)(f9 - f3 * 0.5F), (double)(f10 + f4 * 0.5F - f6 * 0.5F))
         .tex((double)minU, (double)maxV)
         .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
         .lightmap(n, n2)
         .endVertex();
   }

   public Barrier(World world, double d, double d2, double d3, Item item) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      this.setParticleIcon(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(item));
      this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
      this.motionX = this.motionY = this.motionZ = 0.0;
      this.particleGravity = 0.0F;
      this.particleMaxAge = 80;
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new Barrier(world, d, d2, d3, Item.getItemFromBlock(Blocks.barrier));
      }
   }
}
