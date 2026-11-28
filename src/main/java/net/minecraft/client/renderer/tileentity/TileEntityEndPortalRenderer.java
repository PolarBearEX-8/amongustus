package net.minecraft.client.renderer.tileentity;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.shaders.ShadersRender;

public class TileEntityEndPortalRenderer extends TileEntitySpecialRenderer {
   private static final ResourceLocation END_SKY_TEXTURE = new ResourceLocation("textures/environment/end_sky.png");
   private static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");
   private static final Random random = new Random(31100L);
   public FloatBuffer floatBuffer = GLAllocation.createDirectFloatBuffer(16);

   private FloatBuffer getBuffer(float f, float f2, float f3, float f4) {
      ((Buffer)this.floatBuffer).clear();
      this.floatBuffer.put(f).put(f2).put(f3).put(f4);
      ((Buffer)this.floatBuffer).flip();
      return this.floatBuffer;
   }

   @Override
   public void renderTileEntityAt(TileEntity tileEntity, double d, double d2, double d3, float f, int n) {
      this.renderTileEntityAt((TileEntityEndPortal)tileEntity, d, d2, d3, f, n);
   }

   public void renderTileEntityAt(TileEntityEndPortal tileEntityEndPortal, double d, double d2, double d3, float f, int n) {
      if (!Config.isShaderPackLoaded() || !ShadersRender.renderEndPortal(tileEntityEndPortal, d, d2, d3, f, n, 0.75F)) {
         float entityX = (float)this.rendererDispatcher.entityX;
         float entityY = (float)this.rendererDispatcher.entityY;
         float entityZ = (float)this.rendererDispatcher.entityZ;
         GlStateManager.disableLighting();
         random.setSeed(31100L);
         float f2 = 0.75F;

         for (int i = 0; i < 16; i++) {
            GlStateManager.pushMatrix();
            float f3 = (float)(16 - i);
            float f4 = 0.0625F;
            float f5 = 1.0F / (f3 + 1.0F);
            if (i == 0) {
               this.bindTexture(END_SKY_TEXTURE);
               f5 = 0.1F;
               f3 = 65.0F;
               f4 = 0.125F;
               GlStateManager.enableBlend();
               GlStateManager.blendFunc(770, 771);
            }

            if (i >= 1) {
               this.bindTexture(END_PORTAL_TEXTURE);
            }

            if (i == 1) {
               GlStateManager.enableBlend();
               GlStateManager.blendFunc(1, 1);
               f4 = 0.5F;
            }

            float f6 = (float)(-(d2 + (double)f2));
            float f7 = f6 + (float)ActiveRenderInfo.getPosition().yCoord;
            float f9 = f7 / (f6 + f3 + (float)ActiveRenderInfo.getPosition().yCoord);
            f9 = (float)(d2 + (double)f2) + f9;
            GlStateManager.translate(entityX, f9, entityZ);
            GlStateManager.texGen(GlStateManager.TexGen.S, 9217);
            GlStateManager.texGen(GlStateManager.TexGen.T, 9217);
            GlStateManager.texGen(GlStateManager.TexGen.R, 9217);
            GlStateManager.texGen(GlStateManager.TexGen.Q, 9216);
            GlStateManager.texGen(GlStateManager.TexGen.S, 9473, this.getBuffer(1.0F, 0.0F, 0.0F, 0.0F));
            GlStateManager.texGen(GlStateManager.TexGen.T, 9473, this.getBuffer(0.0F, 0.0F, 1.0F, 0.0F));
            GlStateManager.texGen(GlStateManager.TexGen.R, 9473, this.getBuffer(0.0F, 0.0F, 0.0F, 1.0F));
            GlStateManager.texGen(GlStateManager.TexGen.Q, 9474, this.getBuffer(0.0F, 1.0F, 0.0F, 0.0F));
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.S);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.T);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.R);
            GlStateManager.enableTexGenCoord(GlStateManager.TexGen.Q);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0F, (float)(Minecraft.getSystemTime() % 700000L) / 700000.0F, 0.0F);
            GlStateManager.scale(f4, f4, f4);
            GlStateManager.translate(0.5F, 0.5F, 0.0F);
            GlStateManager.rotate((float)(i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(-0.5F, -0.5F, 0.0F);
            GlStateManager.translate(-entityX, -entityZ, -entityY);
            f7 = f6 + (float)ActiveRenderInfo.getPosition().yCoord;
            GlStateManager.translate(
               (float)ActiveRenderInfo.getPosition().xCoord * f3 / f7, (float)ActiveRenderInfo.getPosition().zCoord * f3 / f7, -entityY
            );
            Tessellator instance = Tessellator.getInstance();
            WorldRenderer worldRenderer = instance.getWorldRenderer();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            float f10 = (random.nextFloat() * 0.5F + 0.1F) * f5;
            float f11 = (random.nextFloat() * 0.5F + 0.4F) * f5;
            float f12 = (random.nextFloat() * 0.5F + 0.5F) * f5;
            if (i == 0) {
               f10 = f11 = f12 = 1.0F * f5;
            }

            worldRenderer.pos(d, d2 + (double)f2, d3).color(f10, f11, f12, 1.0F).endVertex();
            worldRenderer.pos(d, d2 + (double)f2, d3 + 1.0).color(f10, f11, f12, 1.0F).endVertex();
            worldRenderer.pos(d + 1.0, d2 + (double)f2, d3 + 1.0).color(f10, f11, f12, 1.0F).endVertex();
            worldRenderer.pos(d + 1.0, d2 + (double)f2, d3).color(f10, f11, f12, 1.0F).endVertex();
            instance.draw();
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
            this.bindTexture(END_SKY_TEXTURE);
         }

         GlStateManager.disableBlend();
         GlStateManager.disableTexGenCoord(GlStateManager.TexGen.S);
         GlStateManager.disableTexGenCoord(GlStateManager.TexGen.T);
         GlStateManager.disableTexGenCoord(GlStateManager.TexGen.R);
         GlStateManager.disableTexGenCoord(GlStateManager.TexGen.Q);
         GlStateManager.enableLighting();
      }
   }
}
