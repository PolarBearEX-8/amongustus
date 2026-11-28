package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class LayerWitherAura implements LayerRenderer {
   private static final ResourceLocation WITHER_ARMOR = new ResourceLocation("textures/entity/wither/wither_armor.png");
   private final RenderWither witherRenderer;
   private final ModelWither witherModel = new ModelWither(0.5F);

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntityWither)entity, f, f2, f3, f4, f5, f6, f7);
   }

   public void doRenderLayer(EntityWither entityWither, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (entityWither.isArmored()) {
         GlStateManager.depthMask(!entityWither.isInvisible());
         this.witherRenderer.bindTexture(WITHER_ARMOR);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         float f8 = (float)entityWither.ticksExisted + f3;
         GlStateManager.translate(MathHelper.cos(f8 * 0.02F) * 3.0F, f8 * 0.01F, 0.0F);
         GlStateManager.matrixMode(5888);
         GlStateManager.enableBlend();
         float f11 = 0.5F;
         GlStateManager.color(f11, f11, f11, 1.0F);
         GlStateManager.disableLighting();
         GlStateManager.blendFunc(1, 1);
         this.witherModel.setLivingAnimations(entityWither, f, f2, f3);
         this.witherModel.setModelAttributes(this.witherRenderer.getMainModel());
         this.witherModel.render(entityWither, f, f2, f4, f5, f6, f7, true);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         GlStateManager.matrixMode(5888);
         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
      }
   }

   public LayerWitherAura(RenderWither renderWither) {
      this.witherRenderer = renderWither;
   }
}
