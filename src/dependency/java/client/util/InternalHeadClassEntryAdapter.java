package client.util;

import client.event.PreRenderDispatcher;
import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntitySkeleton;

public class InternalHeadClassEntryAdapter extends HeadClassEntry {
   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new AimMonitor();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      InternalResourceLocationEntryBridge internalResourceLocationEntryBridge = new InternalResourceLocationEntryBridge(renderManager);
      EntitySkinLocation.applyCoreResourceLocationEntry(internalResourceLocationEntryBridge, (FloatEntryRegistry)passiveFloatEntry);
      internalResourceLocationEntryBridge.passiveFloatEntry = passiveFloatEntry;
      internalResourceLocationEntryBridge.factor = factor;
      return internalResourceLocationEntryBridge;
   }

   public InternalHeadClassEntryAdapter() {
      super(EntitySkeleton.class, "skeleton", 0.7F);
   }
}
