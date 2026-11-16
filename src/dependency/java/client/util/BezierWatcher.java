package client.util;

import client.event.PreRenderDispatcher;
import client.render.BloomFilter;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityPig;

public class BezierWatcher extends AuxiliaryHeadClassEntry {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      return new BloomFilter(renderManager, passiveFloatEntry, factor);
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new AimProfiler();
   }

   public BezierWatcher() {
      super(EntityPig.class, "pig", 0.7F);
   }
}
