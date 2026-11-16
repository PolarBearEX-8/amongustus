package client.util;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.render.VertexArrayRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntitySheep;

public class BezierCollector extends AuxiliaryHeadClassEntry {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new VertexArrayRenderer(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new SupplementalFloatEntry();
   }

   public BezierCollector() {
      super(EntitySheep.class, "sheep", 0.7F);
   }
}
