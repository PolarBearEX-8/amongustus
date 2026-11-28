package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomColors;
import net.optifine.config.Config;

public class LayerWolfCollar implements LayerRenderer {
   private static final ResourceLocation WOLF_COLLAR = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
   private final RenderWolf wolfRenderer;

   public LayerWolfCollar(RenderWolf renderWolf) {
      this.wolfRenderer = renderWolf;
   }

   public void doRenderLayer(EntityWolf entityWolf, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      if (entityWolf.isTamed() && !entityWolf.isInvisible()) {
         this.wolfRenderer.bindTexture(WOLF_COLLAR);
         EnumDyeColor enumDyeColor = EnumDyeColor.byMetadata(entityWolf.getCollarColor().getMetadata());
         float[] dyeRgb = EntitySheep.getDyeRgb(enumDyeColor);
         if (Config.isCustomColors()) {
            dyeRgb = CustomColors.getWolfCollarColors(enumDyeColor, dyeRgb);
         }

         GlStateManager.color(dyeRgb[0], dyeRgb[1], dyeRgb[2]);
         this.wolfRenderer.getMainModel().render(entityWolf, f, f2, f4, f5, f6, f7, true);
      }
   }

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   @Override
   public void doRenderLayer(EntityLivingBase entity, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
      this.doRenderLayer((EntityWolf)entity, f, f2, f3, f4, f5, f6, f7);
   }
}
