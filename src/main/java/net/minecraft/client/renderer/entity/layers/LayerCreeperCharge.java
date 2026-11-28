package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;

public class LayerCreeperCharge implements LayerRenderer {
   private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
   private final RenderCreeper creeperRenderer;
   private final ModelCreeper creeperModel = new ModelCreeper(2.0F);

   public void doRenderLayer(EntityCreeper entityCreeper, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (entityCreeper.getPowered()) {
         boolean isInvisible = entityCreeper.isInvisible();
         GlStateManager.depthMask(!isInvisible);
         this.creeperRenderer.bindTexture(LIGHTNING_TEXTURE);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         float f8 = (float)entityCreeper.ticksExisted + f3;
         GlStateManager.translate(f8 * 0.01F, f8 * 0.01F, 0.0F);
         GlStateManager.matrixMode(5888);
         GlStateManager.enableBlend();
         float f9 = 0.5F;
         GlStateManager.color(f9, f9, f9, 1.0F);
         GlStateManager.disableLighting();
         GlStateManager.blendFunc(1, 1);
         this.creeperModel.setModelAttributes(this.creeperRenderer.getMainModel());
         this.creeperModel.render(entityCreeper, f, f2, f4, f5, f6, f7, true);
         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         GlStateManager.matrixMode(5888);
         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(isInvisible);
      }
   }

   public LayerCreeperCharge(RenderCreeper renderCreeper) {
      this.creeperRenderer = renderCreeper;
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntityCreeper)entity, f, f2, f3, f4, f5, f6, f7);
   }
}
