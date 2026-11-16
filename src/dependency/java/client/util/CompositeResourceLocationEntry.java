package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySilverfish;
import net.minecraft.item.BaseItemArrayEntry;
import net.minecraft.util.ResourceLocation;

public class CompositeResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/silverfish.png");

   public ResourceLocation resolveResourceLocationByEntitySilverfish(EntitySilverfish entitySilverfish) {
      return resourceLocation3;
   }

   @Override
   public float computeFactor(EntityLivingBase livingEntity) {
      return this.computeFactor((EntitySilverfish)livingEntity);
   }

   public float computeFactor(EntitySilverfish entitySilverfish) {
      return 180.0F;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntitySilverfish((EntitySilverfish)entity);
   }

   public CompositeResourceLocationEntry(RenderManager renderManager) {
      super(renderManager, new BaseItemArrayEntry(), 0.3F);
   }
}
