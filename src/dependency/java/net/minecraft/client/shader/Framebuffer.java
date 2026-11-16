package net.minecraft.client.shader;

import client.render.WorldRenderer;
import client.util.DefaultVertexFormats;
import client.util.SpecialLoggerEntry;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class Framebuffer {
   public int count4;
   public int count5;
   public int indexOffset2;
   public int indexOffset1;
   public boolean enabled;
   public int count3;
   public int indexOffset;
   public int count;
   public float[] factorArray;
   public int count2;

   public void applyIndex5(int count6, int count7) {
      this.indexOffset2 = count6;
      this.indexOffset1 = count7;
      this.count4 = count6;
      this.count5 = count7;
      if (!this.isActive()) {
         this.updateState();
      } else {
         this.count3 = OpenGlHelper.getCountAsIndex();
         this.indexOffset = SpecialLoggerEntry.getCount();
         if (this.enabled) {
            this.count = OpenGlHelper.getCount();
         }

         this.applyIndex(9728);
         GlStateManager.setActiveTexture(this.indexOffset);
         GL11.glTexImage2D(3553, 0, 32856, this.count4, this.count5, 0, 6408, 5121, (ByteBuffer)null);
         OpenGlHelper.applyIndex3(OpenGlHelper.count36, this.count3);
         OpenGlHelper.applyIndex(OpenGlHelper.count36, OpenGlHelper.count17, 3553, this.indexOffset, 0);
         if (this.enabled) {
            OpenGlHelper.applyIndex(OpenGlHelper.count13, this.count);
            OpenGlHelper.applyIndexByIndex(OpenGlHelper.count13, 33190, this.count4, this.count5);
            OpenGlHelper.applyIndex(OpenGlHelper.count36, OpenGlHelper.count7, OpenGlHelper.count13, this.count);
         }

         this.updateState();
         this.updateState5();
      }
   }

   public void updateState2() {
      int count6 = OpenGlHelper.computeCountByIndex(OpenGlHelper.count36);
      if (count6 != OpenGlHelper.count23) {
         if (count6 == OpenGlHelper.count24) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT");
         } else if (count6 == OpenGlHelper.count12) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT");
         } else if (count6 == OpenGlHelper.count11) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER");
         } else if (count6 == OpenGlHelper.count3) {
            throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER");
         } else {
            throw new RuntimeException("glCheckFramebufferStatus returned unknown status:" + count6);
         }
      }
   }

   public void applyFloat(float posX, float posX2, float posY, float posZ) {
      this.factorArray[0] = posX;
      this.factorArray[1] = posX2;
      this.factorArray[2] = posY;
      this.factorArray[3] = posZ;
   }

   public void updateState() {
      this.setState(true);
      this.updateState4();
      this.renderElement();
   }

   public void applyIndex4(int count6, int count7) {
      if (!this.isActive()) {
         this.indexOffset2 = count6;
         this.indexOffset1 = count7;
      } else {
         GlStateManager.disableDepth();
         if (this.count3 >= 0) {
            this.updateState6();
         }

         this.applyIndex5(count6, count7);
         this.updateState2();
         OpenGlHelper.applyIndex3(OpenGlHelper.count36, 0);
      }
   }

   public void applyIndex(int count6) {
      if (this.isActive()) {
         this.count2 = count6;
         GlStateManager.setActiveTexture(this.indexOffset);
         GL11.glTexParameterf(3553, 10241, (float)count6);
         GL11.glTexParameterf(3553, 10240, (float)count6);
         GL11.glTexParameterf(3553, 10242, 10496.0F);
         GL11.glTexParameterf(3553, 10243, 10496.0F);
         GlStateManager.setActiveTexture(0);
      }
   }

   public void applyIndex2(int count6, int count7) {
      this.updateState6();
      this.applyIndex5(count6, count7);
   }

   public void updateState3() {
      if (this.isActive()) {
         GlStateManager.setActiveTexture(this.indexOffset);
      }
   }

   public void updateState6() {
      if (this.isActive()) {
         this.updateState5();
         this.renderElement();
         if (this.count > -1) {
            OpenGlHelper.applyIndex7(this.count);
            this.count = -1;
         }

         if (this.indexOffset > -1) {
            SpecialLoggerEntry.applyIndex(this.indexOffset);
            this.indexOffset = -1;
         }

         if (this.count3 > -1) {
            OpenGlHelper.applyIndex3(OpenGlHelper.count36, 0);
            OpenGlHelper.applyIndexByIndex(this.count3);
            this.count3 = -1;
         }
      }
   }

   public void applyIndex(int count6, int count7, boolean flag) {
      if (this.isActive()) {
         GlStateManager.applyFlag(true, true, true, false);
         GlStateManager.enableDepth();
         GlStateManager.depthMask(false);
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         GlStateManager.glStateAction(0.0, (double)count6, (double)count7, 0.0, 1000.0, 3000.0);
         GlStateManager.matrixMode(5888);
         GlStateManager.loadIdentity();
         GlStateManager.translate(0.0F, 0.0F, -2000.0F);
         GlStateManager.viewport(0, 0, count6, count7);
         GlStateManager.disableTexture2D();
         GlStateManager.applyState3();
         GlStateManager.applyState10();
         if (flag) {
            GlStateManager.enableBlend();
            GlStateManager.applyState6();
         }

         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         this.updateState3();
         float factor = (float)count6;
         float factor2 = (float)count7;
         float factor3 = (float)this.indexOffset2 / (float)this.count4;
         float factor4 = (float)this.indexOffset1 / (float)this.count5;
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         worldRenderer.getWorldRenderer(0.0, (double)factor2, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
         worldRenderer.getWorldRenderer((double)factor, (double)factor2, 0.0).getWorldRenderer6((double)factor3, 0.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
         worldRenderer.getWorldRenderer((double)factor, 0.0, 0.0).getWorldRenderer6((double)factor3, (double)factor4).resolveWorldRenderer(255, 255, 255, 255).updateState();
         worldRenderer.getWorldRenderer(0.0, 0.0, 0.0).getWorldRenderer6(0.0, (double)factor4).resolveWorldRenderer(255, 255, 255, 255).updateState();
         tessellator.updateState();
         this.updateState5();
         GlStateManager.depthMask(true);
         GlStateManager.applyFlag(true, true, true, true);
      }
   }

   public boolean checkIndex(int count6, int count7) {
      return this.indexOffset2 != count6 || this.indexOffset1 != count7;
   }

   public boolean isActive() {
      return OpenGlHelper.isActiveAsFlag();
   }

   public void renderElement() {
      if (this.isActive()) {
         OpenGlHelper.applyIndex3(OpenGlHelper.count36, 0);
      }
   }

   public void updateState4() {
      GlStateManager.applyFloat(this.factorArray[0], this.factorArray[1], this.factorArray[2], this.factorArray[3]);
      short shortValue = 16384;
      if (this.enabled) {
         GlStateManager.glClearDepth(1.0);
         shortValue |= 256;
      }

      GlStateManager.glClear(shortValue);
   }

   public void updateState5() {
      if (this.isActive()) {
         GlStateManager.setActiveTexture(0);
      }
   }

   public void applyIndexByIndex(int count6, int count7) {
      this.applyIndex(count6, count7, true);
   }

   public void setState(boolean state) {
      if (this.isActive()) {
         OpenGlHelper.applyIndex3(OpenGlHelper.count36, this.count3);
         if (state) {
            GlStateManager.viewport(0, 0, this.indexOffset2, this.indexOffset1);
         }
      }
   }

   public Framebuffer(int count6, int count7, boolean flag) {
      this.enabled = flag;
      this.count3 = -1;
      this.indexOffset = -1;
      this.count = -1;
      this.factorArray = new float[4];
      this.factorArray[0] = 1.0F;
      this.factorArray[1] = 1.0F;
      this.factorArray[2] = 1.0F;
      this.factorArray[3] = 0.0F;
      this.applyIndex4(count6, count7);
   }
}
