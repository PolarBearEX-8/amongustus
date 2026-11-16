package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.util.AdvancedResourceLocationArrayHandler;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import client.util.ResourceLocationArrayHolder;
import client.util.StandardMapEntry;
import net.minecraft.item.DiamondCrown;
import net.minecraft.tileentity.TileEntityEnderchest;

public class ModelEnderChest extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"lid", "base", "knob"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      StandardMapEntry standardMapEntry = StandardMapEntry.standardMapEntry;
      Object entry = standardMapEntry.resolveResourceLocationArrayHolder(TileEntityEnderchest.class);
      if (!(entry instanceof AdvancedResourceLocationArrayHandler)) {
         return null;
      } else {
         if (((ResourceLocationArrayHolder)entry).getMemberRef() == null) {
            entry = new AdvancedResourceLocationArrayHandler();
            ((ResourceLocationArrayHolder)entry).setStandardMapEntry(standardMapEntry);
         }

         ((AdvancedResourceLocationArrayHandler)entry).diamondCrown = (DiamondCrown)passiveFloatEntry;
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

   public ModelEnderChest() {
      super(TileEntityEnderchest.class, "ender_chest", 0.0F);
   }
}
