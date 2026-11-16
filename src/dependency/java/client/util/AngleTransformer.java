package client.util;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityEnderman;

public class AngleTransformer extends HeadClassEntry {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      client.render.GeometryPipeline geometryPipeline = new client.render.GeometryPipeline(renderManager);
      geometryPipeline.passiveFloatEntry = passiveFloatEntry;
      geometryPipeline.factor = factor;
      return geometryPipeline;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new ExtendedFloatEntryAdapter(0.0F);
   }

   public AngleTransformer() {
      super(EntityEnderman.class, "enderman", 0.5F);
   }
}
