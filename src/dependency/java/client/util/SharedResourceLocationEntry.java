package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMushroomcow;
import net.minecraft.item.ActiveItemEntry;
import net.minecraft.util.ResourceLocation;

public class SharedResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/cow/mooshroom.png");

   public ResourceLocation resolveResourceLocationByEntityMushroomco(EntityMushroomcow entityMushroomcow) {
      return resourceLocation3;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityMushroomco((EntityMushroomcow)entity);
   }

   public SharedResourceLocationEntry(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
      this.add(new ActiveItemEntry(this));
   }
}
