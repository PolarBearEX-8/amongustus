package client.util;

import client.render.WorldRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PicturesConfigPngManager implements MinecraftAccessor {
   public static final ResourceLocation resourceLocation;
   public static final ResourceLocation resourceLocation3;
   public static final ResourceLocation resourceLocation4;
   public static final ResourceLocation resourceLocation5;
   public static final ResourceLocation resourceLocation2;

   public static void applyResourceLocation(ResourceLocation location, int count, int count2, int count3, int count4, double offset) {
      GL11.glPushMatrix();
      minecraft.getTextureManager().applyResourceLocation3(location);
      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.translate3f2(1.0F, 1.0F, 1.0F);
      GL11.glScaled(offset, offset, 1.0);
      float factor = 1.0F / (float)count3;
      float factor2 = 1.0F / (float)count4;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)count, (double)(count2 + count4), 0.0)
         .getWorldRenderer6((double)(0.0F * factor), (double)((0.0F + (float)count4) * factor2))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count3), (double)(count2 + count4), 0.0)
         .getWorldRenderer6((double)((0.0F + (float)count3) * factor), (double)((0.0F + (float)count4) * factor2))
         .updateState();
      worldRenderer.getWorldRenderer((double)(count + count3), (double)count2, 0.0)
         .getWorldRenderer6((double)((0.0F + (float)count3) * factor), (double)(0.0F * factor2))
         .updateState();
      worldRenderer.getWorldRenderer((double)count, (double)count2, 0.0).getWorldRenderer6((double)(0.0F * factor), (double)(0.0F * factor2)).updateState();
      tessellator.updateState();
      GL11.glPopMatrix();
   }

   static {
      String[] textArray = new String[]{
         "pictures/config.png", "pictures/augustusLogo.png", "augustus", "pictures/wheel.png", "augustus", "pictures/failed.png", "pictures/success.png"
      };
      resourceLocation4 = new ResourceLocation(textArray[2], textArray[3]);
      resourceLocation = new ResourceLocation(textArray[4], textArray[0]);
      resourceLocation3 = new ResourceLocation(textArray[4], textArray[6]);
      resourceLocation2 = new ResourceLocation(textArray[4], textArray[5]);
      resourceLocation5 = new ResourceLocation(textArray[4], textArray[1]);
   }
}
