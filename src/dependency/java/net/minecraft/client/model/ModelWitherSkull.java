package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.CommonResourceLocationEntryAdapter;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityWitherskull;
import net.minecraft.item.DiamondGem;

public class ModelWitherSkull extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      CommonResourceLocationEntryAdapter commonResourceLocationEntryAdapter = new CommonResourceLocationEntryAdapter(renderManager);
      commonResourceLocationEntryAdapter.diamondGem = (DiamondGem)passiveFloatEntry;
      commonResourceLocationEntryAdapter.factor = factor;
      return commonResourceLocationEntryAdapter;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondGem();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondGem)) {
         return null;
      } else {
         DiamondGem diamondGem = (DiamondGem)passiveFloatEntry;
         return text.equals("head") ? diamondGem.boxesManager : null;
      }
   }

   public ModelWitherSkull() {
      super(EntityWitherskull.class, "wither_skull", 0.0F);
   }
}
