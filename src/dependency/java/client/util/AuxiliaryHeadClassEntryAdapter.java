package client.util;

import client.event.PreRenderDispatcher;
import client.render.EntitySkinLocation;
import client.render.RenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityPigzombie;

public class AuxiliaryHeadClassEntryAdapter extends HeadClassEntry {
   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      AuxiliaryResourceLocationEntryBridge auxiliaryResourceLocationEntryBridge = new AuxiliaryResourceLocationEntryBridge(renderManager);
      EntitySkinLocation.applyCoreResourceLocationEntry(auxiliaryResourceLocationEntryBridge, (FloatEntryRegistry)passiveFloatEntry);
      auxiliaryResourceLocationEntryBridge.passiveFloatEntry = passiveFloatEntry;
      auxiliaryResourceLocationEntryBridge.factor = factor;
      return auxiliaryResourceLocationEntryBridge;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new InternalFloatEntryBridge();
   }

   public AuxiliaryHeadClassEntryAdapter() {
      super(EntityPigzombie.class, "zombie_pigman", 0.5F);
   }
}
