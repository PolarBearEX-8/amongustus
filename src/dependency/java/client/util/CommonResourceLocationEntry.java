package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCow;
import net.minecraft.util.ResourceLocation;

public class CommonResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/cow/cow.png");

   public ResourceLocation resolveResourceLocationByEntityCow(EntityCow entityCow) {
      return resourceLocation3;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityCow((EntityCow)entity);
   }

   public CommonResourceLocationEntry(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
   }
}
