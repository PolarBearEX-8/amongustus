package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class LayerSaddle implements LayerRenderer {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
   private final RenderPig pigRenderer;
   private final ModelPig pigModel = new ModelPig(0.5F);

   public void doRenderLayer(EntityPig entityPig, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (entityPig.getSaddled()) {
         this.pigRenderer.bindTexture(TEXTURE);
         this.pigModel.setModelAttributes(this.pigRenderer.getMainModel());
         this.pigModel.render(entityPig, f, f2, f4, f5, f6, f7, true);
      }
   }

   public LayerSaddle(RenderPig renderPig) {
      this.pigRenderer = renderPig;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntityPig)entity, f, f2, f3, f4, f5, f6, f7);
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }
}
