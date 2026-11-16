package net.minecraft.client.model;

import client.event.PreRenderDispatcher;
import client.util.BaseResourceLocationArrayHandler;
import client.util.BoxesManager;
import client.util.ClassEntry;
import client.util.PassiveFloatEntry;
import client.util.ResourceLocationArrayHolder;
import client.util.StandardMapEntry;
import net.minecraft.item.DiamondGem;
import net.minecraft.tileentity.TileEntitySkull;

public class ModelHeadSkeleton extends ClassEntry {
   @Override
   public String[] getTextArray() {
      return new String[]{"head"};
   }

   @Override
   public PreRenderDispatcher resolvePreRenderDispatcher(PassiveFloatEntry passiveFloatEntry, float factor) {
      StandardMapEntry standardMapEntry = StandardMapEntry.standardMapEntry;
      Object entry = standardMapEntry.resolveResourceLocationArrayHolder(TileEntitySkull.class);
      if (!(entry instanceof BaseResourceLocationArrayHandler)) {
         return null;
      } else {
         if (((ResourceLocationArrayHolder)entry).getMemberRef() == null) {
            entry = new BaseResourceLocationArrayHandler();
            ((ResourceLocationArrayHolder)entry).setStandardMapEntry(standardMapEntry);
         }

         ((BaseResourceLocationArrayHandler)entry).diamondGem = (DiamondGem)passiveFloatEntry;
         return (PreRenderDispatcher)entry;
      }
   }

   @Override
   public PassiveFloatEntry getPassiveFloatEntry() {
      return new DiamondGem(0, 0, 64, 32);
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

   public ModelHeadSkeleton() {
      super(TileEntitySkull.class, "head_skeleton", 0.0F);
   }
}
