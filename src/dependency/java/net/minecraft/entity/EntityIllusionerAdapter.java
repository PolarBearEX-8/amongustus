package net.minecraft.entity;

import client.render.WorldRenderer;
import client.util.AuxiliaryLoggerEntry;
import client.util.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityIllusionerAdapter extends TertiaryEntityAdapter {
   public static final ResourceLocation resourceLocation;
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry = new AuxiliaryLoggerEntry()
      .resolveAuxiliaryLoggerEntry(DefaultVertexFormats.altLoggerEntry)
      .resolveAuxiliaryLoggerEntry(DefaultVertexFormats.altLoggerEntry4)
      .resolveAuxiliaryLoggerEntry(DefaultVertexFormats.altLoggerEntry6)
      .resolveAuxiliaryLoggerEntry(DefaultVertexFormats.altLoggerEntry3)
      .resolveAuxiliaryLoggerEntry(DefaultVertexFormats.altLoggerEntry5)
      .resolveAuxiliaryLoggerEntry(DefaultVertexFormats.altLoggerEntry2);
   public int count15;
   public int count16;
   public TextureManager textureManager;
   public float factor14;

   @Override
   public int computeCount(float factor) {
      return 61680;
   }

   @Override
   public int getCount4() {
      return 3;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      int count = (int)(((float)this.count15 + posX) * 15.0F / (float)this.count16);
      if (count <= 15) {
         this.textureManager.applyResourceLocation3(resourceLocation);
         float factor = (float)(count % 4) / 4.0F;
         float factor2 = factor + 0.24975F;
         float factor3 = (float)(count / 4) / 4.0F;
         float factor4 = factor3 + 0.24975F;
         float factor5 = 2.0F * this.factor14;
         float factor6 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)posX - value8);
         float factor7 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)posX - value6);
         float factor8 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)posX - value7);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.applyState3();
         RenderHelper.disableStandardItemLighting();
         worldRenderer.updateAuxiliaryLoggerEntry(7, auxiliaryLoggerEntry);
         worldRenderer.getWorldRenderer((double)(factor6 - posX2 * factor5 - posY * factor5), (double)(factor7 - posX3 * factor5), (double)(factor8 - posX4 * factor5 - posZ * factor5))
            .getWorldRenderer6((double)factor2, (double)factor4)
            .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
            .resolveWorldRenderer(0, 240)
            .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
            .updateState();
         worldRenderer.getWorldRenderer((double)(factor6 - posX2 * factor5 + posY * factor5), (double)(factor7 + posX3 * factor5), (double)(factor8 - posX4 * factor5 + posZ * factor5))
            .getWorldRenderer6((double)factor2, (double)factor3)
            .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
            .resolveWorldRenderer(0, 240)
            .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
            .updateState();
         worldRenderer.getWorldRenderer((double)(factor6 + posX2 * factor5 + posY * factor5), (double)(factor7 + posX3 * factor5), (double)(factor8 + posX4 * factor5 + posZ * factor5))
            .getWorldRenderer6((double)factor, (double)factor3)
            .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
            .resolveWorldRenderer(0, 240)
            .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
            .updateState();
         worldRenderer.getWorldRenderer((double)(factor6 + posX2 * factor5 - posY * factor5), (double)(factor7 - posX3 * factor5), (double)(factor8 + posX4 * factor5 - posZ * factor5))
            .getWorldRenderer6((double)factor, (double)factor4)
            .resolveWorldRenderer(this.factor8, this.factor11, this.factor5, 1.0F)
            .resolveWorldRenderer(0, 240)
            .resolveWorldRenderer(0.0F, 1.0F, 0.0F)
            .updateState();
         Tessellator.getTessellator().updateState();
         GlStateManager.applyState13();
      }
   }

   @Override
   public void updateState() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.count15++;
      if (this.count15 == this.count16) {
         this.updateState9();
      }
   }

   public EntityIllusionerAdapter(TextureManager textureManager2, World world, double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      super(world, posX, posX2, posX3, 0.0, 0.0, 0.0);
      this.textureManager = textureManager2;
      this.count16 = 6 + this.random.nextInt(4);
      this.factor8 = this.factor11 = this.factor5 = this.random.nextFloat() * 0.6F + 0.4F;
      this.factor14 = 1.0F - (float)posX4 * 0.5F;
   }

   static {
      String text = "textures/entity/explosion.png";
      resourceLocation = new ResourceLocation(text);
   }
}
