package net.minecraft.entity;

import client.render.WorldRenderer;
import client.util.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityCamelAdapter extends TertiaryEntityAdapter {
   public static final ResourceLocation resourceLocation;
   public int count16;
   public int count15;
   public TextureManager textureManager;

   @Override
   public int getCount4() {
      return 3;
   }

   @Override
   public void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer, Entity entity, float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      float factor = ((float)this.count16 + posX) / (float)this.count15;
      float factor2 = factor * factor;
      float factor3 = 2.0F - factor2 * 2.0F;
      if (factor3 > 1.0F) {
         factor3 = 1.0F;
      }

      float factor4 = factor3 * 0.2F;
      GlStateManager.applyState3();
      float factor5 = (float)(this.posX - value8);
      float factor6 = (float)(this.posY - value6);
      float factor7 = (float)(this.posZ - value7);
      float factor8 = this.worldObj
         .computeFactor(MathHelper.ceiling_double_int(this.posX), MathHelper.ceiling_double_int(this.posY), MathHelper.ceiling_double_int(this.posZ));
      this.textureManager.applyResourceLocation3(resourceLocation);
      GlStateManager.disableBlend();
      GlStateManager.glStateAction3(770, 771);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer((double)(factor5 - 0.125F), (double)factor6, (double)(factor7 + 0.125F))
         .getWorldRenderer6(0.0, 1.0)
         .resolveWorldRenderer(factor8, factor8, factor8, factor4)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor5 + 0.125F), (double)factor6, (double)(factor7 + 0.125F))
         .getWorldRenderer6(1.0, 1.0)
         .resolveWorldRenderer(factor8, factor8, factor8, factor4)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor5 + 0.125F), (double)factor6, (double)(factor7 - 0.125F))
         .getWorldRenderer6(1.0, 0.0)
         .resolveWorldRenderer(factor8, factor8, factor8, factor4)
         .updateState();
      worldRenderer.getWorldRenderer((double)(factor5 - 0.125F), (double)factor6, (double)(factor7 - 0.125F))
         .getWorldRenderer6(0.0, 0.0)
         .resolveWorldRenderer(factor8, factor8, factor8, factor4)
         .updateState();
      Tessellator.getTessellator().updateState();
      GlStateManager.enableBlend();
      GlStateManager.applyState13();
   }

   @Override
   public void updateState() {
      this.count16++;
      if (this.count16 == this.count15) {
         this.updateState9();
      }
   }

   public EntityCamelAdapter(TextureManager textureManager2, World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ, 0.0, 0.0, 0.0);
      this.textureManager = textureManager2;
      this.motionX = this.motionY = this.motionZ = 0.0;
      this.count15 = 200;
   }

   static {
      String text = "textures/particle/footprint.png";
      resourceLocation = new ResourceLocation(text);
   }
}
