package net.minecraft.client.renderer.entity.layers;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;

public class LayerHeldBlock implements LayerRenderer {
   private final RenderEnderman endermanRenderer;

   public LayerHeldBlock(RenderEnderman renderEnderman) {
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
      IBlockState heldBlockState = entityEnderman.getHeldBlockState();
      if (heldBlockState.getBlock().getMaterial() != Material.air) {
         BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
         GlStateManager.enableRescaleNormal();
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, 0.6875F, -0.75F);
         GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.translate(0.25F, 0.1875F, 0.25F);
         float f8 = 0.5F;
         GlStateManager.scale(-f8, -f8, f8);
         int brightnessForRender = entityEnderman.getBrightnessForRender(f3);
         int n = brightnessForRender % 65536;
         int n2 = brightnessForRender / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n / 1.0F, (float)n2 / 1.0F);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.endermanRenderer.bindTexture(TextureMap.locationBlocksTexture);
         blockRendererDispatcher.renderBlockBrightness(heldBlockState, 1.0F);
         GlStateManager.popMatrix();
         GlStateManager.disableRescaleNormal();
      }
   }
}
