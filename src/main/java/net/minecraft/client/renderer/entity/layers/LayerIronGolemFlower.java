package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;

public class LayerIronGolemFlower implements LayerRenderer {
   private final RenderIronGolem ironGolemRenderer;

   public void doRenderLayer(EntityIronGolem entityIronGolem, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (entityIronGolem.getHoldRoseTick() != 0) {
         BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
         GlStateManager.enableRescaleNormal();
         GlStateManager.pushMatrix();
         GlStateManager.rotate(
            5.0F + 180.0F * ((ModelIronGolem)this.ironGolemRenderer.getMainModel()).ironGolemRightArm.rotateAngleX / (float) Math.PI, 1.0F, 0.0F, 0.0F
         );
         GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.translate(-0.9375F, -0.625F, -0.9375F);
         float f8 = 0.5F;
         GlStateManager.scale(f8, -f8, f8);
         int brightnessForRender = entityIronGolem.getBrightnessForRender(f3);
         int n = brightnessForRender % 65536;
         int n2 = brightnessForRender / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n / 1.0F, (float)n2 / 1.0F);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.ironGolemRenderer.bindTexture(TextureMap.locationBlocksTexture);
         blockRendererDispatcher.renderBlockBrightness(Blocks.red_flower.getDefaultState(), 1.0F);
         GlStateManager.popMatrix();
         GlStateManager.disableRescaleNormal();
      }
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   public LayerIronGolemFlower(RenderIronGolem renderIronGolem) {
      this.ironGolemRenderer = renderIronGolem;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntityIronGolem)entity, f, f2, f3, f4, f5, f6, f7);
   }
}
