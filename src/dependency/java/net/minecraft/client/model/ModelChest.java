package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.ExtendedResourceLocationArrayHandler;
import client.util.PassiveFloatEntry;
import client.util.ResourceLocationArrayHolder;
import client.util.StandardMapEntry;
import net.minecraft.item.DiamondCrown;
import net.minecraft.tileentity.TileEntityChest;

public class ModelChest extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"lid", "base", "knob"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      StandardMapEntry standardMapEntry = StandardMapEntry.standardMapEntry;
      Object entry = standardMapEntry.resolveResourceLocationArrayHolder(TileEntityChest.class);
      if (!(entry instanceof ExtendedResourceLocationArrayHandler)) {
         return null;
      } else {
         if (((ResourceLocationArrayHolder)entry).getMemberRef() == null) {
            entry = new ExtendedResourceLocationArrayHandler();
            ((ResourceLocationArrayHolder)entry).setStandardMapEntry(standardMapEntry);
         }

         ((ExtendedResourceLocationArrayHandler)entry).diamondCrown2 = (DiamondCrown)passiveFloatEntry;
         return (PreRenderDispatcher)entry;
      }
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondCrown();
   }

   @Override
   public BoxesManager resolveBoxesManager(PassiveFloatEntry passiveFloatEntry, String text) {
      if (!(passiveFloatEntry instanceof DiamondCrown)) {
         return null;
      } else {
         DiamondCrown diamondCrown = (DiamondCrown)passiveFloatEntry;
         return text.equals("lid") ? diamondCrown.boxesManager : (text.equals("base") ? diamondCrown.boxesManager2 : (text.equals("knob") ? diamondCrown.boxesManager3 : null));
      }
   }

   public ModelChest() {
      super(TileEntityChest.class, "chest", 0.0F);
   }
}
