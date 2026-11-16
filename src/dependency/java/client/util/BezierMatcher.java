package client.util;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityMushroomcow;

public class BezierMatcher extends AuxiliaryHeadClassEntry {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new SharedResourceLocationEntry(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new AimTracker();
   }

   public BezierMatcher() {
      super(EntityMushroomcow.class, "mooshroom", 0.7F);
   }
}
