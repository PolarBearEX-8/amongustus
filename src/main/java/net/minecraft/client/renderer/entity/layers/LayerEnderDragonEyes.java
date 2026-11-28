package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;

public class LayerEnderDragonEyes implements LayerRenderer {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/enderdragon/dragon_eyes.png");
   private final RenderDragon dragonRenderer;

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntityDragon)entity, f, f2, f3, f4, f5, f6, f7);
   }

   public LayerEnderDragonEyes(RenderDragon renderDragon) {
      this.dragonRenderer = renderDragon;
   }

   public void doRenderLayer(EntityDragon entityDragon, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.dragonRenderer.bindTexture(TEXTURE);
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.blendFunc(1, 1);
      GlStateManager.disableLighting();
      GlStateManager.depthFunc(514);
      char ch = '\uf0f0';
      int n = ch % 65536;
      int n2 = ch / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n / 1.0F, (float)n2 / 1.0F);
      GlStateManager.enableLighting();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (Config.isShaderPackLoaded()) {
         Shaders.beginSpiderEyes();
      }

      Config.getRenderGlobal().renderOverlayEyes = true;
      this.dragonRenderer.getMainModel().render(entityDragon, f, f2, f4, f5, f6, f7, true);
      Config.getRenderGlobal().renderOverlayEyes = false;
      if (Config.isShaderPackLoaded()) {
         Shaders.endSpiderEyes();
      }

      this.dragonRenderer.setLightmap(entityDragon, f3);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.depthFunc(515);
   }
}
