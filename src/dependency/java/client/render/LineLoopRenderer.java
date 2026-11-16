package client.render;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBlaze;
import net.minecraft.item.TertiaryItemArrayEntry;
import net.minecraft.util.ResourceLocation;

public class LineLoopRenderer extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/blaze.png");

   public ResourceLocation resolveResourceLocation(EntityBlaze entityBlaze) {
      return resourceLocation3;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityBlaze)entity);
   }

   public LineLoopRenderer(RenderManager renderManager) {
      super(renderManager, new TertiaryItemArrayEntry(), 0.5F);
   }
}
