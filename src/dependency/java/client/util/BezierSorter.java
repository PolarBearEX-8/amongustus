package client.util;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.entity.EntityCavespider;

public class BezierSorter extends ModelSpider {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      ResourceLocationEntryBridge resourceLocationEntryBridge = new ResourceLocationEntryBridge(renderManager);
      resourceLocationEntryBridge.passiveFloatEntry = passiveFloatEntry;
      resourceLocationEntryBridge.factor = factor;
      return resourceLocationEntryBridge;
   }

   public BezierSorter() {
      super(EntityCavespider.class, "cave_spider", 0.7F);
   }
}
