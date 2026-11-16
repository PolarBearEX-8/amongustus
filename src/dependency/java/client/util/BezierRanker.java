package client.util;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelMinecart;

public class BezierRanker extends ModelMinecart {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      SmoothObserver smoothObserver = new SmoothObserver(renderManager);
      smoothObserver.passiveFloatEntry = passiveFloatEntry;
      smoothObserver.factor = factor;
      return smoothObserver;
   }

   public BezierRanker() {
      super(VectorCalculator.class, "tnt_minecart", 0.5F);
   }
}
