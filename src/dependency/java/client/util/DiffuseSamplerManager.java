package client.util;

import client.render.ShaderManager;
import client.render.WorldRenderer;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.util.vector.Matrix4f;

public class DiffuseSamplerManager {
   public final ShaderManager shaderManager;
   public final Framebuffer framebuffer2;
   public final Framebuffer framebuffer;
   public final List list3 = Lists.newArrayList();
   public final List list4 = Lists.newArrayList();
   public final List list = Lists.newArrayList();
   public final List list2 = Lists.newArrayList();
   public Matrix4f matrix4f;

   public void updateState() {
      this.shaderManager.renderElement3();
   }

   public ShaderManager getShaderManager() {
      return this.shaderManager;
   }

   public void applyFloat(float factor) {
      this.updateState2();
      this.framebuffer2.renderElement();
      float count = (float)this.framebuffer.count4;
      float count2 = (float)this.framebuffer.count5;
      GlStateManager.viewport(0, 0, (int)count, (int)count2);
      this.shaderManager.applyText("DiffuseSampler", this.framebuffer2);

      for (int index = 0; index < this.list3.size(); index++) {
         this.shaderManager.applyText((String)this.list4.get(index), this.list3.get(index));
         this.shaderManager
            .resolveIntManagerByText("AuxSize" + index)
            .applyFloat((float)((Integer)this.list.get(index)).intValue(), (float)((Integer)this.list2.get(index)).intValue());
      }

      this.shaderManager.resolveIntManagerByText("ProjMat").applyMatrix4f(this.matrix4f);
      this.shaderManager.resolveIntManagerByText("InSize").applyFloat((float)this.framebuffer2.count4, (float)this.framebuffer2.count5);
      this.shaderManager.resolveIntManagerByText("OutSize").applyFloat(count, count2);
      this.shaderManager.resolveIntManagerByText("Time").applyFloat(factor);
      Minecraft mc = Minecraft.getMinecraft();
      this.shaderManager.resolveIntManagerByText("ScreenSize").applyFloat((float)mc.displayWidth, (float)mc.displayHeight);
      this.shaderManager.renderElement2();
      this.framebuffer.updateState();
      this.framebuffer.setState(false);
      GlStateManager.depthMask(false);
      GlStateManager.applyFlag(true, true, true, true);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
      worldRenderer.getWorldRenderer(0.0, (double)count2, 500.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
      worldRenderer.getWorldRenderer((double)count, (double)count2, 500.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
      worldRenderer.getWorldRenderer((double)count, 0.0, 500.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
      worldRenderer.getWorldRenderer(0.0, 0.0, 500.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
      tessellator.updateState();
      GlStateManager.depthMask(true);
      GlStateManager.applyFlag(true, true, true, true);
      this.shaderManager.renderElement();
      this.framebuffer.renderElement();
      this.framebuffer2.updateState5();

      for (Object element : this.list3) {
         if (element instanceof Framebuffer) {
            ((Framebuffer)element).updateState5();
         }
      }
   }

   public void setMatrix4f(Matrix4f matrix4f2) {
      this.matrix4f = matrix4f2;
   }

   public void applyText(String text, Object entry, int count, int count2) {
      this.list4.add(this.list4.size(), text);
      this.list3.add(this.list3.size(), entry);
      this.list.add(this.list.size(), count);
      this.list2.add(this.list2.size(), count2);
   }

   private void updateState2() {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableBlend();
      GlStateManager.enableDepth();
      GlStateManager.applyState10();
      GlStateManager.applyState20();
      GlStateManager.applyState3();
      GlStateManager.applyState7();
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(0);
   }

   public DiffuseSamplerManager(IResourceManager iResourceManager, String text, Framebuffer framebuffer3, Framebuffer framebuffer4) throws FileNotFoundIOException, IOException {
      this.shaderManager = new ShaderManager(iResourceManager, text);
      this.framebuffer2 = framebuffer3;
      this.framebuffer = framebuffer4;
   }
}
