package net.minecraft.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityLargeExplodeFX extends EntityFX {
   private static final ResourceLocation EXPLOSION_TEXTURE;
   private static final VertexFormat VERTEX_FORMAT = new VertexFormat()
      .addElement(DefaultVertexFormats.POSITION_3F)
      .addElement(DefaultVertexFormats.TEX_2F)
      .addElement(DefaultVertexFormats.COLOR_4UB)
      .addElement(DefaultVertexFormats.TEX_2S)
      .addElement(DefaultVertexFormats.NORMAL_3B)
      .addElement(DefaultVertexFormats.PADDING_1B);
   private int life;
   private int lifeTime;
   private TextureManager theRenderEngine;
   private float size;

   @Override
   public int getBrightnessForRender(float f) {
      return 61680;
   }

   @Override
   public void renderParticle(WorldRenderer worldRenderer, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
      int n = (int)(((float)this.life + f) * 15.0F / (float)this.lifeTime);
      if (n <= 15) {
         this.theRenderEngine.bindTexture(EXPLOSION_TEXTURE);
         float f7 = (float)(n % 4) / 4.0F;
         float f8 = f7 + 0.24975F;
         float f9 = (float)(n / 4) / 4.0F;
         float f10 = f9 + 0.24975F;
         float f11 = 2.0F * this.size;
         float f12 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
         float f13 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
         float f14 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableLighting();
         RenderHelper.disableStandardItemLighting();
         worldRenderer.begin(7, VERTEX_FORMAT);
         worldRenderer.pos((double)(f12 - f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 - f4 * f11 - f6 * f11))
            .tex((double)f8, (double)f10)
            .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
            .lightmap(0, 240)
            .normal(0.0F, 1.0F, 0.0F)
            .endVertex();
         worldRenderer.pos((double)(f12 - f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 - f4 * f11 + f6 * f11))
            .tex((double)f8, (double)f9)
            .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
            .lightmap(0, 240)
            .normal(0.0F, 1.0F, 0.0F)
            .endVertex();
         worldRenderer.pos((double)(f12 + f2 * f11 + f5 * f11), (double)(f13 + f3 * f11), (double)(f14 + f4 * f11 + f6 * f11))
            .tex((double)f7, (double)f9)
            .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
            .lightmap(0, 240)
            .normal(0.0F, 1.0F, 0.0F)
            .endVertex();
         worldRenderer.pos((double)(f12 + f2 * f11 - f5 * f11), (double)(f13 - f3 * f11), (double)(f14 + f4 * f11 - f6 * f11))
            .tex((double)f7, (double)f10)
            .color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
            .lightmap(0, 240)
            .normal(0.0F, 1.0F, 0.0F)
            .endVertex();
         Tessellator.getInstance().draw();
         GlStateManager.enableLighting();
      }
   }

   @Override
   public int getFXLayer() {
      return 3;
   }

   static {
      String str = "textures/entity/explosion.png";
      EXPLOSION_TEXTURE = new ResourceLocation(str);
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.life++;
      if (this.life == this.lifeTime) {
         this.setDead();
      }
   }

   public EntityLargeExplodeFX(TextureManager textureManager, World world, double d, double d2, double d3, double d4, double d5, double d6) {
      super(world, d, d2, d3, 0.0, 0.0, 0.0);
      this.theRenderEngine = textureManager;
      this.lifeTime = 6 + this.rand.nextInt(4);
      this.particleRed = this.particleGreen = this.particleBlue = this.rand.nextFloat() * 0.6F + 0.4F;
      this.size = 1.0F - (float)d4 * 0.5F;
   }


   public static class Factory implements IParticleFactory {
      @Override
      public EntityFX getEntityFX(int n, World world, double d, double d2, double d3, double d4, double d5, double d6, int[] ints) {
         return new EntityLargeExplodeFX(Minecraft.getMinecraft().getTextureManager(), world, d, d2, d3, d4, d5, d6);
      }
   }
}
