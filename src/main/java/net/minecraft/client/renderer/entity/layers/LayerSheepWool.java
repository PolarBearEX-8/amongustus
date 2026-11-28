package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomColors;
import net.optifine.config.Config;

public class LayerSheepWool implements LayerRenderer {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
   private final RenderSheep sheepRenderer;
   public ModelSheep1 sheepModel = new ModelSheep1();

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntitySheep)entity, f, f2, f3, f4, f5, f6, f7);
   }

   public LayerSheepWool(RenderSheep renderSheep) {
      this.sheepRenderer = renderSheep;
   }

   public void doRenderLayer(EntitySheep entitySheep, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (!entitySheep.getSheared() && !entitySheep.isInvisible()) {
         this.sheepRenderer.bindTexture(TEXTURE);
         if (entitySheep.hasCustomName() && "jeb_".equals(entitySheep.getCustomNameTag())) {
            byte b = 25;
            int n = entitySheep.ticksExisted / 25 + entitySheep.getEntityId();
            int length = EnumDyeColor.values().length;
            int n2 = n % length;
            int n3 = (n + 1) % length;
            float f8 = ((float)(entitySheep.ticksExisted % 25) + f3) / 25.0F;
            float[] dyeRgb = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(n2));
            float[] dyeRgb2 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(n3));
            if (Config.isCustomColors()) {
               dyeRgb = CustomColors.getSheepColors(EnumDyeColor.byMetadata(n2), dyeRgb);
               dyeRgb2 = CustomColors.getSheepColors(EnumDyeColor.byMetadata(n3), dyeRgb2);
            }

            GlStateManager.color(
               dyeRgb[0] * (1.0F - f8) + dyeRgb2[0] * f8, dyeRgb[1] * (1.0F - f8) + dyeRgb2[1] * f8, dyeRgb[2] * (1.0F - f8) + dyeRgb2[2] * f8
            );
         } else {
            float[] dyeRgb3 = EntitySheep.getDyeRgb(entitySheep.getFleeceColor());
            if (Config.isCustomColors()) {
               dyeRgb3 = CustomColors.getSheepColors(entitySheep.getFleeceColor(), dyeRgb3);
            }

            GlStateManager.color(dyeRgb3[0], dyeRgb3[1], dyeRgb3[2]);
         }

         this.sheepModel.setModelAttributes(this.sheepRenderer.getMainModel());
         this.sheepModel.setLivingAnimations(entitySheep, f, f2, f3);
         this.sheepModel.render(entitySheep, f, f2, f4, f5, f6, f7, true);
      }
   }
}
