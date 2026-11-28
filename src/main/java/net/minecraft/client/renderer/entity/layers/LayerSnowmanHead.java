package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class LayerSnowmanHead implements LayerRenderer {
   private final RenderSnowMan snowManRenderer;

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntitySnowman)entity, f, f2, f3, f4, f5, f6, f7);
   }

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   public void doRenderLayer(EntitySnowman entitySnowman, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (!entitySnowman.isInvisible()) {
         GlStateManager.pushMatrix();
         this.snowManRenderer.getSnowManModel().head.postRender(0.0625F);
         float f8 = 0.625F;
         GlStateManager.translate(0.0F, -0.34375F, 0.0F);
         GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.scale(f8, -f8, -f8);
         Minecraft.getMinecraft().getItemRenderer().renderItem(entitySnowman, new ItemStack(Blocks.pumpkin, 1), ItemCameraTransforms.TransformType.HEAD);
         GlStateManager.popMatrix();
      }
   }

   public LayerSnowmanHead(RenderSnowMan renderSnowMan) {
      this.snowManRenderer = renderSnowMan;
   }
}
