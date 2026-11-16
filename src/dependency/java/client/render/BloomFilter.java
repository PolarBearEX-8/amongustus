package client.render;

import client.util.AuxiliaryListenerRegistry;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPig;
import net.minecraft.util.ResourceLocation;

public class BloomFilter extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/pig/pig.png");

   public ResourceLocation resolveResourceLocationByEntityPig(EntityPig entityPig) {
      return resourceLocation3;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityPig((EntityPig)entity);
   }

   public BloomFilter(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
      this.add(new AuxiliaryListenerRegistry(this));
   }
}
