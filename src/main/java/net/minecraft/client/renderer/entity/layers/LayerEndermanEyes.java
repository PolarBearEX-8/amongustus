package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;

public class LayerEndermanEyes implements LayerRenderer {
   private static final ResourceLocation ENDERMAN_EYES_TEXTURE = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
   private final RenderEnderman endermanRenderer;

   public LayerEndermanEyes(RenderEnderman renderEnderman) {
      this.endermanRenderer = renderEnderman;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntityEnderman)entity, f, f2, f3, f4, f5, f6, f7);
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   public void doRenderLayer(EntityEnderman entityEnderman, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.endermanRenderer.bindTexture(ENDERMAN_EYES_TEXTURE);
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.blendFunc(1, 1);
      GlStateManager.disableLighting();
      GlStateManager.depthMask(!entityEnderman.isInvisible());
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
      this.endermanRenderer.getMainModel().render(entityEnderman, f, f2, f4, f5, f6, f7, true);
      Config.getRenderGlobal().renderOverlayEyes = false;
      if (Config.isShaderPackLoaded()) {
         Shaders.endSpiderEyes();
      }

      this.endermanRenderer.setLightmap(entityEnderman, f3);
      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
   }
}
