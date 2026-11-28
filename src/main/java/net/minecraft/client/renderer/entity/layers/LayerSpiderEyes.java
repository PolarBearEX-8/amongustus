package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;

public class LayerSpiderEyes implements LayerRenderer {
   private static final ResourceLocation SPIDER_EYES = new ResourceLocation("textures/entity/spider_eyes.png");
   private final RenderSpider spiderRenderer;

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntitySpider)entity, f, f2, f3, f4, f5, f6, f7);
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   public LayerSpiderEyes(RenderSpider renderSpider) {
      this.spiderRenderer = renderSpider;
   }

   public void doRenderLayer(EntitySpider entitySpider, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.spiderRenderer.bindTexture(SPIDER_EYES);
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.blendFunc(1, 1);
      if (entitySpider.isInvisible()) {
         GlStateManager.depthMask(false);
      } else {
         GlStateManager.depthMask(true);
      }

      int n = 61680;
      int n2 = n % 65536;
      int n3 = n / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n2 / 1.0F, (float)n3 / 1.0F);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (Config.isShaderPackLoaded()) {
         Shaders.beginSpiderEyes();
      }

      Config.getRenderGlobal().renderOverlayEyes = true;
      this.spiderRenderer.getMainModel().render(entitySpider, f, f2, f4, f5, f6, f7, true);
      Config.getRenderGlobal().renderOverlayEyes = false;
      if (Config.isShaderPackLoaded()) {
         Shaders.endSpiderEyes();
      }

      n = entitySpider.getBrightnessForRender(f3);
      n2 = n % 65536;
      n3 = n / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n2 / 1.0F, (float)n3 / 1.0F);
      this.spiderRenderer.setLightmap(entitySpider, f3);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
   }
}
