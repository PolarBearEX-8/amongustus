package net.minecraft.client.shader;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonException;
import org.lwjgl.util.vector.Matrix4f;

public class Shader {
   private final ShaderManager manager;
   public final Framebuffer framebufferIn;
   public final Framebuffer framebufferOut;
   private final List listAuxFramebuffers = Lists.newArrayList();
   private final List listAuxNames = Lists.newArrayList();
   private final List listAuxWidths = Lists.newArrayList();
   private final List listAuxHeights = Lists.newArrayList();
   private Matrix4f projectionMatrix;

   public void addAuxFramebuffer(String str, Object obj, int n, int n2) {
      this.listAuxNames.add(this.listAuxNames.size(), str);
      this.listAuxFramebuffers.add(this.listAuxFramebuffers.size(), obj);
      this.listAuxWidths.add(this.listAuxWidths.size(), n);
      this.listAuxHeights.add(this.listAuxHeights.size(), n2);
   }

   public void deleteShader() {
      this.manager.deleteShader();
   }

   public ShaderManager getShaderManager() {
      return this.manager;
   }

   public void loadShader(float f) {
      this.preLoadShader();
      this.framebufferIn.unbindFramebuffer();
      float f2 = (float)this.framebufferOut.framebufferTextureWidth;
      float f3 = (float)this.framebufferOut.framebufferTextureHeight;
      GlStateManager.viewport(0, 0, (int)f2, (int)f3);
      this.manager.addSamplerTexture("DiffuseSampler", this.framebufferIn);

      for (int i = 0; i < this.listAuxFramebuffers.size(); i++) {
         this.manager.addSamplerTexture((String)this.listAuxNames.get(i), this.listAuxFramebuffers.get(i));
         this.manager
            .getShaderUniformOrDefault("AuxSize" + i)
            .set((float)((Integer)this.listAuxWidths.get(i)).intValue(), (float)((Integer)this.listAuxHeights.get(i)).intValue());
      }

      this.manager.getShaderUniformOrDefault("ProjMat").set(this.projectionMatrix);
      this.manager
         .getShaderUniformOrDefault("InSize")
         .set((float)this.framebufferIn.framebufferTextureWidth, (float)this.framebufferIn.framebufferTextureHeight);
      this.manager.getShaderUniformOrDefault("OutSize").set(f2, f3);
      this.manager.getShaderUniformOrDefault("Time").set(f);
      Minecraft minecraft = Minecraft.getMinecraft();
      this.manager.getShaderUniformOrDefault("ScreenSize").set((float)minecraft.displayWidth, (float)minecraft.displayHeight);
      this.manager.useShader();
      this.framebufferOut.framebufferClear();
      this.framebufferOut.bindFramebuffer(false);
      GlStateManager.depthMask(false);
      GlStateManager.colorMask(true, true, true, true);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos(0.0, (double)f3, 500.0).color(255, 255, 255, 255).endVertex();
      worldRenderer.pos((double)f2, (double)f3, 500.0).color(255, 255, 255, 255).endVertex();
      worldRenderer.pos((double)f2, 0.0, 500.0).color(255, 255, 255, 255).endVertex();
      worldRenderer.pos(0.0, 0.0, 500.0).color(255, 255, 255, 255).endVertex();
      instance.draw();
      GlStateManager.depthMask(true);
      GlStateManager.colorMask(true, true, true, true);
      this.manager.endShader();
      this.framebufferOut.unbindFramebuffer();
      this.framebufferIn.unbindFramebufferTexture();

      for (Object obj : this.listAuxFramebuffers) {
         if (obj instanceof Framebuffer) {
            ((Framebuffer)obj).unbindFramebufferTexture();
         }
      }
   }

   public Shader(IResourceManager resourceManager, String str, Framebuffer framebuffer, Framebuffer framebuffer2) throws JsonException, IOException {
      this.manager = new ShaderManager(resourceManager, str);
      this.framebufferIn = framebuffer;
      this.framebufferOut = framebuffer2;
   }

   public void setProjectionMatrix(Matrix4f matrix4f) {
      this.projectionMatrix = matrix4f;
   }

   private void preLoadShader() {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableBlend();
      GlStateManager.disableDepth();
      GlStateManager.disableAlpha();
      GlStateManager.disableFog();
      GlStateManager.disableLighting();
      GlStateManager.disableColorMaterial();
      GlStateManager.enableTexture2D();
      GlStateManager.bindTexture(0);
   }
}
