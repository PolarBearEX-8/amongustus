package client.util;

import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPrimedtnt;
import net.minecraft.item.GoldenCrystal;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class EnhancedResourceLocationEntryAdapter extends EntitySkinLocation {
   public ResourceLocation resolveResourceLocationByEntityPrimedtnt(EntityPrimedtnt entityPrimedtnt) {
      return ListenerAdapter27.resourceLocation;
   }

   public void applyEntityPrimedtnt(EntityPrimedtnt entityPrimedtnt, double posX, double posY, double posZ, float factor, float factor2) {
      GoldenCrystal goldenCrystal = Minecraft.getMinecraft().getGoldenCrystal();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY + 0.5F, (float)posZ);
      if ((float)entityPrimedtnt.indexOffset4 - factor2 + 1.0F < 10.0F) {
         float factor3 = MathHelper.clamp_float(1.0F - ((float)entityPrimedtnt.indexOffset4 - factor2 + 1.0F) / 10.0F, 0.0F, 1.0F);
         float factor4 = factor3 * factor3;
         float factor5 = factor4 * factor4;
         float factor6 = 1.0F + factor5 * 0.3F;
         GlStateManager.translate3f3(factor6, factor6, factor6);
      }

      float factor7 = (1.0F - ((float)entityPrimedtnt.indexOffset4 - factor2 + 1.0F) / 100.0F) * 0.8F;
      this.matchesEntity3(entityPrimedtnt);
      GlStateManager.translate(-0.5F, -0.5F, 0.5F);
      goldenCrystal.applyIBlockState(Blocks.block135.getDefaultState(), entityPrimedtnt.computeFactor(factor2));
      GlStateManager.translate(0.0F, 0.0F, 1.0F);
      if (entityPrimedtnt.indexOffset4 / 5 % 2 == 0) {
         GlStateManager.enableTexture2D();
         GlStateManager.applyState3();
         GlStateManager.disableBlend();
         GlStateManager.glStateAction3(770, 772);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, factor7);
         GlStateManager.applyFloat(-3.0F, -3.0F);
         GlStateManager.applyState4();
         goldenCrystal.applyIBlockState(Blocks.block135.getDefaultState(), 1.0F);
         GlStateManager.applyFloat(0.0F, 0.0F);
         GlStateManager.enableAlpha();
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
         GlStateManager.applyState13();
         GlStateManager.disableTexture2D();
      }

      GlStateManager.popMatrix();
      super.applyEntity(entityPrimedtnt, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityPrimedtnt((EntityPrimedtnt)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityPrimedtnt((EntityPrimedtnt)entity);
   }

   public EnhancedResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
      this.factor = 0.5F;
   }
}
