package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.render.RenderManager;
import client.util.AuxiliaryResourceLocationEntryAdapter;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBoat;
import net.minecraft.item.SecondaryItemArrayEntry;

public class ModelBoat extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"bottom", "back", "front", "right", "left"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
      AuxiliaryResourceLocationEntryAdapter auxiliaryResourceLocationEntryAdapter = new AuxiliaryResourceLocationEntryAdapter(renderManager);
      auxiliaryResourceLocationEntryAdapter.passiveFloatEntry = passiveFloatEntry;
      auxiliaryResourceLocationEntryAdapter.factor = factor;
      return auxiliaryResourceLocationEntryAdapter;
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new SecondaryItemArrayEntry();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof SecondaryItemArrayEntry)) {
         return null;
      } else {
         SecondaryItemArrayEntry secondaryItemArrayEntry = (SecondaryItemArrayEntry)passiveFloatEntry;
         return text.equals("bottom")
            ? secondaryItemArrayEntry.boxesManagerArray[0]
            : (
               text.equals("back")
                  ? secondaryItemArrayEntry.boxesManagerArray[1]
                  : (
                     text.equals("front")
                        ? secondaryItemArrayEntry.boxesManagerArray[2]
                        : (text.equals("right") ? secondaryItemArrayEntry.boxesManagerArray[3] : (text.equals("left") ? secondaryItemArrayEntry.boxesManagerArray[4] : null))
                  )
            );
      }
   }

   public ModelBoat() {
      super(EntityBoat.class, "boat", 0.5F);
   }
}
