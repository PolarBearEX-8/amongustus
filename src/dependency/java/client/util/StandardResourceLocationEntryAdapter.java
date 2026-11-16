package client.util;

import client.render.EntitySkinLocation;
import client.render.GlassManager;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEndercrystal;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class StandardResourceLocationEntryAdapter extends EntitySkinLocation {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
   public PassiveFloatEntry passiveFloatEntry = new GlassManager(0.0F, true);

   public ResourceLocation resolveResourceLocation(EntityEndercrystal entityEndercrystal) {
      return resourceLocation3;
   }

   public void applyEntityEndercrystal(EntityEndercrystal entityEndercrystal, double posX, double posY, double posZ, float factor, float factor2) {
      float factor3 = (float)entityEndercrystal.count12 + factor2;
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)posX, (float)posY, (float)posZ);
      this.applyResourceLocation2(resourceLocation3);
      float factor4 = MathHelper.abs(factor3 * 0.2F) / 2.0F + 0.5F;
      float factor5 = factor4 * factor4 + factor4;
      this.passiveFloatEntry.applyEntity(entityEndercrystal, 0.0F, factor3 * 3.0F, factor5 * 0.2F, 0.0F, 0.0F, 0.0625F, true);
      GlStateManager.popMatrix();
      super.applyEntity(entityEndercrystal, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityEndercrystal((EntityEndercrystal)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityEndercrystal)entity);
   }

   public StandardResourceLocationEntryAdapter(RenderManager renderManager) {
      super(renderManager);
      this.factor = 0.5F;
   }
}
