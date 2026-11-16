package client.util;

import client.render.WorldRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class FovParser {
   public BoxesManager boxesManager = null;
   public int count = 0;
   public int count2 = 0;
   public float factor = 0.0F;
   public float factor2 = 0.0F;
   public float factor3 = 0.0F;
   public int count3 = 0;
   public int count4 = 0;
   public int count5 = 0;
   public float factor4 = 0.0F;
   public float factor5 = 0.0F;
   public float factor6 = 0.0F;
   public float factor7 = 0.0F;
   public float factor8 = 0.0F;

   public void applyTessellator(Tessellator tessellator, float factor9) {
      GlStateManager.translate(this.factor * factor9, this.factor2 * factor9, this.factor3 * factor9);
      float factor10 = this.factor5;
      float factor11 = this.factor7;
      float factor12 = this.factor6;
      float factor13 = this.factor8;
      if (this.boxesManager.enabled) {
         factor10 = this.factor7;
         factor11 = this.factor5;
      }

      if (this.boxesManager.enabled6) {
         factor12 = this.factor8;
         factor13 = this.factor6;
      }

      applyTessellator(
         tessellator, factor10, factor12, factor11, factor13, this.count3, this.count4, factor9 * (float)this.count5, this.boxesManager.factor12, this.boxesManager.factor11
      );
      GlStateManager.translate(-this.factor * factor9, -this.factor2 * factor9, -this.factor3 * factor9);
   }

   public static void applyTessellator(Tessellator tessellator, float posX, float posX2, float posY, float posZ, int count6, int count7, float posX3, float posY2, float posZ2) {
      float factor9 = posX3;
      if (posX3 < 6.25E-4F) {
         factor9 = 6.25E-4F;
      }

      float factor10 = posY - posX;
      float factor11 = posZ - posX2;
      double offset = (double)(MathHelper.computeFactor(factor10) * (posY2 / 16.0F));
      double offset2 = (double)(MathHelper.computeFactor(factor11) * (posZ2 / 16.0F));
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(0.0, offset2, 0.0).getWorldRenderer6((double)posX, (double)posZ).updateState();
      worldRenderer.getWorldRenderer(offset, offset2, 0.0).getWorldRenderer6((double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer(offset, 0.0, 0.0).getWorldRenderer6((double)posY, (double)posX2).updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, 0.0).getWorldRenderer6((double)posX, (double)posX2).updateState();
      tessellator.updateState();
      GL11.glNormal3f(0.0F, 0.0F, 1.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer(0.0, 0.0, (double)factor9).getWorldRenderer6((double)posX, (double)posX2).updateState();
      worldRenderer.getWorldRenderer(offset, 0.0, (double)factor9).getWorldRenderer6((double)posY, (double)posX2).updateState();
      worldRenderer.getWorldRenderer(offset, offset2, (double)factor9).getWorldRenderer6((double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer(0.0, offset2, (double)factor9).getWorldRenderer6((double)posX, (double)posZ).updateState();
      tessellator.updateState();
      float factor12 = 0.5F * factor10 / (float)count6;
      float factor13 = 0.5F * factor11 / (float)count7;
      GL11.glNormal3f(-1.0F, 0.0F, 0.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);

      for (int index = 0; index < count6; index++) {
         float factor14 = (float)index / (float)count6;
         float factor15 = posX + factor10 * factor14 + factor12;
         worldRenderer.getWorldRenderer((double)factor14 * offset, offset2, (double)factor9).getWorldRenderer6((double)factor15, (double)posZ).updateState();
         worldRenderer.getWorldRenderer((double)factor14 * offset, offset2, 0.0).getWorldRenderer6((double)factor15, (double)posZ).updateState();
         worldRenderer.getWorldRenderer((double)factor14 * offset, 0.0, 0.0).getWorldRenderer6((double)factor15, (double)posX2).updateState();
         worldRenderer.getWorldRenderer((double)factor14 * offset, 0.0, (double)factor9).getWorldRenderer6((double)factor15, (double)posX2).updateState();
      }

      tessellator.updateState();
      GL11.glNormal3f(1.0F, 0.0F, 0.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);

      for (int index2 = 0; index2 < count6; index2++) {
         float factor16 = (float)index2 / (float)count6;
         float factor17 = posX + factor10 * factor16 + factor12;
         float factor18 = factor16 + 1.0F / (float)count6;
         worldRenderer.getWorldRenderer((double)factor18 * offset, 0.0, (double)factor9).getWorldRenderer6((double)factor17, (double)posX2).updateState();
         worldRenderer.getWorldRenderer((double)factor18 * offset, 0.0, 0.0).getWorldRenderer6((double)factor17, (double)posX2).updateState();
         worldRenderer.getWorldRenderer((double)factor18 * offset, offset2, 0.0).getWorldRenderer6((double)factor17, (double)posZ).updateState();
         worldRenderer.getWorldRenderer((double)factor18 * offset, offset2, (double)factor9).getWorldRenderer6((double)factor17, (double)posZ).updateState();
      }

      tessellator.updateState();
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);

      for (int index3 = 0; index3 < count7; index3++) {
         float factor19 = (float)index3 / (float)count7;
         float factor20 = posX2 + factor11 * factor19 + factor13;
         float factor21 = factor19 + 1.0F / (float)count7;
         worldRenderer.getWorldRenderer(0.0, (double)factor21 * offset2, (double)factor9).getWorldRenderer6((double)posX, (double)factor20).updateState();
         worldRenderer.getWorldRenderer(offset, (double)factor21 * offset2, (double)factor9).getWorldRenderer6((double)posY, (double)factor20).updateState();
         worldRenderer.getWorldRenderer(offset, (double)factor21 * offset2, 0.0).getWorldRenderer6((double)posY, (double)factor20).updateState();
         worldRenderer.getWorldRenderer(0.0, (double)factor21 * offset2, 0.0).getWorldRenderer6((double)posX, (double)factor20).updateState();
      }

      tessellator.updateState();
      GL11.glNormal3f(0.0F, -1.0F, 0.0F);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);

      for (int index4 = 0; index4 < count7; index4++) {
         float factor22 = (float)index4 / (float)count7;
         float factor23 = posX2 + factor11 * factor22 + factor13;
         worldRenderer.getWorldRenderer(offset, (double)factor22 * offset2, (double)factor9).getWorldRenderer6((double)posY, (double)factor23).updateState();
         worldRenderer.getWorldRenderer(0.0, (double)factor22 * offset2, (double)factor9).getWorldRenderer6((double)posX, (double)factor23).updateState();
         worldRenderer.getWorldRenderer(0.0, (double)factor22 * offset2, 0.0).getWorldRenderer6((double)posX, (double)factor23).updateState();
         worldRenderer.getWorldRenderer(offset, (double)factor22 * offset2, 0.0).getWorldRenderer6((double)posY, (double)factor23).updateState();
      }

      tessellator.updateState();
   }

   public FovParser(BoxesManager boxesManager2, int count6, int count7, float posX, float posY, float posZ, int count8, int count9, int count10, float factor9) {
      this.boxesManager = boxesManager2;
      this.count = count6;
      this.count2 = count7;
      this.factor = posX;
      this.factor2 = posY;
      this.factor3 = posZ;
      this.count3 = count8;
      this.count4 = count9;
      this.count5 = count10;
      this.factor4 = factor9;
      this.factor5 = (float)count6 / boxesManager2.factor12;
      this.factor6 = (float)count7 / boxesManager2.factor11;
      this.factor7 = (float)(count6 + count8) / boxesManager2.factor12;
      this.factor8 = (float)(count7 + count9) / boxesManager2.factor11;
   }
}
