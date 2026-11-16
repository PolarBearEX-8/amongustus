package client.util;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCow;

public class BezierTracker extends AuxiliaryHeadClassEntry {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new CommonResourceLocationEntry(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new AimTracker();
   }

   public BezierTracker() {
      super(EntityCow.class, "cow", 0.7F);
   }
}
