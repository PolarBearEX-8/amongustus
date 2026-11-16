package client.render;

import client.util.CustomListenerHandler;
import client.util.PassiveFloatEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySheep;
import net.minecraft.util.ResourceLocation;

public class VertexArrayRenderer extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/sheep/sheep.png");

   public ResourceLocation resolveResourceLocation(EntitySheep entitySheep) {
      return resourceLocation3;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntitySheep)entity);
   }

   public VertexArrayRenderer(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
      this.add(new CustomListenerHandler(this));
   }
}
