package net.augustus.ui;

import net.augustus.utils.interfaces.MC;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UiIconResources implements MC {
   public static final ResourceLocation AUGUSTUS_TEXTURE = new ResourceLocation("augustus", "pictures/failed.png");
   public static final ResourceLocation AUGUSTUS_TEXTURE2 = new ResourceLocation("augustus", "pictures/success.png");
   public static final ResourceLocation AUGUSTUS_TEXTURE3 = new ResourceLocation("augustus", "pictures/wheel.png");
   public static final ResourceLocation AUGUSTUS_TEXTURE4 = new ResourceLocation("augustus", "pictures/config.png");

   public static void drawIcon(ResourceLocation location, int n, int n2, int n3, int n4, double d) {
      GL11.glPushMatrix();
      mc.getTextureManager().bindTexture(location);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      GL11.glScaled(d, d, 1.0);
      float f = 1.0F / (float)n3;
      float f2 = 1.0F / (float)n4;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)n, (double)(n2 + n4), 0.0).tex((double)(0.0F * f), (double)((0.0F + (float)n4) * f2)).endVertex();
      worldRenderer.pos((double)(n + n3), (double)(n2 + n4), 0.0)
         .tex((double)((0.0F + (float)n3) * f), (double)((0.0F + (float)n4) * f2))
         .endVertex();
      worldRenderer.pos((double)(n + n3), (double)n2, 0.0).tex((double)((0.0F + (float)n3) * f), (double)(0.0F * f2)).endVertex();
      worldRenderer.pos((double)n, (double)n2, 0.0).tex((double)(0.0F * f), (double)(0.0F * f2)).endVertex();
      instance.draw();
      GL11.glPopMatrix();
   }
}
