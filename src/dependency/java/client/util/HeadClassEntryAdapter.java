package client.util;

import client.event.PreRenderDispatcher;
import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityZombie;

public class HeadClassEntryAdapter extends HeadClassEntry {
   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new InternalFloatEntryBridge();
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      ResourceLocationEntryHandler resourceLocationEntryHandler = new ResourceLocationEntryHandler(renderManager);
      EntitySkinLocation.applyCoreResourceLocationEntry(resourceLocationEntryHandler, (FloatEntryRegistry)passiveFloatEntry);
      resourceLocationEntryHandler.passiveFloatEntry = passiveFloatEntry;
      resourceLocationEntryHandler.factor = factor;
      return resourceLocationEntryHandler;
   }

   public HeadClassEntryAdapter() {
      super(EntityZombie.class, "zombie", 0.5F);
   }
}
